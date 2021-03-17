package com.lozano.billing.back.api.models.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lozano.billing.back.api.models.dao.IUserDao;

@Service
public class UserService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IUserDao userDao;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		com.lozano.billing.back.api.models.entity.User user = userDao.findByUsername(username);

		if (user == null) {
			String msg = "User " + username + " doesn't eixist";
			logger.error(msg);
			throw new UsernameNotFoundException(msg);
		}

		List<GrantedAuthority> authorities = user.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.peek(authority -> logger.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());
		
		return new User(user.getUsername(), user.getPassword(), user.getEnabled(), true, true, true, authorities);
	
	}

}
