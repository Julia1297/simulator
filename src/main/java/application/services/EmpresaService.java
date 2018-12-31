package application.services;

import application.exception.EmpresaNotFoundException;

import application.models.Empresa;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.query.QueryBuilder;
import com.cloudant.client.api.query.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.cloudant.client.api.query.Expression.eq;
import static com.cloudant.client.api.query.Operation.and;

@Service
public class EmpresaService {



    private final Database db;

    @Autowired
    public EmpresaService(Database db) {

        this.db = db;
    }


    public Empresa obtenerEmpresa(String id) throws EmpresaNotFoundException {

        QueryResult<Empresa> queryEmpresa = db.query(new QueryBuilder(eq("_id", id)).build(), Empresa.class);
        List<Empresa> empresasRegistrados =  queryEmpresa.getDocs();
        if(empresasRegistrados.size()==0)
            throw new EmpresaNotFoundException();
        else
            return empresasRegistrados.get(0);
    }


    public void save(Empresa empresa){

        db.save(empresa);
    }


    public List<Empresa> listarEmpresasPorCodigoJuego(String codigo){
        QueryResult<Empresa> queryResult = db.query(new QueryBuilder(eq("codigoJuego",codigo)).build(), Empresa.class);
        List<Empresa> estadoResultados =  queryResult.getDocs();
        if(estadoResultados.size()==0)
            return null;
        else
            return  estadoResultados;
    }
    public List<Empresa> listarEmpresasPorCodigoJuegoActualizandoDatos(String codigo, int mercado){
        QueryResult<Empresa> queryResult = db.query(new QueryBuilder(eq("codigoJuego",codigo)).build(), Empresa.class);
        List<Empresa> estadoResultados =  queryResult.getDocs();
        if(estadoResultados.size()==0)
            return null;
        else{
            for(int i=0;i<estadoResultados.size();i++)
            {
                estadoResultados.get(i).calcularPorcentajeMercado(mercado);
                db.update(estadoResultados.get(i));
            }
            return  estadoResultados;

        }
    }
    public  void update(Empresa empresa) {
        db.update(empresa);
    }

}
