package org.aston.ems.admin_service.security;

import java.util.Collection;

import org.aston.ems.admin_service.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class SecurityUser implements UserDetails{
	
	private final User user;
	
	public static SecurityUser from(User user) {
		return new SecurityUser(user);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getAuthorities().stream().map(x -> new SimpleGrantedAuthority(x.getName())).toList();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	

}
