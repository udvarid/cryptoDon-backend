package com.donat.crypto.domain;

import com.donat.crypto.events.Event;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "users")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
	@SequenceGenerator(name = "user_generator", sequenceName = "user_seq")
	private Long id;

	@NotNull
	@Size(max = 250)
	@Column(name = "email", unique = true)
	private String email;

	@NotNull
	@Size(max = 250)
	@Column(name = "password")
	private String password;

	private String fullname;

	private boolean enabled;

	@CreationTimestamp
	private LocalDateTime timeOfRegistration;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "users_roles",
		joinColumns = {@JoinColumn(name = "user_id")},
		inverseJoinColumns = {@JoinColumn(name = "role_id")}
	)
	private Set<Role> roles = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Wallet> wallets = new HashSet<>();

	@OneToMany
	@JoinColumn(name = "user_id")
	private Set<Event> events = new HashSet<>();

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
