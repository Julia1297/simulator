package application.services;

import application.models.Ventas;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.query.QueryBuilder;
import com.cloudant.client.api.query.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.cloudant.client.api.query.Expression.eq;
import static com.cloudant.client.api.query.Operation.and;

@Service
public class VentasService {

    private final Database db;

    @Autowired
    public VentasService(Database db) {
        this.db = db;
    }


    public Ventas obtenerVentas(String id) throws Exception {

        QueryResult<Ventas> queryVentas = db.query(new QueryBuilder(eq("_id", id)).build(), Ventas.class);
        List<Ventas> ventasRegistrados =  queryVentas.getDocs();
        if(ventasRegistrados.size()==0)
            throw new Exception();
        else
            return ventasRegistrados.get(0);
    }

    public List<Ventas> obtenerVentasPorNumeroYJuego(String codigo, int  numero) {

        QueryResult<Ventas> queryResult = db.query(new QueryBuilder(and(eq("codigo", codigo),eq("numero",numero))).build(), Ventas.class);
        List<Ventas> ventasList =  queryResult.getDocs();
        return  ventasList;
    }

    public void save(Ventas ventas ){
        db.save(ventas);
    }
}
