package savogineros.Gestionedispositivi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import savogineros.Gestionedispositivi.entities.Utente;

import java.util.UUID;

@Repository
public interface UtentiDAO extends JpaRepository<Utente, UUID> {

}
