
package application.services;


import application.models.EstadoResultados;

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
public class EstadoResultadosService {
    private final Database db;

    @Autowired
    public EstadoResultadosService(Database db) {

        this.db = db;
    }

    public void save(EstadoResultados estadoResultados){

        db.save(estadoResultados);
    }
    public EstadoResultados obtenerEstadoResultado(String id) throws Exception {

        QueryResult<EstadoResultados> queryVentas = db.query(new QueryBuilder(eq("_id", id)).build(), EstadoResultados.class);
        List<EstadoResultados> ventasRegistrados =  queryVentas.getDocs();
        if(ventasRegistrados.size()==0)
            throw new Exception();
        else
            return ventasRegistrados.get(0);
    }
    public List<EstadoResultados> listarEstadosEmpresa(String empresa){
        QueryResult<EstadoResultados> queryResult = db.query(new QueryBuilder(eq("empresa", empresa)).build(), EstadoResultados.class);
        List<EstadoResultados> estadoResultados =  queryResult.getDocs();
        if(estadoResultados.size()==0)
            return null;
        else
            return  estadoResultados.subList(1,estadoResultados.size());
    }

    public List<EstadoResultados> obtenerEstadoResultaodsPorNumeroYJuego(String codigo, int  numero) {

        QueryResult<EstadoResultados> queryResult = db.query(new QueryBuilder(and(eq("codigo", codigo),eq("numero",numero))).build(), EstadoResultados.class);
        List<EstadoResultados> estadoResultadosList =  queryResult.getDocs();
        return  estadoResultadosList;
    }

    public List<Double> promedioUtilidadNetaEmpresas(String codigo) {
        QueryResult<EstadoResultados> queryResult = db.query(new QueryBuilder(eq("codigo", codigo)).build(), EstadoResultados.class);
        List<EstadoResultados> bimestres = queryResult.getDocs();
        int suma = 0;
        int cantidad = 0;
        List<Double> promedios = new ArrayList<>();

        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < bimestres.size(); j++) {
                if (bimestres.get(j).getNumero() == i) {
                    suma = suma + bimestres.get(j).getUtilidadNeta();
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
}
