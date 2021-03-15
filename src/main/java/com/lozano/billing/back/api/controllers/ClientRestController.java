package com.lozano.billing.back.api.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lozano.billing.back.api.models.entity.Client;
import com.lozano.billing.back.api.models.services.IClientService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ClientRestController {

	@Autowired
	private IClientService clientService;

	@GetMapping("/clients")
	public HashMap<String, Object> get() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("success", true);
		map.put("data", clientService.findAll());
		map.put("message", "Get all clients.");
		return map;
	}

	@GetMapping("/clients/{id}")
	// @ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> show(@PathVariable Long id) {

		Client client = null;
		Map<String, Object> map = new HashMap<>();

		try {
			client = clientService.findById(id);
		} catch (DataAccessException e) {
			map.put("message", "Error query in database.");
			map.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (client == null) {
			map.put("message", "Client with " + id + " doesn't exist.");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Client>(client, HttpStatus.OK);
	}

	@PostMapping("/clients")
	//@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody Client client) {
		
		Client clientNew = null;
		Map<String, Object> map = new HashMap<>();
		
		try {
			clientNew = clientService.save(client);
		} catch (DataAccessException e) {
			map.put("message", "Error creating new client.");
			map.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		map.put("success", true);
		map.put("data", clientNew);
		map.put("message", "Client was created successfully.");
		
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.CREATED);
	}

	@PutMapping("/clients/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Client update(@RequestBody Client client, @PathVariable Long id) {
		client.setId(id);
		return clientService.save(client);
	}

	@DeleteMapping("/clients/{id}")
	// @ResponseStatus(HttpStatus.NO_CONTENT) //204
	public Boolean delete(@PathVariable Long id) {
		return clientService.delete(id);
	}

}
