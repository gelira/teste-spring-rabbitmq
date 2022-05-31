package com.example.testerabbitmq.controllers;

import com.example.testerabbitmq.config.RabbitMQConfig;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/teste")
public class TesteController {

  private final RabbitTemplate template;

  @GetMapping
  public String teste(@RequestParam(defaultValue = "Nada") String msg) {
    String msgDirect = "Direct: " + msg;
    String msgFanout1 = "Fanout 1: " + msg;
    String msgFanout2 = "Fanout 2: " + msg;

    template.convertAndSend(RabbitMQConfig.QUEUE4, msgDirect);
    template.convertAndSend(RabbitMQConfig.EXCHANGE_TESTE1, "", msgFanout1);
    template.convertAndSend(RabbitMQConfig.EXCHANGE_TESTE2, "", msgFanout2);

    return "OK: " + msg;
  }
}
