package com.donat.crypto.events;

import lombok.Data;

@Data
public class Indicator {

    private IndicatorType indicatorType;

    private Double value;
}
