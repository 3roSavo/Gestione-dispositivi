package savogineros.Gestionedispositivi.payloadsDTO.Dispositivo;

import savogineros.Gestionedispositivi.entities.StatoDispositivo;

import java.util.UUID;

public record DTOResponseDispositivoLatoUtente(UUID id, StatoDispositivo statoDispositivo) {
}
