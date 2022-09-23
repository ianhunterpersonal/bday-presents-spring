package com.totnesjava.bdaypresents.bdd;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.utility.DockerImageName;

import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberBootstrap {

   @Autowired
   protected TestRestTemplate testRestTemplate;
    
   @Autowired
   protected DockerChromeWebDriver dockerChromeWebDriver;
   
 	@LocalServerPort
 	protected int				port;

 	protected WebDriver getWebDriver() {
 		return dockerChromeWebDriver.getWebDriver();
 	}
 	
}

