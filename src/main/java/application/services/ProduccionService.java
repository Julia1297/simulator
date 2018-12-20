package application.services;


import application.models.Produccion;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.query.QueryBuilder;
import com.cloudant.client.api.query.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List< Produccion> obtenerListaProduccionBimestre(String nombre) throws Exception {

        QueryResult<Produccion> queryProduccion = db.query(new QueryBuilder(eq("nombreEmpresaProduccion", nombre)).build(), Produccion.class);
        List<Produccion> produccionList =  queryProduccion.getDocs();
        return  produccionList;
    }
    public List <Double> sumatoriaProduccionEmpresas(String codigo){
        QueryResult<Produccion> queryResult =db.query(new QueryBuilder(eq("codigo",codigo)).build(),Produccion.class);
        List<Produccion> bimestres =queryResult.getDocs();
        int suma=0;
        List <Double> sumatoria= new ArrayList<>();

        for(int i=1;i<4;i++){
            for(int j=0;j<bimestres.size();j++){
                if(bimestres.get(j).getNumero()==i){
                    suma=suma+bimestres.get(j).getNumero()*50+850;
                }
            }
            sumatoria.add((double) suma);
            suma=0;
        }
        return  sumatoria;
    }


    public void save(Produccion produccion ){

        db.save(produccion);
    }

    public void update(Produccion produccion ){

        db.update(produccion);
    }
    
    
    
    
}
