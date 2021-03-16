package com.lozano.billing.back.api.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lozano.billing.back.api.models.entity.Client;

public interface IClientService {

	public List<Client> findAll();
	
	public Page<Client> findAll(Pageable pageable);

	public Client findById(Long id);

	public Client save(Client client);

	public void delete(Long id);

	public Integer countClients();

}
