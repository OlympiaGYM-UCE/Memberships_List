package com.gym.olympia.controller;


import com.gym.olympia.entity.Office;
import com.gym.olympia.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/office")
public class OfficeController {

    @Autowired
    OfficeService officeService;

    @PostMapping
    public Office crear(@RequestBody Office office) {
        return officeService.guardar(office);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Office> actualizar(@PathVariable Long id, @RequestBody Office office) {
        try {
            Office officeActualizada = officeService.actualizar(id, office);
            return ResponseEntity.ok(officeActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
