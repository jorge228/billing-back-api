package com.lozano.billing.back.api.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lozano.billing.back.api.models.entity.Client;

public interface IClientDao extends JpaRepository<Client, Long> {

	@Query("select count(*) from Client")
	Integer countClients();

}
