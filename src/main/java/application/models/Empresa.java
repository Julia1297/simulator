package application.models;

import java.util.List;

public class Empresa {

    private String _id = null;
    private String _rev = null;

    private String nombre;

    private String user_id;
    private String bimestre1_id;
    private String bimestre2_id;
    private String bimestre3_id="y";
    private int numeroBimestre=1;
    private double cantidadVendida;
    private double cantidadVendidaAnterior=500;
    private double porcentajeDeMercado;
    private String codigo;





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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBimestre1_id() {
        return bimestre1_id;
    }

    public void setBimestre1_id(String bimestre1_id) {
        this.bimestre1_id = bimestre1_id;
    }

    public String getBimestre2_id() {
        return bimestre2_id;
    }

    public void setBimestre2_id(String bimestre2_id) {
        this.bimestre2_id = bimestre2_id;
    }

    public String getBimestre3_id() {
        return bimestre3_id;
    }

    public void setBimestre3_id(String bimestre3_id) {
        this.bimestre3_id = bimestre3_id;
    }

    public int getNumeroBimestre() {
        return numeroBimestre;
    }

    public void setNumeroBimestre(int numeroBimestre) {
        this.numeroBimestre = numeroBimestre;
    }

    public double getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(double cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    public double getPorcentajeDeMercado() {
        return porcentajeDeMercado;
    }

    public void setPorcentajeDeMercado(double porcentajeDeMercado) {
        this.porcentajeDeMercado = porcentajeDeMercado;
    }
    public void calcularCantidadVendida(List<Empresa> empresaList,int precioUnitario){
        double suma=0;
        for(int i=0;i<empresaList.size();i++){
            if(empresaList.get(i).get_id()!=this._id);
                suma=suma+empresaList.get(i).getCantidadVendidaAnterior();
        }
        suma=suma/2;
        this.cantidadVendida=((337.5-precioUnitario)/0.18)-suma;
        this.cantidadVendidaAnterior=this.cantidadVendida;
    }

    public double getCantidadVendidaAnterior() {
        return cantidadVendidaAnterior;
    }

    public void setCantidadVendidaAnterior(double cantidadVendidaAnterior) {
        this.cantidadVendidaAnterior = cantidadVendidaAnterior;
    }

    public void calcularPorcentajeMercado(int produccion, int inventarioUnidadesAnterior, double mercado){
        double res=produccion+inventarioUnidadesAnterior;
        res = Math.min(this.cantidadVendida, res);
        porcentajeDeMercado=res/mercado;


    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
