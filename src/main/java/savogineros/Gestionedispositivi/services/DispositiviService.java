package savogineros.Gestionedispositivi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import savogineros.Gestionedispositivi.entities.Dispositivo;
import savogineros.Gestionedispositivi.exceptions.NotFoundException;
import savogineros.Gestionedispositivi.payloadsDTO.Dispositivo.DTOResponseDispositivoLatoDispositivo;
import savogineros.Gestionedispositivi.payloadsDTO.Dispositivo.DTOResponseDispositivoLatoUtente;
import savogineros.Gestionedispositivi.payloadsDTO.Dispositivo.NewDispositivoRequestDTO;
import savogineros.Gestionedispositivi.payloadsDTO.Utente.DTOResponseUtenteLatoDispositivo;
import savogineros.Gestionedispositivi.repositories.DispositiviDAO;

import java.util.Optional;
import java.util.UUID;

@Service
public class DispositiviService {
    @Autowired
    private DispositiviDAO dispositiviDAO;

    // GET -> getAllDispositivi
    /*public Page<Dispositivo> getAllDispositivi(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return dispositiviDAO.findAll(pageable);
    }*/

    // Prova con response di tipo DTO
    public Page<DTOResponseDispositivoLatoDispositivo> getAllDispositivi(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Dispositivo> dispositivoPage = dispositiviDAO.findAll(pageable);
        return dispositivoPage.map(
                dispositivo -> {
                    DTOResponseUtenteLatoDispositivo utenteAssociato;
                    if (dispositivo.getUtente() != null) {
                        utenteAssociato = new DTOResponseUtenteLatoDispositivo(
                    dispositivo.getUtente().getId(),
                    dispositivo.getUtente().getUserName());
                    } else {
                        utenteAssociato = null;
                    }
                    // Perché se un dispositivo non ha un utente da errore, quindi mettiamo null

            return new DTOResponseDispositivoLatoDispositivo(
                    dispositivo.getId(),
                    dispositivo.getTipoDispositivo(),
                    utenteAssociato
            );
                }
        );
    }

    // POST -> save
    public Dispositivo salvaDispositivo(NewDispositivoRequestDTO richiestaDispositivo) {
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setTipoDispositivo(richiestaDispositivo.tipoDispositivo());
        dispositivo.setUtente(richiestaDispositivo.utente());
        return dispositiviDAO.save(dispositivo);
    }

    // GET Ricerca specifico Dispositivo con id
    public Dispositivo getDispositivoById(UUID idDispositivo) {
        Optional<Dispositivo> dispositivo = dispositiviDAO.findById(idDispositivo);
        if (dispositivo.isPresent()) {
            return dispositivo.get();
        } else {
            throw new NotFoundException(idDispositivo);
        }
    }

    // PUT Modifica un Dispositivo, dato id e corpo della richiesta
    public Dispositivo modificaDispositivo(UUID idDispositivo, NewDispositivoRequestDTO richiestaDispositivo) {
        Dispositivo dispositivo = getDispositivoById(idDispositivo);
        dispositivo.setTipoDispositivo(richiestaDispositivo.tipoDispositivo());
        dispositivo.setUtente(richiestaDispositivo.utente());
        return dispositiviDAO.save(dispositivo); // ricordati che la save fa da creazione o modifica nel caso trovi già un elemento con lo stesso id
    }

    // DELETE - Elimina Dispositivo, dato id
    public void eliminaDispositivo(UUID idDispositivo) {
        Dispositivo dispositivo = getDispositivoById(idDispositivo);
        dispositiviDAO.delete(dispositivo);
    }


}
