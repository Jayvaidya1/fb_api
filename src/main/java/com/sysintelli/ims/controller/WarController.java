package com.sysintelli.ims.controller;



import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(WarController.WAR_BASE_URI)
public class WarController {
public static final String WAR_BASE_URI="svc1/v1/war";
@RequestMapping(value="{war}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public int getContract(@PathVariable final int war) {
	return war;
}
}
