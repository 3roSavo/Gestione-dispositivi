package savogineros.Gestionedispositivi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import savogineros.Gestionedispositivi.entities.Utente;
import savogineros.Gestionedispositivi.exceptions.NotFoundException;
import savogineros.Gestionedispositivi.payloadsDTO.NewUtenteRequestDTO;
import savogineros.Gestionedispositivi.repositories.UtentiDAO;

import java.util.Optional;
import java.util.UUID;

@Service
public class UtentiService {
    @Autowired
    private UtentiDAO utentiDAO;

    // GET -> getAllUsers
    public Page<Utente> getAllUsers(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return utentiDAO.findAll(pageable);
    }

    // POST -> save
    public Utente salvaUtente(NewUtenteRequestDTO utenteRequestDTO) {
        Utente utente = new Utente();
        utente.setUserName(utenteRequestDTO.userName());
        utente.setNome(utenteRequestDTO.nome());
        utente.setCognome(utenteRequestDTO.cognome());
        utente.setEmail(utenteRequestDTO.email());
        utente.setListaDispositivi(utenteRequestDTO.listaDispositivi());
        return utentiDAO.save(utente);
    }

    // GET Ricerca specifico utente con id
    public Utente getUtenteById(UUID idUtente) {
        Optional<Utente> utente = utentiDAO.findById(idUtente);
        if (utente.isPresent()) {
            return utente.get();
        } else {
            throw new NotFoundException(idUtente);
        }
    }

    // PUT Modifica un Utente, dato id e corpo della richiesta
    public Utente modificaUtente(UUID idUtente, NewUtenteRequestDTO richiestaUtente) {
        Utente utente = getUtenteById(idUtente);
        utente.setUserName(richiestaUtente.userName());
        utente.setNome(richiestaUtente.nome());
        utente.setCognome(richiestaUtente.cognome());
        utente.setEmail(richiestaUtente.email());
        utente.setListaDispositivi(richiestaUtente.listaDispositivi());
        return utentiDAO.save(utente); // ricordati che la save fa da creazione o modifica nel caso trovi già un elemento con lo stesso id
    }

    // DELETE Elimina utente, dato id
    public void eliminaUtente(UUID idUtente) {
        Utente utente = getUtenteById(idUtente);
        utentiDAO.delete(utente);
    }

}
