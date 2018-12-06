package application.services;


import application.models.Produccion;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.query.QueryBuilder;
import com.cloudant.client.api.query.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.cloudant.client.api.query.Expression.eq;

@Service
public class ProduccionService {
    private final Database db;

    @Autowired
    public ProduccionService(Database db) {

        this.db = db;
    }
    public Produccion obtenerProduccion(String id) throws Exception {

        QueryResult<Produccion> queryProduccion = db.query(new QueryBuilder(eq("_id", id)).build(), Produccion.class);
        List<Produccion> produccionList =  queryProduccion.getDocs();
        if(produccionList.size()==0)
            throw new Exception();
        else
            return produccionList.get(0);
    }


    public void save(Produccion produccion ){

        db.save(produccion);
    }
    
    
    
    
    
}
