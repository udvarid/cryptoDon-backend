package com.donat.crypto.events;

import java.util.ArrayList;
import java.util.List;
import com.donat.crypto.events.enums.CCY;
import com.donat.crypto.events.enums.TransactionType;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "events")
@Data
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private CCY ccy;

	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;

	private Double amount;

	@OneToMany(fetch = FetchType.EAGER)
	private List<Actions> actionLists = new ArrayList<>();

	private boolean onlyFullAmount = false;

	private boolean fullFilled = false;

}
