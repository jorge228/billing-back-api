package com.lozano.billing.back.api.models.services;

import java.util.List;

import com.lozano.billing.back.api.models.entity.User;

public interface IUserService {

	public List<User> findAll();

}
