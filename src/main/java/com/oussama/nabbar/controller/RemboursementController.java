package com.oussama.nabbar.controller;


import com.oussama.nabbar.dto.RemboursementDTO;
import com.oussama.nabbar.service.RemboursementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/remboursements")
@Tag(name = "Remboursement Management", description = "Operations for managing remboursements")
public class RemboursementController {

    @Autowired
    private RemboursementService remboursementService;

    @GetMapping
    public List<RemboursementDTO> getAllRemboursements() {
        return remboursementService.getAllRemboursements();
    }

    @GetMapping("/{id}")
    public RemboursementDTO getRemboursementById(@PathVariable Long id) {
        return remboursementService.getRemboursementById(id);
    }

    @PostMapping
    public RemboursementDTO createRemboursement(@RequestBody RemboursementDTO dto) {
        return remboursementService.saveRemboursement(dto);
    }

    @PutMapping("/{id}")
    public RemboursementDTO updateRemboursement(@PathVariable Long id, @RequestBody RemboursementDTO dto) {
        dto.setId(id);
        return remboursementService.updateRemboursement(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteRemboursement(@PathVariable Long id) {
        remboursementService.deleteRemboursement(id);
    }
}
