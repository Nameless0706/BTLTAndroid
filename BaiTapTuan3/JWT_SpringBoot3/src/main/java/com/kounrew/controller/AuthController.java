package com.kounrew.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AuthController {
	@GetMapping("login")
	public String index() {
		return "login";
	}
	
	@GetMapping("user/profile")
	public String profile() {
		return "profile";
	}
	
}
