package com.tropia.mercuryapp.controllers;

import com.tropia.mercuryapp.dto.Client.CreateClientDto;
import com.tropia.mercuryapp.dto.Client.ReadClientDto;
import com.tropia.mercuryapp.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;


    @PostMapping("/{companyId}")
    public ResponseEntity<ReadClientDto> create(
            @RequestBody CreateClientDto dto,
            @PathVariable Long companyId) {
        return  ResponseEntity.ok(clientService.create(dto,companyId));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReadClientDto> get(@PathVariable Long id) {
        return  ResponseEntity.ok(clientService.findById(id));
    }
    @GetMapping()
    public ResponseEntity<List<ReadClientDto>> getAllByCompanyId(
            @RequestParam("companyId") Long companyId
    ) {
        return ResponseEntity.ok(clientService.getByCompanyId(companyId));
    }
}
