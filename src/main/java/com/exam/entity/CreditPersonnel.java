package com.exam.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@DiscriminatorValue("PERSONNEL")
public class CreditPersonnel extends Credit {

    private String motif;

}
