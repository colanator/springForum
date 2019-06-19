package com.colanator.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {
	@GetMapping({"/", "/test"})
	public String test(Model model, @RequestParam(value="test", required=false, defaultValue="testing") String test) {
		model.addAttribute("test", test);
		return "test";
	}
}
