package com.donat.crypto.events;

import com.donat.crypto.events.enums.IndicatorType;
import lombok.Data;

@Data
public class Indicator {

    private IndicatorType indicatorType;

    private Double value;
}
