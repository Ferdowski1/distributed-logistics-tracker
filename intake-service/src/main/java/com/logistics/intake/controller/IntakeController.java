package com.logistics.intake.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistics.intake.config.RabbitMQConfig;
import com.logistics.intake.dto.PackageDTO;

@RestController
@RequestMapping("/intake")
public class IntakeController {

    private final RabbitTemplate rabbitTemplate;

    public IntakeController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping
    public ResponseEntity<String> intakePackage(@RequestBody PackageDTO pkg) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonMessage = objectMapper.writeValueAsString(pkg);
            rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, jsonMessage);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error converting to JSON");
        }

        return ResponseEntity.ok("Package accepted and sent to queue.");
    }
}
