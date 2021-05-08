package com.lozano.billing.back.api.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lozano.billing.back.api.models.entity.User;
import com.lozano.billing.back.api.models.services.IUserService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class UserController {

	private static int pageSize = 10;

	@Autowired
	private IUserService userService;

	@GetMapping("/users")
	public HashMap<String, Object> get() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("success", true);
		map.put("data", userService.findAll());
		map.put("message", "Get all users.");
		return map;
	}

	@GetMapping("/users/page/{page}")
	public Page<User> get(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, pageSize);
		return userService.findAll(pageable);
	}

}
