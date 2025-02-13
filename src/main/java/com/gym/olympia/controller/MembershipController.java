package com.gym.olympia.controller;

import com.gym.olympia.entity.DTO.MembershipInfoDTO;
import com.gym.olympia.entity.Membership;
import com.gym.olympia.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/memberships")
public class MembershipController {

    @Autowired
    private MembershipService membershipService;

    @GetMapping
    public List<Membership> getAllMemberships() {
        return membershipService.getAllMemberships();
    }

    @PostMapping
    public Membership createMembership(@RequestBody Membership membership) {
        return membershipService.createMembership(membership);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Membership> updateMembership(@PathVariable Long id, @RequestBody Membership membership) {
        Membership updated = membershipService.updateMembership(id, membership);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @GetMapping("/cedula/{cedula}")
    public List<String> getMembershipsByCedula(@PathVariable String cedula) {
        return membershipService.getMembershipsByCedula(cedula);
    }


}