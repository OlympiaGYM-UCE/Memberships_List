package com.gym.olympia.repository;


import com.gym.olympia.entity.DTO.MembershipInfoDTO;
import com.gym.olympia.entity.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {
    List<Membership> findByClienteId(Long clienteId);

    @Query(value = """
        SELECT c.nombre, c.apellido,c.telefono, m.dateend 
        FROM memberships m 
        JOIN clientes c ON m.cliente_id = c.id 
        WHERE c.cedula = :cedula
        """, nativeQuery = true)
    List<Object[]> findMembershipsByCedula(String cedula);

}