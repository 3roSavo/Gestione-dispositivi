package savogineros.Gestionedispositivi.payloadsDTO;

import java.util.UUID;

public record NewUtenteResponseDTO(UUID id, String username, String nome, String cognome, String email) {
}
// I record dispongono in automatico di tutti i getter, MA non dei setter
// Dato che non abbiamo i setter il costruttore con tutti gli argomenti è incluso, quindi non devo
// crearmi a mano l'oggetto nel controller
// Ogni parametro del record corrisponde in automatico a un attributo