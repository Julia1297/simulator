package application.services;

import application.models.Empresa;
import application.models.VisionGeneral;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.query.QueryBuilder;
import com.cloudant.client.api.query.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.cloudant.client.api.query.Expression.eq;
import static com.cloudant.client.api.query.Operation.and;

@Service
public class VisionGeneralService {

    private final Database db;

    @Autowired
    public VisionGeneralService(Database db) {
        this.db = db;
    }
    public VisionGeneral obtenerVisionGeneral(String id) throws Exception {

        QueryResult<VisionGeneral> queryVisionGeneral = db.query(new QueryBuilder(eq("_id", id)).build(), VisionGeneral.class);
        List<VisionGeneral> visionGeneralList =  queryVisionGeneral.getDocs();
        if(visionGeneralList.size()==0)
            throw new Exception();
        else
            return visionGeneralList.get(0);
    }

    public List<VisionGeneral> listarVisionesGeneralesJuegoNumeroBimestre(String codigo, int numero){
        QueryResult<VisionGeneral> queryResult = db.query(new QueryBuilder(and(eq("codigoVision", codigo),eq("numeroVision",numero))).build(), VisionGeneral.class);
        List<VisionGeneral> visionGeneralList =  queryResult.getDocs();
        if(visionGeneralList.size()==0)
            return null;
        else
            return  visionGeneralList;

    }

    public void actualizarPorcentajesMercado(String codigo, int numero, List<Empresa> empresaList){
        QueryResult<VisionGeneral> queryResult = db.query(new QueryBuilder(and(eq("codigoVision", codigo),eq("numeroVision",numero))).build(), VisionGeneral.class);
        List<VisionGeneral> visionGeneralList =  queryResult.getDocs();
        for(int i=0;i<empresaList.size();i++){
            for(int j=0;j<visionGeneralList.size();j++){
                if((empresaList.get(i).getNombre()).equals(visionGeneralList.get(j).getNombreEmpresa())){
                    visionGeneralList.get(j).setPorcentajeDeMercado(empresaList.get(i).getPorcentajeDeMercado());
                    db.update(visionGeneralList.get(j));
                }
            }
        }

    }

    public void save(VisionGeneral visionGeneral ){

        db.save(visionGeneral);
    }
    public  void update(VisionGeneral visionGeneral){
        db.update(visionGeneral);
    }
}
