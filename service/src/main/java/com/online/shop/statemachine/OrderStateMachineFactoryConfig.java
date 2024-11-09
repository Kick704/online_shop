package com.online.shop.statemachine;

import com.online.shop.entity.Order;
import com.online.shop.enums.OrderEvent;
import com.online.shop.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

import java.util.EnumSet;

/**
 * Конфигурация фабрики машин состояний для обработки заказов в интернет-магазине.
 * Определяет статусы заказов, события для их переходов и обработчик событий.
 */
@Configuration
@EnableStateMachineFactory
public class OrderStateMachineFactoryConfig extends EnumStateMachineConfigurerAdapter<OrderStatus, OrderEvent> {

    @Autowired
    private OrderStateMachineEventListener orderStateMachineEventListener;

    /**
     * Настройка конфигурации, включающий автозапуск и добавление слушателя событий
     *
     * @param config объект конфигурации для настройки параметров
     * @throws Exception если возникает настройки конфигурации
     */
    @Override
    public void configure(StateMachineConfigurationConfigurer<OrderStatus, OrderEvent> config) throws Exception {
        config
                .withConfiguration()
                .autoStartup(true)
                .listener(orderStateMachineEventListener);
    }

    /**
     * Определяет состояния машины на основе статусов заказа {@link OrderStatus},
     * включая определение начального и конечных состояний
     *
     * @param states объект для настройки состояний
     * @throws Exception если возникает ошибка при определении состояний
     */
    @Override
    public void configure(StateMachineStateConfigurer<OrderStatus, OrderEvent> states) throws Exception {
        states.withStates()
                .initial(OrderStatus.CREATED)
                .states(EnumSet.allOf(OrderStatus.class))
                .end(OrderStatus.DELIVERED)
                .end(OrderStatus.CANCELLED);
    }

    /**
     * Определяет переходы между состояниями машины на основе событий {@link OrderEvent}
     *
     * @param transitions объект для настройки переходов
     * @throws Exception если возникает ошибка при определении переходов
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatus, OrderEvent> transitions) throws Exception {
        transitions.withExternal()
                .source(OrderStatus.CREATED).target(OrderStatus.COLLECTING).event(OrderEvent.COLLECT)
                .and().withExternal()
                .source(OrderStatus.COLLECTING).target(OrderStatus.SHIPPED).event(OrderEvent.SHIP)
                .and().withExternal()
                .source(OrderStatus.SHIPPED).target(OrderStatus.DELIVERED).event(OrderEvent.CONFIRM_DELIVERY)
                .and().withExternal()
                .source(OrderStatus.CREATED).target(OrderStatus.CANCELLED).event(OrderEvent.CANCEL)
                .and().withExternal()
                .source(OrderStatus.COLLECTING).target(OrderStatus.CANCELLED).event(OrderEvent.CANCEL)
                .and().withExternal()
                .source(OrderStatus.SHIPPED).target(OrderStatus.CANCELLED).event(OrderEvent.CANCEL);
    }

    /**
     * Создает и настраивает объект {@link StateMachinePersister} для поддержки сохранения и восстановления
     * состояний заказов в хранилище.
     *
     * @param orderStateMachinePersist реализация объекта из {@link OrderStateMachinePersist}
     * @return объект для управления состояниями машины
     */
    @Bean
    public StateMachinePersister<OrderStatus, OrderEvent, Order> stateMachinePersister(
            OrderStateMachinePersist orderStateMachinePersist
    ) {
        return new DefaultStateMachinePersister<>(orderStateMachinePersist);
    }

}
