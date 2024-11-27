package com.montieri.esercitazione.controllers;

import com.montieri.esercitazione.dto.CodiceFiscaleResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
        import java.time.LocalDate;
import java.time.Period;

@RestController
@RequestMapping("/api/v1/codice-fiscale")
public class CodiceFiscaleController {

    @GetMapping("/{codiceFiscale}")
    @Operation(
            summary = "Ottieni i dettagli da un codice fiscale",
            description = "Restituisce la data di nascita e l'età calcolata a partire da un codice fiscale fornito."
    )
    public CodiceFiscaleResponseDto getDataNascitaEta(@PathVariable String codiceFiscale) {
        String dataNascita = estraiDataNascita(codiceFiscale);
        int eta = calcolaEta(dataNascita);
        return new CodiceFiscaleResponseDto(dataNascita, eta);
    }

    private String estraiDataNascita(String codiceFiscale) {
        String anno = codiceFiscale.substring(6, 8);
        String mese = codiceFiscale.substring(8, 9).toUpperCase();
        String giorno = codiceFiscale.substring(9, 11);

        String[] mesi = {"A", "B", "C", "D", "E", "H", "L", "M", "P", "R", "S", "T"};
        int meseNumero = 1;
        for (int i = 0; i < mesi.length; i++) {
            if (mesi[i].equals(mese)) {
                meseNumero = i + 1;
                break;
            }
        }

        int giornoInt = Integer.parseInt(giorno);
        if (giornoInt > 31) {
            giornoInt -= 40;
        }

        int annoInt = Integer.parseInt(anno);
        annoInt += (annoInt <= LocalDate.now().getYear() % 100) ? 2000 : 1900;

        return LocalDate.of(annoInt, meseNumero, giornoInt).toString();
    }

    private int calcolaEta(String dataNascita) {
        LocalDate data = LocalDate.parse(dataNascita);
        return Period.between(data, LocalDate.now()).getYears();
    }
}
