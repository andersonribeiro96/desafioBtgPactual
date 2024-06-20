package com.desafio.btgpactual.orderms.listener;

import com.desafio.btgpactual.orderms.listener.dto.OrderCreateEvent;
import com.desafio.btgpactual.orderms.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static com.desafio.btgpactual.orderms.config.RabbitMqConfig.ORDER_CREATED_QUEUE;

@Component
public class OrderCreatedListener {

    private final Logger logger = LoggerFactory.getLogger(OrderCreatedListener.class);

    private final OrderService service;

    public OrderCreatedListener(OrderService service) {
        this.service = service;
    }

    @RabbitListener(queues = ORDER_CREATED_QUEUE)
    public void listen(Message<OrderCreateEvent> message) {
        logger.info("Message consumed: {} ", message);
        service.save(message.getPayload());
    }





}
