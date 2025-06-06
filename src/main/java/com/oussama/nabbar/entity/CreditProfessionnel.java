package com.oussama.nabbar.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("PROFESSIONNEL")
public class CreditProfessionnel extends Credit {

    private String motif;
    private String raisonSociale;

}
