package com.logistics.tracker.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.tracker.model.PackageEntity;
import com.logistics.tracker.repository.PackageRepository;

@RestController
@RequestMapping("/packages")
public class PackageController {

    private final PackageRepository packageRepository;

    public PackageController(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPackage(@PathVariable String id) {
        Optional<PackageEntity> result = packageRepository.findById(id);

        return result
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body("Package not found"));
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<?> completePackage(@PathVariable String id) {
        Optional<PackageEntity> result = packageRepository.findById(id);

        if (result.isEmpty()) {
            return ResponseEntity.status(404).body("Package not found");
        }

        PackageEntity pkg = result.get();
        pkg.setStatus("Delivered");
        packageRepository.save(pkg);

        return ResponseEntity.ok(pkg);
}

}
