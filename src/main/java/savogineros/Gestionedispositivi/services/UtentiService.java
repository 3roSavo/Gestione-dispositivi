package savogineros.Gestionedispositivi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import savogineros.Gestionedispositivi.entities.Utente;
import savogineros.Gestionedispositivi.repositories.UtentiDAO;

@Service
public class UtentiService {
    @Autowired
    private UtentiDAO utentiDAO;

    // GET -> getAllUsers
    public Page<Utente> getAllUsers(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return utentiDAO.findAll(pageable);
    }

}
