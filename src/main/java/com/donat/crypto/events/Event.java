package com.donat.crypto.events;

import java.util.List;
import com.donat.crypto.events.enums.CCY;
import com.donat.crypto.events.enums.TransactionType;
import lombok.Data;

@Data
public class Event {

	private CCY ccy;

	private TransactionType transactionType;

	private Double amount;

	private List<ActionAnd> andActions;

	private List<ActionOr> orActions;

	private boolean onlyFullAmount;

	private boolean fullFilled;

}
