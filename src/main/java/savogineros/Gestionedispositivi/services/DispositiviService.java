package savogineros.Gestionedispositivi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import savogineros.Gestionedispositivi.repositories.DispositiviDAO;

@Service
public class DispositiviService {
    @Autowired
    private DispositiviDAO dispositiviDAO;


}
