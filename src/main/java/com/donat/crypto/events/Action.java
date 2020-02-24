package com.donat.crypto.events;

import com.donat.crypto.events.enums.RelationType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Action {

    private Indicator indicatorOne;

    private Indicator indicatorTwo;

    private RelationType relation;
}
