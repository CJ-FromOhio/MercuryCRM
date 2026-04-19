package com.tropia.mercuryapp.controllers;

import com.tropia.mercuryapp.dto.Company.CreateCompanyDto;
import com.tropia.mercuryapp.dto.Company.ReadCompanyDto;
import com.tropia.mercuryapp.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping("/{directorId}")
    public ResponseEntity<ReadCompanyDto> create (@RequestBody CreateCompanyDto dto,
                                                  @PathVariable("directorId") Long directorId) {
        return ResponseEntity.ok(companyService.create(dto, directorId));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReadCompanyDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.findById(id));
    }
}
