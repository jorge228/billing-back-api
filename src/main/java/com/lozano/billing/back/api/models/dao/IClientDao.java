package com.lozano.billing.back.api.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.lozano.billing.back.api.models.entity.Client;

public interface IClientDao extends CrudRepository<Client, Long> {

	@Query("select count(*) from Client")
	Integer countClients();

}
