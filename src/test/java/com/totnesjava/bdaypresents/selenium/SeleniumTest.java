package com.totnesjava.bdaypresents.selenium;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import com.totnesjava.bdaypresents.bdd.DockerChromeWebDriver;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
//,properties = {
//		 "spring.datasource.url=jdbc:tc:postgresql:14-alpine://testcontainers/giftsdb"
//		}
)
@Slf4j
public class SeleniumTest {

//	@Test
//	public void driverTest() {
//
//		chrome.getWebDriver().get("http://host.docker.internal:" + port + "/");
//
//		log.info("PAGE = " + chrome.getWebDriver().getTitle());
//	}
//
//	@Autowired
//	private DockerChromeWebDriver chrome;
//
//	@LocalServerPort
//	private int port;
	
}
