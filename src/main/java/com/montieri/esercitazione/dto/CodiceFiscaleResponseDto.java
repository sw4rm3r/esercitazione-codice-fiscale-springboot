package com.montieri.esercitazione.dto;

public class CodiceFiscaleResponseDto {
    private String dataDiNascita;
    private int eta;

    public CodiceFiscaleResponseDto(String dataDiNascita, int eta) {
        this.dataDiNascita = dataDiNascita;
        this.eta = eta;
    }

    public String getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(String dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }
}
