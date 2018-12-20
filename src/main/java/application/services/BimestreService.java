package application.services;


import application.exception.BimestrenameNotFoundException;
import application.models.Bimestre;

import com.cloudant.client.api.Database;
import com.cloudant.client.api.query.QueryBuilder;
import com.cloudant.client.api.query.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.cloudant.client.api.query.Expression.eq;
import static com.cloudant.client.api.query.Operation.and;

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


    public List<Bimestre> obtenerBimestrePorNumeroYJuego(String codigo, int  numero) throws BimestrenameNotFoundException {

        QueryResult<Bimestre> queryBimestre = db.query(new QueryBuilder(and(eq("codigo", codigo),eq("numero",numero))).build(), Bimestre.class);
        List<Bimestre> bimestresRegistrados =  queryBimestre.getDocs();
        return  bimestresRegistrados;
    }


    public List <Double> promedioPrecioUnitarioEmpresas(String codigo) {
        QueryResult<Bimestre> queryResult = db.query(new QueryBuilder(eq("codigo", codigo)).build(), Bimestre.class);
        List<Bimestre> bimestres = queryResult.getDocs();
        int suma = 0;
        int cantidad = 0;
        List<Double> promedios = new ArrayList<>();

        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < bimestres.size(); j++) {
                if (bimestres.get(j).getNumero() == i) {
                    suma = suma + bimestres.get(j).getPrecioUnitario();
                    cantidad++;
                }
            }
            suma = suma / cantidad;
            promedios.add((double) suma);
            suma = 0;
            cantidad = 0;
        }
        return  promedios;
    }


    public void save(Bimestre bimestre){

        db.save(bimestre);
    }

}
