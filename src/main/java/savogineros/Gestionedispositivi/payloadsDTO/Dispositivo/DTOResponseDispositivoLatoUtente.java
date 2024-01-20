package savogineros.Gestionedispositivi.payloadsDTO.Dispositivo;

import savogineros.Gestionedispositivi.entities.TipoDispositivo;

import java.util.UUID;

public record DTOResponseDispositivoLatoUtente(UUID id, TipoDispositivo tipoDispositivo) {
}
