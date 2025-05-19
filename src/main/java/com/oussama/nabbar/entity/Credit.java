package com.oussama.nabbar.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_credit")
public abstract class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate dateDemande;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatutCredit statut;

    private LocalDate dateAcception;

    @NotNull
    @Min(value = 0, message = "Le montant doit être positif")
    private double montant;

    @NotNull
    @Min(value = 1, message = "La durée de remboursement doit être d'au moins 1 mois")
    private int dureeRemboursement; // en mois

    @NotNull
    @Min(value = 0, message = "Le taux d'intérêt doit être positif")
    private double tauxInteret;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "credit", cascade = CascadeType.ALL)
    private List<Remboursement> remboursements;

}
