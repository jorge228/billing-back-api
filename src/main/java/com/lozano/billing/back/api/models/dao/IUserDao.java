package com.lozano.billing.back.api.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lozano.billing.back.api.models.entity.User;

public interface IUserDao extends JpaRepository<User, Long> {

}
