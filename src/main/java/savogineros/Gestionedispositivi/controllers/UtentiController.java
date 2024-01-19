package savogineros.Gestionedispositivi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import savogineros.Gestionedispositivi.entities.Utente;
import savogineros.Gestionedispositivi.services.UtentiService;

import java.util.List;

@RestController
@RequestMapping("/utenti")
public class UtentiController {
    @Autowired
    private UtentiService utentiService;

    // GET - tutti gli utenti
    // URL http://localhost:3001/utenti
    @GetMapping("")
    public Page<Utente> getUtenti(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "userName") String sort) {
        return utentiService.getAllUsers(page,size, sort);
    }

    // POST - Aggiungi un utente
    // URL http://localhost:3001/utenti
}
