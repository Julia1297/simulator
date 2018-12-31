package application.models;

import java.util.List;

public class Produccion {


    private String _id = null;
    private String _rev = null;

    private int produccionIndustriaValorActual=2500;
    private int produccionIndustriaValorAnterior;
    private double costeMedioTotalActual=48270;
    private double costeMedioTotalAnterior;
    private double costeMedioUnitarioActual=94.54;
    private double costeMedioUnitarioAnterior;
    private String nombreEmpresaProduccion;
    private String codigo;

    private int numero;



//    private double produccionIndustriaVariacion;
  //  private double costeMedioTotalVariacion;
    //private double costeMedioUnitarioVariacion;


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



    public int getProduccionIndustriaValorActual() {
        return produccionIndustriaValorActual;
    }

    public void setProduccionIndustriaValorActual(int produccionIndustriaValorActual) {
        this.produccionIndustriaValorActual = produccionIndustriaValorActual;
    }

    public int getProduccionIndustriaValorAnterior() {
        return produccionIndustriaValorAnterior;
    }

    public void setProduccionIndustriaValorAnterior(int produccionIndustriaValorAnterior) {
        this.produccionIndustriaValorAnterior = produccionIndustriaValorAnterior;
    }

    

    public double getCosteMedioTotalAnterior() {
        return costeMedioTotalAnterior;
    }

    public void setCosteMedioTotalAnterior(double costeMedioTotalAnterior) {
        this.costeMedioTotalAnterior = costeMedioTotalAnterior;
    }

       public String getNombreEmpresaProduccion() {
        return nombreEmpresaProduccion;
    }

    public void setNombreEmpresaProduccion(String nombreEmpresaProduccion) {
        this.nombreEmpresaProduccion = nombreEmpresaProduccion;
    }

    public void calcular(List<Bimestre> bimestreList, List<CostosProduccion> costosProduccionList){
        int suma=0;
        for(int i=0;i<bimestreList.size();i++){
            suma=suma+bimestreList.get(i).getProduccion();
        }
        this.produccionIndustriaValorActual=suma;
        suma=0;
        for(int i=0;i<costosProduccionList.size();i++){
            this.costeMedioTotalActual=this.costeMedioTotalActual+costosProduccionList.get(i).getCostoTotal();
            this.costeMedioUnitarioActual=this.costeMedioUnitarioActual +costosProduccionList.get(i).getCostoUnitario();
        }
        this.costeMedioTotalActual=this.costeMedioTotalActual /costosProduccionList.size();
        this.costeMedioUnitarioActual=this.costeMedioUnitarioActual /costosProduccionList.size();
    }

    public void cambiarActualAnterior(){
        this.produccionIndustriaValorAnterior=this.produccionIndustriaValorActual;
        this.costeMedioTotalAnterior=this.costeMedioTotalActual;
        this.costeMedioUnitarioAnterior=this.costeMedioUnitarioActual;
    }
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getCosteMedioUnitarioAnterior() {
        return costeMedioUnitarioAnterior;
    }

    public void setCosteMedioUnitarioAnterior(double costeMedioUnitarioAnterior) {
        this.costeMedioUnitarioAnterior = costeMedioUnitarioAnterior;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getCosteMedioTotalActual() {
        return costeMedioTotalActual;
    }

    public void setCosteMedioTotalActual(double costeMedioTotalActual) {
        this.costeMedioTotalActual = costeMedioTotalActual;
    }

    public double getCosteMedioUnitarioActual() {
        return costeMedioUnitarioActual;
    }

    public void setCosteMedioUnitarioActual(double costeMedioUnitarioActual) {
        this.costeMedioUnitarioActual = costeMedioUnitarioActual;
    }
}
