package com.donat.crypto.events;

import java.util.ArrayList;
import java.util.List;
import com.donat.crypto.domain.User;
import com.donat.crypto.events.enums.CCY;
import com.donat.crypto.events.enums.TransactionType;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "events")
@Data
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_generator")
	@SequenceGenerator(name = "event_generator", sequenceName = "event_seq")
	private Long id;

	@ManyToOne
	private User user;

	@Enumerated(EnumType.STRING)
	private CCY ccy;

	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;

	private Double amount;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "event_id")
	private List<Actions> actionLists = new ArrayList<>();

	private boolean onlyFullAmount = false;

	private boolean fullFilled = false;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Event event = (Event) o;

		return id != null ? id.equals(event.id) : event.id == null;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

}
