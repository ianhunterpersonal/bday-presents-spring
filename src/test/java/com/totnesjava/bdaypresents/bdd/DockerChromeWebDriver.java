package com.totnesjava.bdaypresents.bdd;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.utility.DockerImageName;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Getter
public class DockerChromeWebDriver {

	private static String DOCKER_IMAGE_NAME = "selenium/standalone-chrome-debug";
	private static String DOCKER_TAG = "latest";
	
	private WebDriver webDriver;
	
	public BrowserWebDriverContainer<?> chromeWebDriverContainer;
	    
	@PostConstruct
	public void postConstruct() {
		
		ChromeOptions options = new ChromeOptions().addArguments("--headless", "--incognito");

		DockerImageName imageName = DockerImageName.parse(DOCKER_IMAGE_NAME).withTag(DOCKER_TAG);
		
		chromeWebDriverContainer = new BrowserWebDriverContainer<>(imageName).withCapabilities(options);				

		chromeWebDriverContainer.start();
		
		webDriver = chromeWebDriverContainer.getWebDriver();
		
		log.info("Chrome started");
		
	}
	
	@PreDestroy
	public void afterAll() {
		if ((chromeWebDriverContainer != null) && chromeWebDriverContainer.isRunning()) {
			chromeWebDriverContainer.close();
			chromeWebDriverContainer.stop();
		}
	}
	
}
