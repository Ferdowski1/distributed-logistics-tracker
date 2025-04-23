package com.logistics.tracker.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistics.tracker.model.PackageEntity;
import com.logistics.tracker.repository.PackageRepository;

@Component
public class PackageListener {

    private final PackageRepository packageRepository;

    public PackageListener(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    @RabbitListener(queues = "routing.queue")
    public void receivePackage(String message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            PackageEntity pkg = objectMapper.readValue(message, PackageEntity.class);

            // Simulate setting package status
            pkg.setStatus("In Transit");

            packageRepository.save(pkg);
            System.out.println("✅ Saved package to database: " + pkg.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
