package com.lozano.billing.back.api.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.lozano.billing.back.api.models.entity.User;

public interface IUserDao extends CrudRepository<User, Long> {

	public User findByUsername(String username);

	// @Query("select u from User u where u.username=?1")
	// public User findByUsernameQuery(String username);

}
