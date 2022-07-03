package com.ehabaleid.healthdiary.service;

import com.ehabaleid.healthdiary.model.user.User;
import com.ehabaleid.healthdiary.data.UserRepository;
import com.ehabaleid.healthdiary.model.user.AppUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

	UserRepository userRepository;

	public AppUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username + "not found");
		}
		return new AppUserDetails(user);
	}


	public int loadUserIdByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username + "not found");
		}
		return user.getId();
	}


	public User getUserByUsername(String name) {
		return userRepository.findByUsername(name);
	}

	public User getUserById(int id) {
		return userRepository.getById(id);
	}
}
