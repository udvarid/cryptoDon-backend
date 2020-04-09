package com.donat.crypto.events;

import com.donat.crypto.events.enums.RelationType;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "actions")
@Data
public class Action {

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "action_generator")
	@SequenceGenerator(name = "action_generator", sequenceName = "action_seq")
    private Long id;

    @OneToOne
    private Indicator indicatorOne;

    @OneToOne
    private Indicator indicatorTwo;

    @Enumerated(EnumType.STRING)
    private RelationType relation;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Action action = (Action) o;

		return id != null ? id.equals(action.id) : action.id == null;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
