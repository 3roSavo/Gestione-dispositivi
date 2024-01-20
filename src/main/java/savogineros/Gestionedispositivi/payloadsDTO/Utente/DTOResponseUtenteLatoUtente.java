package savogineros.Gestionedispositivi.payloadsDTO.Utente;

import savogineros.Gestionedispositivi.payloadsDTO.Dispositivo.DTOResponseDispositivoLatoUtente;

import java.util.List;
import java.util.UUID;

public record DTOResponseUtenteLatoUtente(UUID id, String username, String nome, String cognome, String email, List<DTOResponseDispositivoLatoUtente> listaDispositivi) {
}
// I record dispongono in automatico di tutti i getter, MA non dei setter
// Dato che non abbiamo i setter il costruttore con tutti gli argomenti è incluso, quindi non devo
// crearmi a mano l'oggetto nel controller
// Ogni parametro del record corrisponde in automatico a un attributo