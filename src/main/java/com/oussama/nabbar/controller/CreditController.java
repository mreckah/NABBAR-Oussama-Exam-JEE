package com.oussama.nabbar.controller;

import com.oussama.nabbar.dto.CreditDTO;
import com.oussama.nabbar.service.CreditService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credits")
@Tag(name = "Gestion des crédits", description = "API pour gérer les crédits bancaires")
@Validated
public class CreditController {

    private final CreditService creditService;

    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @Operation(summary = "Lister tous les crédits")
    @GetMapping
    public ResponseEntity<List<CreditDTO>> getAllCredits() {
        List<CreditDTO> credits = creditService.getAllCredits();
        return ResponseEntity.ok(credits);
    }

    @Operation(summary = "Obtenir un crédit par son ID")
    @GetMapping("/{id}")
    public ResponseEntity<CreditDTO> getCreditById(@PathVariable Long id) {
        CreditDTO credit = creditService.getCreditById(id);
        return ResponseEntity.ok(credit);
    }

    @Operation(summary = "Créer un nouveau crédit")
    @PostMapping
    public ResponseEntity<CreditDTO> createCredit(@Valid @RequestBody CreditDTO creditDTO) {
        CreditDTO createdCredit = creditService.createCredit(creditDTO);
        return new ResponseEntity<>(createdCredit, HttpStatus.CREATED);
    }

    @Operation(summary = "Modifier un crédit existant")
    @PutMapping("/{id}")
    public ResponseEntity<CreditDTO> updateCredit(@PathVariable Long id, @Valid @RequestBody CreditDTO creditDTO) {
        CreditDTO updatedCredit = creditService.updateCredit(id, creditDTO);
        return ResponseEntity.ok(updatedCredit);
    }

    @Operation(summary = "Supprimer un crédit par son ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCredit(@PathVariable Long id) {
        creditService.deleteCredit(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Lister les crédits d'un client donné")
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<CreditDTO>> getCreditsByClient(@PathVariable Long clientId) {
        List<CreditDTO> credits = creditService.getCreditsByClientId(clientId);
        return ResponseEntity.ok(credits);
    }
}
