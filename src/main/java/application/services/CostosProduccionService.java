package application.services;


import application.models.CostosProduccion;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.query.QueryBuilder;
import com.cloudant.client.api.query.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.cloudant.client.api.query.Expression.eq;

@Service
public class CostosProduccionService {


    private final Database db;

    @Autowired
    public CostosProduccionService(Database db) {
        this.db = db;
    }


    public CostosProduccion obtenerCostoProduccion(String id) throws Exception {

        QueryResult<CostosProduccion> queryCostos = db.query(new QueryBuilder(eq("_id", id)).build(), CostosProduccion.class);
        List<CostosProduccion> costosProduccionRegistrados =  queryCostos.getDocs();
        if(costosProduccionRegistrados.size()==0)
            throw new Exception();
        else
            return costosProduccionRegistrados.get(0);
    }


    public void save(CostosProduccion costosProduccion ){
        db.save(costosProduccion);
    }
}
