package savogineros.Gestionedispositivi.payloadsDTO.Dispositivo;

import savogineros.Gestionedispositivi.entities.StatoDispositivo;
import savogineros.Gestionedispositivi.entities.Utente;

public record NewDispositivoRequestDTO(StatoDispositivo statoDispositivo, Utente utente) {
}
