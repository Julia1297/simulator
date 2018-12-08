package application.services;

import application.models.VentasIndustria;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.query.QueryBuilder;
import com.cloudant.client.api.query.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.cloudant.client.api.query.Expression.eq;

@Service
public class VentasIndustriaService {

    private final Database db;

    @Autowired
    public VentasIndustriaService(Database db) {

        this.db = db;
    }
    public VentasIndustria obtenerVentasIndustria(String id) throws Exception {

        QueryResult<VentasIndustria> queryVentasIndustria = db.query(new QueryBuilder(eq("_id", id)).build(), VentasIndustria.class);
        List<VentasIndustria> ventasIndustriaList =  queryVentasIndustria.getDocs();
        if(ventasIndustriaList.size()==0)
            throw new Exception();
        else
            return ventasIndustriaList.get(0);
    }


    public void save(VentasIndustria ventasIndustria ){

        db.save(ventasIndustria);
    }
    public void update(VentasIndustria ventasIndustria ){

        db.update(ventasIndustria);
    }
}
