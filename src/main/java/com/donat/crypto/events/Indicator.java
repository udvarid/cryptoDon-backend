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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Indicator indicator = (Indicator) o;

        return id != null ? id.equals(indicator.id) : indicator.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
