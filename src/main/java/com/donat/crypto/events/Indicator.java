package com.donat.crypto.events;

import com.donat.crypto.events.enums.IndicatorType;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "indicators")
@Data
public class Indicator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private IndicatorType indicatorType;

    private Double value;
}
