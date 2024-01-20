package savogineros.Gestionedispositivi.payloadsDTO.Dispositivo;

import savogineros.Gestionedispositivi.entities.TipoDispositivo;
import savogineros.Gestionedispositivi.entities.Utente;

public record NewDispositivoRequestDTO(TipoDispositivo tipoDispositivo, Utente utente) {
}
