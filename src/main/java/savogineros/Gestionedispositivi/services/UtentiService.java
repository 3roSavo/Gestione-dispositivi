package savogineros.Gestionedispositivi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import savogineros.Gestionedispositivi.entities.Dispositivo;
import savogineros.Gestionedispositivi.entities.Utente;
import savogineros.Gestionedispositivi.exceptions.NotFoundException;
import savogineros.Gestionedispositivi.payloadsDTO.Dispositivo.DTOResponseDispositivoLatoUtente;
import savogineros.Gestionedispositivi.payloadsDTO.Utente.NewUtenteRequestDTO;
import savogineros.Gestionedispositivi.payloadsDTO.Utente.DTOResponseUtenteLatoUtente;
import savogineros.Gestionedispositivi.repositories.DispositiviDAO;
import savogineros.Gestionedispositivi.repositories.UtentiDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UtentiService {
    @Autowired
    private UtentiDAO utentiDAO;
    @Autowired
    private DispositiviDAO dispositiviDAO;

    // GET -> getAllUsers
    public Page<DTOResponseUtenteLatoUtente> getAllUsers(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Utente> listaUtenti = utentiDAO.findAll(pageable);
        return listaUtenti.map(utente -> {
        List<DTOResponseDispositivoLatoUtente> listaDispositivi = new ArrayList<>();
            utente.getListaDispositivi().forEach(dispositivo ->
                    listaDispositivi.add(new DTOResponseDispositivoLatoUtente(dispositivo.getId(),
                            dispositivo.getTipoDispositivo())));
            return new DTOResponseUtenteLatoUtente(
                    utente.getId(),
                    utente.getUserName(),
                    utente.getNome(),
                    utente.getCognome(),
                    utente.getEmail(),
                    listaDispositivi
            );
            // Finalmente abbiamo usato due DTO per personalizzare la risposta in JSON senza creare StackOverflow
            // Ho modificato col mio UtenteDTO la List<Dispositivo> in List<DTOResponseDispositivoLatoUtente> che tralascia l'utente
        });
    }

    // POST -> save
    public DTOResponseUtenteLatoUtente salvaUtente(NewUtenteRequestDTO utenteRequestDTO) {

        Utente utente = new Utente(
                utenteRequestDTO.userName(),
                utenteRequestDTO.nome(),
                utenteRequestDTO.cognome(),
                utenteRequestDTO.email()
        ); // il costruttore non accetta la lista, quindi la setto dopo la creazione
        utente.getListaDispositivi().addAll(utenteRequestDTO.listaDispositivi());

        utentiDAO.save(utente);

        /*utente.setUserName(utenteRequestDTO.userName());
        utente.setNome(utenteRequestDTO.nome());
        utente.setCognome(utenteRequestDTO.cognome());
        utente.setEmail(utenteRequestDTO.email());
        utente.setListaDispositivi(utenteRequestDTO.listaDispositivi());*/

        List<DTOResponseDispositivoLatoUtente> listaDispositivi = utente.getListaDispositivi()
                .stream()
                .map(dispositivo ->
                        new DTOResponseDispositivoLatoUtente(
                                dispositivo.getId(),
                                dispositivo.getTipoDispositivo()))
                .toList();

        return new DTOResponseUtenteLatoUtente(
                utente.getId(),
                utente.getUserName(),
                utente.getNome(),
                utente.getCognome(),
                utente.getEmail(),
                listaDispositivi
        );
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

        utente.getListaDispositivi().clear();

        // Prima prova risoluzione problema aggiunta lista
        /*List<Dispositivo> listaDispositivi = new ArrayList<>(richiestaUtente.listaDispositivi()
                .stream()
                .map(dispositivo -> dispositiviDAO
                        .findById(dispositivo.getId()).orElseThrow(() -> new NotFoundException(dispositivo.getId())))
                .toList());*/


        // FORSE IL PROBLEMA È CHE DEVO USARE IL DAO DEL DISPOSITIVO PER SALVARE UNO PIU' DISPOSITIVI NELL'UTENTE
        // OK RISOLTO PROPRIO IN QUESTO MODO MA E' IL METODO CORRETTO???
        richiestaUtente.listaDispositivi().forEach(dispositivo -> {
            Optional<Dispositivo> dispositivoOptional =  dispositiviDAO.findById(dispositivo.getId());
            // Controlliamo prima che la lista contenga id validi
            if (dispositivoOptional.isPresent()) {
                dispositivoOptional.get().setUtente(utente);
                dispositiviDAO.save(dispositivoOptional.get());
            } else {
                throw new NotFoundException(dispositivo.getId());
            }
        });


        // Seconda prova risoluzione aggiunta lista
        //utente.setListaDispositivi(richiestaUtente.listaDispositivi());
        //listaDispositivi.forEach(dispositivo -> utente.getListaDispositivi().add(dispositivo));

        //utente.getListaDispositivi().addAll(listaDispositivi);
        //utente.setListaDispositivi(listaDispositivi);
        utente.getListaDispositivi().forEach(dispositivo -> System.out.println(dispositivo));

        // non mi aggiorna la lista dispositivi
        return utentiDAO.save(utente); // ricordati che la save fa da creazione o modifica nel caso trovi già un elemento con lo stesso id
    }

    // DELETE Elimina utente, dato id
    public void eliminaUtente(UUID idUtente) {
        Utente utente = getUtenteById(idUtente);
        utentiDAO.delete(utente);
    }
}