package org.aston.ems.admin_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authorities")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @JsonBackReference
    @ManyToMany(mappedBy = "authorities")
    @Builder.Default
    private Set<User> users = new HashSet<>();

    public Authority(String name) {
        this.name = name;
    }

    public void addUser(User user){
        users.add(user);
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Authority))
            return false;
        Authority authority = (Authority) o;
        return this.name.equals(authority.getName());
    }

    @Override
    public int hashCode() {
        return 31*name.hashCode();
    }
}
