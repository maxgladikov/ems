package org.aston.ems.admin_service.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aston.ems.admin_service.mapper.UserMapper;
import org.aston.ems.admin_service.model.Authority;
import org.aston.ems.admin_service.model.User;
import org.aston.ems.admin_service.repository.AuthorityRepository;
import org.aston.ems.admin_service.repository.UserRepository;
import org.aston.ems.admin_service.util.UserUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
	private final AuthorityRepository authorityRepository;
	private final PasswordEncoder encoder;

	@Override
	public User get(String name) {
		return userRepository.findByUsername(name)
			.orElseThrow(() -> new UsernameNotFoundException("User with requested username not found."));
	}

	@Override
	public List<User> get() {
		return userRepository.findAll();
	}

	@Transactional
	@Override
	public void create(User user) {

		if (userRepository.existsByUsername(user.getUsername()))
			throw new RuntimeException();
		log.info("************************");
		log.info("user:{}",user.toString());
		log.info("************************");
		user.setPassword(encoder.encode(user.getPassword()));
		var authorities = user.getAuthorities()
			.stream().map(
							x -> authorityRepository.findByName(x.getName())
										.orElseGet(() -> authorityRepository.save(x))
						).toList();
		authorities.forEach(x -> x.addUser(user));
		user.setAuthorities(authorities);
		log.info("user:{}",user.toString());
		userRepository.save(user);

	}

	@Transactional
	@Override
	public void update(User newUser) {
		User oldUser = get(newUser.getUsername());
		create(UserUtils.update(newUser,oldUser));
	}

	@Transactional
	public void delete(String username) {
		userRepository.deleteByUsername(username);
	}

}
