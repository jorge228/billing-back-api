package com.lozano.billing.back.api.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lozano.billing.back.api.models.entity.User;

public interface IUserService {

	public List<User> findAll();

	public Page<User> findAll(Pageable pageable);

	public User findById(Long id);

	public User save(User user);

	public void delete(Long id);

}
