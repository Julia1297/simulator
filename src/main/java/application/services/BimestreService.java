package application.services;


import application.exception.BimestrenameNotFoundException;
import application.models.Bimestre;

import com.cloudant.client.api.Database;
import com.cloudant.client.api.query.QueryBuilder;
import com.cloudant.client.api.query.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import static com.cloudant.client.api.query.Expression.eq;

@Service
public class BimestreService {



    private final Database db;

    @Autowired
    public BimestreService(Database db) {
        this.db = db;
    }


    public Bimestre obtenerBimestre(String id) throws BimestrenameNotFoundException {

        QueryResult<Bimestre> queryBimestre = db.query(new QueryBuilder(eq("_id", id)).build(), Bimestre.class);
        List<Bimestre> bimestresRegistrados =  queryBimestre.getDocs();
        if(bimestresRegistrados.size()==0)
            throw new BimestrenameNotFoundException();
        else
            return bimestresRegistrados.get(0);
    }


    public void save(Bimestre bimestre){
         db.save(bimestre);
    }
    
}
