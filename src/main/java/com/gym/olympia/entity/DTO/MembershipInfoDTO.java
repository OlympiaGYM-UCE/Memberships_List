package com.gym.olympia.entity.DTO;

import java.time.LocalDate;

public class MembershipInfoDTO {
    private String nombre;
    private String apellido;
    private LocalDate dateEnd;

    // Constructor
    public MembershipInfoDTO(String nombre, String apellido, LocalDate dateEnd) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dateEnd = dateEnd;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }
}
