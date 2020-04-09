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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "actions_generator")
	@SequenceGenerator(name = "actions_generator", sequenceName = "actions_seq")
	private Long id;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "actionlist_id")
	List<Action> actionsAnd = new ArrayList<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Actions actions = (Actions) o;

		return id != null ? id.equals(actions.id) : actions.id == null;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
