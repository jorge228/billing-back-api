package com.lozano.billing.back.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lozano.billing.back.api.models.entity.Client;
import com.lozano.billing.back.api.models.services.IClientService;

@RestController
@RequestMapping("/api")
public class ClientRestController {

	@Autowired
	private IClientService clientService;

	@GetMapping("/clients")
	public List<Client> index() {
		return clientService.findAll();
	}

}
