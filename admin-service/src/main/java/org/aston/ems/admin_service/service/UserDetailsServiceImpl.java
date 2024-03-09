package org.aston.ems.admin_service.service;

import lombok.RequiredArgsConstructor;
import org.aston.ems.admin_service.repository.UserRepository;
import org.aston.ems.admin_service.security.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.findByUsername(username).map(SecurityUser::from)
            .orElseThrow(() -> new UsernameNotFoundException("User with requested username not found."));
    }

}
