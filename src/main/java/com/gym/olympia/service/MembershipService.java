package com.gym.olympia.service;


import com.gym.olympia.entity.DTO.MembershipInfoDTO;
import com.gym.olympia.entity.Membership;
import com.gym.olympia.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private RestTemplate restTemplate;


    public List<Membership> getAllMemberships() {
        return membershipRepository.findAll();
    }

    public Membership createMembership(Membership membership) {
        return membershipRepository.save(membership);
    }

    public Membership updateMembership(Long id, Membership updatedMembership) {
        return membershipRepository.findById(id).map(membership -> {
            membership.setClienteId(updatedMembership.getClienteId());
            membership.setDateStart(updatedMembership.getDateStart());
            membership.setDateEnd(updatedMembership.getDateEnd());
            return membershipRepository.save(membership);
        }).orElse(null);
    }

    public List<String> getMembershipsByCedula(String cedula) {
        List<Object[]> results = membershipRepository.findMembershipsByCedula(cedula);

        // Transformamos los resultados en Strings legibles
        List<String> memberships = results.stream().map(obj -> {
            String nombre = (String) obj[0];
            String apellido = (String) obj[1];
            String dateEnd = obj[3].toString();
            return "Cliente: " + nombre + " " + apellido + " | Membresía vence: " + dateEnd;
        }).collect(Collectors.toList());

        // Si existe al menos un registro, extraemos el teléfono del primer elemento y enviamos la notificación
        if (!results.isEmpty()) {
            String telefono = (String) results.get(0)[2];

            // Obtener la fecha actual, puedes formatearla según tus necesidades
            LocalDate fechaActual = LocalDate.now();
            // Ejemplo con formateo: DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            // String fechaFormateada = fechaActual.format(formatter);

            // Concatenar la fecha en el mensaje
            String mensaje = "Gracias por venir el día de hoy " + fechaActual + ", ¡Recuerda que cada día hará la diferencia para tu cambio sigue así.";
            sendWhatsappNotification(telefono, mensaje);
        }

        return memberships;
    }

    @Retryable(
            value = { Exception.class },   // excepciones que se considerarán para reintentar
            maxAttempts = 5,               // número máximo de intentos
            backoff = @Backoff(delay = 2000) // espera de 2000ms entre intentos
    )
    private void sendWhatsappNotification(String to, String message) {
        String url = "http://54.152.113.164:9090/send-whatsapp";

        // Construir el payload
        Map<String, String> payload = new HashMap<>();
        payload.put("to", to);
        payload.put("message", message);

        // Configurar headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(payload, headers);

        try {
            restTemplate.postForEntity(url, request, String.class);
        } catch (Exception e) {
            // Manejo de errores, por ejemplo, registrar el error para seguimiento
            e.printStackTrace();
        }
    }


}
