package com.gym.olympia.service;

import com.gym.olympia.entity.Office;
import com.gym.olympia.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfficeService {

    @Autowired
    OfficeRepository officeRepository;

    public Office guardar(Office empresa) {
        return officeRepository.save(empresa);
    }

    public Office actualizar(Long id, Office office) {
        return officeRepository.findById(id).map(existingOffice -> {
            existingOffice.setUbicacion(office.getUbicacion());
            existingOffice.setTelefono(office.getTelefono());
            existingOffice.setEmail(office.getEmail());
            existingOffice.setIdempresa(office.getIdempresa());
            return officeRepository.save(existingOffice);
        }).orElseThrow(() -> new RuntimeException("Office dont found with id " + id));
    }
}

