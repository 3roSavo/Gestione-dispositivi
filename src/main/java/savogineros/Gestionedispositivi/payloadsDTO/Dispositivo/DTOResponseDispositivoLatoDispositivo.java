package savogineros.Gestionedispositivi.payloadsDTO.Dispositivo;

import savogineros.Gestionedispositivi.entities.StatoDispositivo;
import savogineros.Gestionedispositivi.payloadsDTO.Utente.DTOResponseUtenteLatoDispositivo;

import java.util.UUID;
public record DTOResponseDispositivoLatoDispositivo(UUID id, StatoDispositivo statoDispositivo, DTOResponseUtenteLatoDispositivo utente_associato) {
}
