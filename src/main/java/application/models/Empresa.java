package application.models;

import java.util.List;

public class Empresa {

    private String _id = null;
    private String _rev = null;

    private String nombre;

    private String user_id;
    private String bimestre1_id;
    private String bimestre2_id;
    private String bimestre3_id;
    private int numeroBimestre=1;
    private double cantidadVendida;
    private double cantidadVendidaAnterior=500;
    private int porcentajeDeMercado;
    private String codigoJuego;


    //nuevos
    private int cantidadIdeal=500;
    private int cantidadIdealTotal=535;
    private int produccion=500;
    private int cantidadRealVendida=500;

    private String estado="Jugando";




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

    public int getPorcentajeDeMercado() {
        return porcentajeDeMercado;
    }

    public void setPorcentajeDeMercado(int porcentajeDeMercado) {
        this.porcentajeDeMercado = porcentajeDeMercado;
    }
    public int obtenerPorcentaje(int investigacion){
        if(investigacion==0)
            return 0;
        if(investigacion==1500)
            return (int) (0.1*this.cantidadIdeal);
        if(investigacion==3000)
            return (int) (0.3*this.cantidadIdeal);
        if(investigacion==4500)
            return (int) (0.5*this.cantidadIdeal);
        if(investigacion==6000)
            return (int) (0.7*this.cantidadIdeal);
        return 0;
    }
   //guardar produccion en empresa
    public void calcular(List<Empresa> empresaList,int precioUnitario,int marketing,int investigacion, int activos, int inventarioUnidadesAnterior){

       int suma=0;
       for (int i=0;i<empresaList.size();i++){
           if(empresaList.get(i).get_id()!=this._id)
               suma=suma+empresaList.get(i).getProduccion();
       }
       suma=suma/2;
       this.cantidadIdeal= (int) (((337.5-precioUnitario)/0.125)-suma);
       this.cantidadIdealTotal=this.cantidadIdeal+obtenerPorcentaje(marketing)+obtenerPorcentaje(investigacion)+obtenerPorcentaje(activos);
       this.cantidadRealVendida=Math.min(cantidadIdealTotal,produccion+inventarioUnidadesAnterior);

    }


    public double getCantidadVendidaAnterior() {
        return cantidadVendidaAnterior;
    }

    public void setCantidadVendidaAnterior(double cantidadVendidaAnterior) {
        this.cantidadVendidaAnterior = cantidadVendidaAnterior;
    }

    public void calcularPorcentajeMercado( int mercado){
        System.out.println(mercado);
        System.out.println(Math.round((this.cantidadRealVendida*100)/mercado));
        this.porcentajeDeMercado= (int) Math.ceil((this.cantidadRealVendida*100)/mercado);
    }


    public String getCodigoJuego() {
        return codigoJuego;
    }

    public void setCodigoJuego(String codigoJuego) {
        this.codigoJuego = codigoJuego;
    }

    public int getCantidadIdeal() {
        return cantidadIdeal;
    }

    public void setCantidadIdeal(int cantidadIdeal) {
        this.cantidadIdeal = cantidadIdeal;
    }

    public int getCantidadIdealTotal() {
        return cantidadIdealTotal;
    }

    public void setCantidadIdealTotal(int cantidadIdealTotal) {
        this.cantidadIdealTotal = cantidadIdealTotal;
    }

    public int getProduccion() {
        return produccion;
    }

    public void setProduccion(int produccion) {
        this.produccion = produccion;
    }



    public int getCantidadRealVendida() {
        return cantidadRealVendida;
    }

    public void setCantidadRealVendida(int cantidadRealVendida) {
        this.cantidadRealVendida = cantidadRealVendida;
    }



    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
