package application.models;

import java.util.List;

public class VentasIndustria {
    private String _id = null;
    private String _rev = null;

    private String nombreEmpresaVentasI;


    private int ventasIndustriaUnidadesActual;
    private int ventasIndustriaUnidadesAnterior;

    private double ventasIndustriaMonetarioActual;
    private double ventasIndustriaMonetarioAnterior;

    private  double precioUnitarioPromedioActual;
    private  double precioUnitarioPromedioAnterior;

    private double inventarioPromediosActual;
    private double invetarioPromediosAnterior;

    private int numero;

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

    public int getVentasIndustriaUnidadesActual() {
        return ventasIndustriaUnidadesActual;
    }

    public void setVentasIndustriaUnidadesActual(int ventasIndustriaUnidadesActual) {
        this.ventasIndustriaUnidadesActual = ventasIndustriaUnidadesActual;
    }

    public int getVentasIndustriaUnidadesAnterior() {
        return ventasIndustriaUnidadesAnterior;
    }

    public void setVentasIndustriaUnidadesAnterior(int ventasIndustriaUnidadesAnterior) {
        this.ventasIndustriaUnidadesAnterior = ventasIndustriaUnidadesAnterior;
    }

    public double getVentasIndustriaMonetarioActual() {
        return ventasIndustriaMonetarioActual;
    }

    public void setVentasIndustriaMonetarioActual(double ventasIndustriaMonetarioActual) {
        this.ventasIndustriaMonetarioActual = ventasIndustriaMonetarioActual;
    }

    public double getVentasIndustriaMonetarioAnterior() {
        return ventasIndustriaMonetarioAnterior;
    }

    public void setVentasIndustriaMonetarioAnterior(double ventasIndustriaMonetarioAnterior) {
        this.ventasIndustriaMonetarioAnterior = ventasIndustriaMonetarioAnterior;
    }

    public double getPrecioUnitarioPromedioActual() {
        return precioUnitarioPromedioActual;
    }

    public void setPrecioUnitarioPromedioActual(double precioUnitarioPromedioActual) {
        this.precioUnitarioPromedioActual = precioUnitarioPromedioActual;
    }

    public double getPrecioUnitarioPromedioAnterior() {
        return precioUnitarioPromedioAnterior;
    }

    public void setPrecioUnitarioPromedioAnterior(double precioUnitarioPromedioAnterior) {
        this.precioUnitarioPromedioAnterior = precioUnitarioPromedioAnterior;
    }

    public double getInventarioPromediosActual() {
        return inventarioPromediosActual;
    }

    public void setInventarioPromediosActual(double inventarioPromediosActual) {
        this.inventarioPromediosActual = inventarioPromediosActual;
    }

    public double getInvetarioPromediosAnterior() {
        return invetarioPromediosAnterior;
    }

    public void setInvetarioPromediosAnterior(double invetarioPromediosAnterior) {
        this.invetarioPromediosAnterior = invetarioPromediosAnterior;
    }

    public String getNombreEmpresaVentasI() {
        return nombreEmpresaVentasI;
    }

    public void setNombreEmpresaVentasI(String nombreEmpresaVentasI) {
        this.nombreEmpresaVentasI = nombreEmpresaVentasI;
    }

    public void calcular(List<Bimestre> bimestreList, List<Ventas> ventasList){
        int suma=0;
        for(int i=0;i<bimestreList.size();i++){
            suma=suma+bimestreList.get(i).getPrecioUnitario();
        }
        this.precioUnitarioPromedioActual=suma/bimestreList.size();

        for(int i=0;i<ventasList.size();i++){
            this.ventasIndustriaUnidadesActual=this.ventasIndustriaUnidadesActual+ventasList.get(i).getVentasRealizadasUnidades();
            this.ventasIndustriaMonetarioActual=this.ventasIndustriaMonetarioActual+ventasList.get(i).getVentasRealizadasMonetario();
            this.inventarioPromediosActual=this.inventarioPromediosActual+ventasList.get(i).getInventarioUnidades();
        }
        this.inventarioPromediosActual=this.inventarioPromediosActual/ventasList.size();
    }
    public  void cambiarActualAnterior(){
        this.invetarioPromediosAnterior=this.inventarioPromediosActual;
        this.ventasIndustriaMonetarioAnterior=this.ventasIndustriaMonetarioActual;
        this.ventasIndustriaUnidadesAnterior=this.ventasIndustriaUnidadesActual;
        this.precioUnitarioPromedioAnterior=this.precioUnitarioPromedioActual;
    }
  public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
