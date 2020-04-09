package com.donat.crypto.service;

import com.donat.crypto.domain.User;
import com.donat.crypto.events.Action;
import com.donat.crypto.events.Actions;
import com.donat.crypto.events.Event;
import com.donat.crypto.events.Indicator;
import com.donat.crypto.events.enums.CCY;
import com.donat.crypto.events.enums.IndicatorType;
import com.donat.crypto.events.enums.RelationType;
import com.donat.crypto.events.enums.TransactionType;
import com.donat.crypto.repository.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Component
@Transactional
public class StartingTasks {

    @Autowired
    UserRepository userRepository;

    @Autowired
    IndicatorRepository indicatorRepository;

    @Autowired
    ActionRepository actionRepository;

    @Autowired
    ActionsRepository actionsRepository;

    @Autowired
    EventRepository eventRepository;

    @PostConstruct
    void runMe() {
//        User user = userRepository.findByEmailAndFetched("udvarid@hotmail.com");
//        if (user == null) {
//            return;
//        }
//
//        Indicator indicatorOne = createIndicator(IndicatorType.STATIC_PRICE, 500d);
//        Indicator indicatorTwo = createIndicator(IndicatorType.CALC_ACTUAL_PRICE, 0d);
//        Indicator indicatorThree = createIndicator(IndicatorType.STATIC_PRICE, 400d);
//        Indicator indicatorFour = createIndicator(IndicatorType.CALC_ACTUAL_PRICE, 0d);
//
//        Indicator indicatorFive = createIndicator(IndicatorType.STATIC_PRICE, 450d);
//        Indicator indicatorSix = createIndicator(IndicatorType.CALC_ACTUAL_PRICE, 0d);
//        Indicator indicatorSeven = createIndicator(IndicatorType.STATIC_PRICE, 350d);
//        Indicator indicatorEight = createIndicator(IndicatorType.CALC_ACTUAL_PRICE, 0d);
//
//
//        Action action = createAction(indicatorOne, indicatorTwo, RelationType.GE);
//        Action action2 = createAction(indicatorThree, indicatorFour, RelationType.GT);
//
//        Action action3 = createAction(indicatorFive, indicatorSix, RelationType.GT);
//        Action action4 = createAction(indicatorSeven, indicatorEight, RelationType.GE);
//
//        Actions actions1 = createActionGroup(action, action2);
//        Actions actions2 = createActionGroup(action3, action4);
//
//        Event event = new Event();
//        event.getActionLists().add(actions1);
//        event.getActionLists().add(actions2);
//        event.setAmount(20d);
//        event.setCcy(CCY.ETH);
//        event.setTransactionType(TransactionType.BUY);
//
//        event.setUser(user);
//        eventRepository.saveAndFlush(event);
//
//        user.getEvents().add(event);
//        userRepository.saveAndFlush(user);
//
//        System.out.println(user.getFullname());
    }

    @NotNull
    private Actions createActionGroup(Action... actionGroup) {
        Actions actions = new Actions();
        for (Action action : actionGroup) {
            actions.getActionsAnd().add(action);
        }
        actionsRepository.saveAndFlush(actions);
        return actions;
    }

    private Action createAction(Indicator indicatorOne, Indicator indicatorTwo, RelationType relation) {
        Action action = new Action();
        action.setIndicatorOne(indicatorOne);
        action.setIndicatorTwo(indicatorTwo);
        action.setRelation(relation);
        actionRepository.saveAndFlush(action);
        return action;
    }

    private Indicator createIndicator(IndicatorType type, Double value) {
        Indicator indicator = new Indicator();
        indicator.setIndicatorType(type);
        indicator.setValue(value);
        indicatorRepository.saveAndFlush(indicator);
        return indicator;
    }
}
