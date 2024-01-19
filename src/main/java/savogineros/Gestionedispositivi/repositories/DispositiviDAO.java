package savogineros.Gestionedispositivi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import savogineros.Gestionedispositivi.entities.Dispositivo;

import java.util.UUID;

public interface DispositiviDAO extends JpaRepository<Dispositivo, UUID> {

}
