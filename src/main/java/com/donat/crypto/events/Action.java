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
}
