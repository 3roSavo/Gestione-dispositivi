package savogineros.Gestionedispositivi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Utente {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    private String userName;

    private String nome;

    private String cognome;

    private String email;

    @OneToMany(mappedBy = "utente")
    private List<Dispositivo> listaDispositivi;

    public Utente(String userName, String nome, String cognome, String email) {
        this.userName = userName;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
    }

}
