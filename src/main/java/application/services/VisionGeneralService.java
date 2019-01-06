package application.services;

import application.models.Empresa;
import application.models.VisionGeneral;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.query.QueryBuilder;
import com.cloudant.client.api.query.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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
        else {
            visionGeneralList.sort(Comparator.comparing(VisionGeneral::getPorcentajeDeMercado));
            int puntaje=2*visionGeneralList.size();
            for(int i=visionGeneralList.size()-1;i>=0;i--) {
                if (i > 0 && visionGeneralList.get(i).getPorcentajeDeMercado() != visionGeneralList.get(i - 1).getPorcentajeDeMercado()) {
                    visionGeneralList.get(i).setPuntajeMercado(puntaje);
                    puntaje--;
                    puntaje--;
                } else {
                    visionGeneralList.get(i).setPuntajeMercado(puntaje);
                }
            }
                visionGeneralList.sort(Comparator.comparing(VisionGeneral::getBeneficio));
                puntaje=2*visionGeneralList.size();
                for(int j=visionGeneralList.size()-1;j>=0;j--){
                    if(j>0 && visionGeneralList.get(j).getBeneficio()!=visionGeneralList.get(j-1).getBeneficio()) {
                        visionGeneralList.get(j).setPuntajeBeneficio(puntaje);
                        puntaje--;
                        puntaje--;
                    }
                    else{
                        visionGeneralList.get(j).setPuntajeBeneficio(puntaje);
                    }
                }
            visionGeneralList.sort(Comparator.comparing(VisionGeneral::getNombreEmpresa));

            return visionGeneralList;
        }
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
