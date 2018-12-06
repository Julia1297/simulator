package application.services;

import application.exception.EmpresaNotFoundException;
import application.exception.JuegoNotFoundException;
import application.models.Empresa;
import application.models.Juego;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.query.QueryBuilder;
import com.cloudant.client.api.query.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.cloudant.client.api.query.Expression.eq;

@Service
public class JuegoService {


    private final Database db;

    @Autowired
    public JuegoService(Database db) {

        this.db = db;
    }


    public Juego obtenerJuego(String id) throws JuegoNotFoundException {

        QueryResult<Juego> queryJuego = db.query(new QueryBuilder(eq("_id", id)).build(), Juego.class);
        List<Juego> juegosRegistrados = queryJuego.getDocs();
        if (juegosRegistrados.size() == 0)
            throw new JuegoNotFoundException();
        else
            return juegosRegistrados.get(0);
    }


    public void save(Juego juego) {

        db.save(juego);
    }

}
