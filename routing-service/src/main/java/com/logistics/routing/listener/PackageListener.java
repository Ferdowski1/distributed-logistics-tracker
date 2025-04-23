package com.logistics.routing.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistics.routing.dto.PackageDTO;

@Component
public class PackageListener {

    private final RabbitTemplate rabbitTemplate;

    public PackageListener(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "routing.queue")
    public void receivePackage(String message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            PackageDTO pkg = objectMapper.readValue(message, PackageDTO.class);

            System.out.println("📦 Routing package: " + pkg.getId());
            System.out.println("From: " + pkg.getOrigin() + " → To: " + pkg.getDestination());

            // Simulate routing logic
            String nextFacility = "New York Hub";
            System.out.println("Routing through: " + nextFacility);

            // Forward to tracker
            pkg.setDestination(nextFacility);  
            String updatedJson = objectMapper.writeValueAsString(pkg);
            rabbitTemplate.convertAndSend("routing.queue", updatedJson);

            System.out.println("📤 Sent to tracker via routing.queue");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
