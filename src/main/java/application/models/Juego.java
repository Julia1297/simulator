package application.models;

import java.util.List;

public class Juego {
    private String _id = null;
    private String _rev = null;

    private String empresa1_id;
    private String empresa2_id;
    private String empresa3_id;
    private String empresa4_id;
    private String empresa5_id;
    private  int cantidadEmpresa=1;


    //
    private int mercado=2675;
    private int mercadoDesatendido=175;
    private int porcentajeMercadoDesatendido;


    private String moderador_id;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_rev() {
        return _rev;
    }

    public void set_rev(String _rev) {
        this._rev = _rev;
    }

    public String getEmpresa1_id() {
        return empresa1_id;
    }

    public void setEmpresa1_id(String empresa1_id) {
        this.empresa1_id = empresa1_id;
    }

    public String getEmpresa2_id() {
        return empresa2_id;
    }

    public void setEmpresa2_id(String empresa2_id) {
        this.empresa2_id = empresa2_id;
    }

    public String getEmpresa3_id() {
        return empresa3_id;
    }

    public void setEmpresa3_id(String empresa3_id) {
        this.empresa3_id = empresa3_id;
    }

    public String getEmpresa4_id() {
        return empresa4_id;
    }

    public void setEmpresa4_id(String empresa4_id) {
        this.empresa4_id = empresa4_id;
    }

    public String getEmpresa5_id() {
        return empresa5_id;
    }

    public void setEmpresa5_id(String empresa5_id) {
        this.empresa5_id = empresa5_id;
    }

    public String getModerador_id() {
        return moderador_id;
    }

    public void setModerador_id(String moderador_id) {
        this.moderador_id = moderador_id;
    }

    public int getCantidadEmpresa() {
        return cantidadEmpresa;
    }

    public void setCantidadEmpresa(int cantidadEmpresa) {
        this.cantidadEmpresa = cantidadEmpresa;
    }


    public int getMercadoDesatendido() {
        return mercadoDesatendido;
    }

    public void setMercadoDesatendido(int mercadoDesatendido) {

        this.mercadoDesatendido = mercadoDesatendido;
    }



    public  void calcular(List<Empresa> empresaList){
/*        this.mercadoCubierto=0;
        System.out.println();
        System.out.println(empresaList.size());
        for(int i=0;i<empresaList.size();i++){
            this.mercadoCubierto=this.mercadoCubierto+empresaList.get(i).getCantidadVendida();
            System.out.println(empresaList.get(i).getCantidadVendida());
            System.out.println(empresaList.get(i).get_id());


        }
        System.out.println("mercado:");
        System.out.println(mercadoCubierto);*/
        this.mercado=0;
        for(int i=0;i<empresaList.size();i++){
            this.mercado=this.mercado+empresaList.get(i).getCantidadIdealTotal();
        }
        this.mercadoDesatendido=0;
        for(int i=0;i<empresaList.size();i++){
            this.mercadoDesatendido=this.mercadoDesatendido+empresaList.get(i).getCantidadRealVendida();
        }
        this.mercadoDesatendido=this.mercado- this.mercadoDesatendido;
        this.porcentajeMercadoDesatendido=(this.mercadoDesatendido/this.mercado)*100;

    }

    //segundo
    public  void calcularMercadoDesatendido(List<Empresa> empresaList){
        this.mercadoDesatendido=0;
        for(int i=0;i<empresaList.size();i++){
            this.mercadoDesatendido=this.mercadoDesatendido+empresaList.get(i).getCantidadRealVendida();
        }
        this.mercadoDesatendido=this.mercado- this.mercadoDesatendido;
        this.porcentajeMercadoDesatendido=(this.mercadoDesatendido/this.mercado)*100;
    }

    public int getMercado() {
        return mercado;
    }

    public void setMercado(int mercado) {
        this.mercado = mercado;
    }

    public int getPorcentajeMercadoDesatendido() {
        return porcentajeMercadoDesatendido;
    }

    public void setPorcentajeMercadoDesatendido(int porcentajeMercadoDesatendido) {
        this.porcentajeMercadoDesatendido = porcentajeMercadoDesatendido;
    }
}
