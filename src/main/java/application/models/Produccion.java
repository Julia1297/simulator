package application.models;

import java.util.List;

public class Produccion {


    private String _id = null;
    private String _rev = null;

    private int produccionIndustriaValorActual;
    private int produccionIndustriaValorAnterior;
    private double costeMedioTotalActual;
    private double costeMedioTotalAnterior;
    private double costeMedioUnitarioActual;
    private double costeMedioUnitarioAnterior;
    private String nombreEmpresa;
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

       public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
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
            this.costeMedioUnitarioActual=this.costeMedioUnitarioActual+costosProduccionList.get(i).getCostoUnitario();
        }
        this.costeMedioTotalActual=this.costeMedioTotalActual/costosProduccionList.size();
        this.costeMedioUnitarioActual=this.costeMedioUnitarioActual/costosProduccionList.size();
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
}
