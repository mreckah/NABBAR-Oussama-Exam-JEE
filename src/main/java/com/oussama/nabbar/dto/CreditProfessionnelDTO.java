package com.oussama.nabbar.dto;

import lombok.Data;

@Data
public class CreditProfessionnelDTO extends CreditDTO {
    private String motif;
    private String raisonSociale;
}
