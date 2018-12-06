
package application.services;


import application.models.BalanceGeneral;

import com.cloudant.client.api.Database;
import com.cloudant.client.api.query.QueryBuilder;
import com.cloudant.client.api.query.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import static com.cloudant.client.api.query.Expression.eq;

@Service
public class BalanceGeneralService {
    private final Database db;

    @Autowired
    public BalanceGeneralService(Database db) {
        this.db = db;
    }

    public void save(BalanceGeneral balanceGeneral){
        db.save(balanceGeneral);
    }


    public BalanceGeneral obtenerBalance(String id) throws Exception {

        QueryResult<BalanceGeneral> queryVentas = db.query(new QueryBuilder(eq("_id", id)).build(), BalanceGeneral.class);
        List<BalanceGeneral> ventasRegistrados =  queryVentas.getDocs();
        if(ventasRegistrados.size()==0)
            throw new Exception();
        else
            return ventasRegistrados.get(0);
    }

}
