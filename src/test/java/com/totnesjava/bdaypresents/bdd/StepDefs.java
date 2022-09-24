package com.totnesjava.bdaypresents.bdd;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;

import com.totnesjava.bdaypresents.persons.PersonEntity;
import com.totnesjava.bdaypresents.persons.PersonRepository;
import com.totnesjava.bdaypresents.persons.PersonResource;
import com.totnesjava.bdaypresents.sessions.Credentials;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StepDefs extends AbstractSteps {

	@Autowired
	private PersonRepository	personRepository;

	@Autowired
	protected TestRestTemplate	testRestTemplate;
	
	
	// this method executes before every scenario
	@Before
	public void before() {
		fixIssueOfRetriesByRestTemplate();
		personRepository.deleteAll();
		webDriver = getWebDriver();
	}

	// this method executes before every step
	@BeforeStep
	public void beforeStep() {
	}

	// this method executes after every scenario
	@After
	public void cleanUp() {
	}

	// this method executes after every step
	@AfterStep
	public void afterStep() {
	}

	@Given("I create users")
	public void i_create_users(List<PersonEntity> persons) {
		persons.forEach((p) -> {
			personRepository.save(p);
		});
		//guiTest();
	}

	@When("I login with email {string} and password {string}")
	public void i_login_with_email_and_password(String email, String password) {

		lastLoggedInPerson = null;
		final Credentials creds = Credentials.builder().email(email).password(password).build();
		try {
			PersonResource result = super.callRestPost("/v1/sessions/login", creds, PersonResource.class);
			lastLoggedInPerson = result;
		} catch (Exception ex) {
			lastLoggedInPerson = null;
			throw ex;
		}
	}

	@Then("I recieve a valid token from server")
	public void i_recieve_a_valid_token_from_server() {
		Assertions.assertNotNull(lastLoggedInPerson);
		Assertions.assertTrue(GuidUtils.isValidGUID(lastLoggedInPerson.getLoginToken()), "Login Token is not a GUID");

	}

	@Then("I recieve HTTP Status {string}")
	public void i_recieve_http_status(String expectedHttpStatus) {
		Assertions.assertEquals(super.lastHttpStatus, Integer.valueOf(expectedHttpStatus));
	}

	@Then("I am not logged in")
	public void i_recieve_a_null_token_from_server() {
		Assertions.assertTrue((lastLoggedInPerson == null) || (lastLoggedInPerson.getLoginToken() == null));
	}

	public void guiTest() {
		
		// Given we use correct email
		webDriver.get("http://host.docker.internal:" + super.port + "/");
		WebElement nameText = webDriver.findElement(By.id("name"));
		nameText.clear();
		nameText.sendKeys("ian");
		
		// When we login
		WebElement loginButton = webDriver.findElement(By.id("login-button")); // //*[@id="login-button"]
		loginButton.click();
		
		// Then we get apporpriate page elements
		WebElement we = webDriver.findElement(By.xpath("/html/body/div[1]/div/p[1]"));
		Assertions.assertEquals("Name: _Test_Person_1", we.getText());
		
	}
	
	/**
	 * In the case of a 401(Unauthorised) it seems that RestTemplate attempts a
	 * retry. Not desirable in a test, so disable it.
	 * 
	 * @Link https://stackoverflow.com/questions/16748969/java-net-httpretryexception-cannot-retry-due-to-server-authentication-in-strea
	 */
	private void fixIssueOfRetriesByRestTemplate() {
		testRestTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		testRestTemplate.getRestTemplate().setErrorHandler(new DefaultResponseErrorHandler() {
			public boolean hasError(ClientHttpResponse response) throws IOException {
				HttpStatus statusCode = response.getStatusCode();
				return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
			}
		});
	}

	private WebDriver webDriver;
	
	private PersonResource lastLoggedInPerson;

}
