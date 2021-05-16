package com.lozano.billing.back.api.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lozano.billing.back.api.models.entity.User;
import com.lozano.billing.back.api.models.services.IUserService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class UserController {
	
	// TODO add, delete and update role 

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

	@GetMapping("/users/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {

		User user = null;
		Map<String, Object> map = new HashMap<>();

		try {
			user = userService.findById(id);
		} catch (DataAccessException e) {
			map.put("success", false);
			map.put("message", "Error query in database.");
			map.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (user == null) {
			map.put("success", false);
			map.put("message", "User with " + id + " doesn't exist.");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
		}

		map.put("success", true);
		map.put("data", user);
		map.put("message", "User was found successfully.");

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@PostMapping("/users")
	public ResponseEntity<?> create(@RequestBody User user) {

		User userNew = null;
		Map<String, Object> map = new HashMap<>();

		try {
			userNew = userService.save(user);
		} catch (DataAccessException e) {
			map.put("success", false);
			map.put("message", "Error creating new user.");
			map.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		map.put("success", true);
		map.put("data", userNew);
		map.put("message", "User was created successfully.");

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.CREATED);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<?> update(@RequestBody User user, @PathVariable Long id) {

		User userUpdated = null;
		Map<String, Object> map = new HashMap<>();

		try {
			user.setId(id);
			userUpdated = userService.save(user);
		} catch (DataAccessException e) {
			map.put("success", false);
			map.put("message", "Error updating user.");
			map.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		map.put("success", true);
		map.put("data", userUpdated);
		map.put("message", "User was updated successfully.");

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.CREATED);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Map<String, Object> map = new HashMap<>();

		try {
			userService.delete(id);
		} catch (DataAccessException e) {
			map.put("success", false);
			map.put("message", "Error deleting user.");
			map.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		map.put("success", true);
		map.put("message", "User was deleted successfully.");

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

}
