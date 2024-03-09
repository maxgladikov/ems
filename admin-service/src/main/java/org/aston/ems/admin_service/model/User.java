package org.aston.ems.admin_service.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

import jakarta.persistence.*;
import org.aston.ems.admin_service.security.EncryptionAlgorithm;
import org.aston.ems.admin_service.security.SecurityUser;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "users")
public class User implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 7347135328470391617L;

	@Id
	@SequenceGenerator(name = "user_seq",
			sequenceName = "user_seq",
			initialValue = 1, allocationSize = 3)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@Column(name = "id", nullable = false)
	private Long id;
	
	@NotEmpty
	@Column(nullable = false, unique = true)
	private String username;

	@Size(min=5, message = "Password too short")
	@Column(name = "parole", nullable = false)
	private String password;

	@NotEmpty
	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.EAGER)
	@JoinTable(
			name = "users_authorities",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "authority_id")
	)
	@Builder.Default
	private Collection<Authority> authorities = new HashSet<>();

	public void setAuthority(Authority auth){
		if(authorities == null)
			authorities = new HashSet<>();
		authorities.add(auth);
	}

	public void setAuthoritis(String[] roles){
		Arrays.stream(roles).map(Authority::new).forEach(x -> authorities.add(x));
	}


	@Override
	public boolean equals(Object o) {
		if (!(o instanceof User))
			return false;
		User user = (User) o;
		return this.username.equals(user.getUsername());
	}
	
	@Override
	public int hashCode() {
		return 31*username.hashCode()+password.hashCode();
	}


}
