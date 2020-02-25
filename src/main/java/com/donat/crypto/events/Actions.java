package com.donat.crypto.events;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "group_of_actions")
@Data
public class Actions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(fetch = FetchType.EAGER)
	List<Action> actionsAnd = new ArrayList<>();
}
