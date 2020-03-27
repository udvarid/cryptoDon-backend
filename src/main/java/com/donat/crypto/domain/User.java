package com.donat.crypto.domain;

import com.donat.crypto.events.Event;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", length = 250, unique = true, nullable = false)
    private String email;

    @Column(name = "password", length = 250, nullable = false)
    private String password;

    private String fullname;

    private boolean enabled;

    private LocalDateTime timeOfRegistration;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private List<Role> roles = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Wallet> wallets = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Event> event = new HashSet<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		User user = (User) o;

		return id != null ? id.equals(user.id) : user.id == null;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
