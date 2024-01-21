package savogineros.Gestionedispositivi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import savogineros.Gestionedispositivi.entities.Utente;
import savogineros.Gestionedispositivi.payloadsDTO.Dispositivo.DTOResponseDispositivoLatoUtente;
import savogineros.Gestionedispositivi.payloadsDTO.Utente.NewUtenteRequestDTO;
import savogineros.Gestionedispositivi.payloadsDTO.Utente.DTOResponseUtenteLatoUtente;
import savogineros.Gestionedispositivi.services.UtentiService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/utenti")
public class UtentiController {
    @Autowired
    private UtentiService utentiService;
//----------------------------------------------------------------------------------

    // GET - tutti gli utenti
    // URL http://localhost:3001/utenti
    @GetMapping("")
    public Page<DTOResponseUtenteLatoUtente> getUtenti(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "userName") String sort) {
        return utentiService.getAllUsers(page,size, sort);
    }

    // POST - Aggiungi un utente
    // URL http://localhost:3001/utenti     + (body)
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public DTOResponseUtenteLatoUtente creaUtente(@RequestBody NewUtenteRequestDTO utente) {
        return utentiService.salvaUtente(utente);
                // Anche qui utilizzo i DTO per personalizzare la risposta
                // Mi esplode la testa ma dovrebbe funzionare :(
    }

    // GET - Ricerca specifico Utente
    // URL http://localhost:3001/utenti/{idUtente}
    @GetMapping("/{idUtente}")
    public DTOResponseUtenteLatoUtente getUtenteById(@PathVariable UUID idUtente) {  // se non sbaglio il nome della variabile DEVE essere
        return utentiService.getUtenteById(idUtente);           // uguale a quella dell' URL. Quindi anche nella PUT
    }

    // PUT - Modifica Utente dato id e payload
    // URL http://localhost:3001/utenti/{idUtente}     + (body)
    @PutMapping("/{idUtente}")
    public DTOResponseUtenteLatoUtente modificaUtente(@PathVariable UUID idUtente, @RequestBody NewUtenteRequestDTO richiestaUtente) {
        // Mi vengono in mente due modi, o mi inietto in questa classe la repo UtentiDao così da utilizzare un'altra save()
        // Oppure mi faccio un altro metodo specifico per gli Update nel service
        // Seguiamo la seconda opzione
        return utentiService.modificaUtente(idUtente,richiestaUtente);
    }

    // DELETE - Elimina un utente dato l'id
    // URL http://localhost:3001/utenti/{idUtente}
    @DeleteMapping("{idUtente}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminaUtente(@PathVariable UUID idUtente) {
        utentiService.eliminaUtente(idUtente);
    }





}



















