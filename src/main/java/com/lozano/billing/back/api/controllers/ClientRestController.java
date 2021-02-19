package com.lozano.billing.back.api.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
		map.put("message", "Get all clients");
		return map;
	}

	@GetMapping("/clients/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Client show(@PathVariable Long id) {
		return clientService.findById(id);
	}

	@PostMapping("/clients")
	@ResponseStatus(HttpStatus.CREATED)
	public Client create(@RequestBody Client client) {
		return clientService.save(client);
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
