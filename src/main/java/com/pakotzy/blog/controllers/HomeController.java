package com.pakotzy.blog.controllers;

import com.pakotzy.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@Autowired
	PostService postService;

	@RequestMapping("/")
	public String root() {
		return "redirect:/home";
	}

	@RequestMapping("home")
	public String home(Model model) {
		model.addAttribute("posts", postService.findAll());

		return "home";
	}
}
