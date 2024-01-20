package savogineros.Gestionedispositivi.payloadsDTO.Dispositivo;

import lombok.NoArgsConstructor;
import savogineros.Gestionedispositivi.entities.TipoDispositivo;
import savogineros.Gestionedispositivi.payloadsDTO.Utente.DTOResponseUtenteLatoDispositivo;

import java.util.UUID;
public record DTOResponseDispositivoLatoDispositivo(UUID id, TipoDispositivo tipoDispositivo, DTOResponseUtenteLatoDispositivo utente_associato) {
}
