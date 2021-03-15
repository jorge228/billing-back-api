package com.lozano.billing.back.api.models.services;

import java.util.List;

import com.lozano.billing.back.api.models.entity.Client;

public interface IClientService {

	public List<Client> findAll();

	public Client findById(Long id);

	public Client save(Client client);

	public void delete(Long id);

}
