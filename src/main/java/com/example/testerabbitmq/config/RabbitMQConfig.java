package com.example.testerabbitmq.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
  public static final String QUEUE1 = "QUEUE1";
  public static final String QUEUE2 = "QUEUE2";
  public static final String QUEUE3 = "QUEUE3";
  public static final String QUEUE4 = "QUEUE4";
  public static final String EXCHANGE_TESTE1 = "EXCHANGE_TESTE1";
  public static final String EXCHANGE_TESTE2 = "EXCHANGE_TESTE2";

  @Bean
  public Queue queue1() {
    return new Queue(QUEUE1, false);
  }

  @Bean
  public Queue queue2() {
    return new Queue(QUEUE2, false);
  }

  @Bean
  public Queue queue3() {
    return new Queue(QUEUE3, false);
  }

  @Bean
  public Queue queue4() {
    return new Queue(QUEUE4, false);
  }

  @Bean
  public FanoutExchange fanoutExchange1() {
    return new FanoutExchange(EXCHANGE_TESTE1, false, false);
  }

  @Bean
  public FanoutExchange fanoutExchange2() {
    return new FanoutExchange(EXCHANGE_TESTE2, false, false);
  }

  @Bean
  public Binding binding1(FanoutExchange fanoutExchange1, Queue queue1) {
    return BindingBuilder.bind(queue1).to(fanoutExchange1);
  }

  @Bean
  public Binding binding2(FanoutExchange fanoutExchange1, Queue queue2) {
    return BindingBuilder.bind(queue2).to(fanoutExchange1);
  }

  @Bean
  public Binding binding3(FanoutExchange fanoutExchange2, Queue queue2) {
    return BindingBuilder.bind(queue2).to(fanoutExchange2);
  }

  @Bean
  public Binding binding4(FanoutExchange fanoutExchange2, Queue queue3) {
    return BindingBuilder.bind(queue3).to(fanoutExchange2);
  }

  @Bean
  public MessageConverter messageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public AmqpTemplate template(ConnectionFactory connectionFactory) {
    RabbitTemplate template = new RabbitTemplate(connectionFactory);
    template.setMessageConverter(messageConverter());
    return template;
  }
}
