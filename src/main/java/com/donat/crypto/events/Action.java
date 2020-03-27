package com.donat.crypto.events;

import com.donat.crypto.events.enums.RelationType;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "actions")
@Data
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private Indicator indicatorOne;

    @OneToOne(fetch = FetchType.EAGER)
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
