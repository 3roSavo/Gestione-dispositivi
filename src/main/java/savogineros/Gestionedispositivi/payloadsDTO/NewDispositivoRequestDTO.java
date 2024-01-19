package savogineros.Gestionedispositivi.payloadsDTO;

import savogineros.Gestionedispositivi.entities.TipoDispositivo;
import savogineros.Gestionedispositivi.entities.Utente;

public record NewDispositivoRequestDTO(TipoDispositivo tipoDispositivo, Utente utente) {
}
