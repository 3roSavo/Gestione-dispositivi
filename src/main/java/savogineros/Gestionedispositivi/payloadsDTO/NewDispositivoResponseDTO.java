package savogineros.Gestionedispositivi.payloadsDTO;

import savogineros.Gestionedispositivi.entities.TipoDispositivo;

import java.util.UUID;

public record NewDispositivoResponseDTO(UUID id, TipoDispositivo tipoDispositivo) {
}
