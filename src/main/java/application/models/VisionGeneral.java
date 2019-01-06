package application.models;

public class VisionGeneral {

    private String _id = null;
    private String _rev = null;

    private String nombreEmpresa;
    private double ventas;
    private double beneficio;
    private int precioUnitario;
    private int porcentajeDeMercado;
    private String codigoVision;
    private int numeroVision;

    private int puntajeMercado =2;
    private int puntajeBeneficio =2;

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

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public double getVentas() {
        return ventas;
    }

    public void setVentas(double ventas) {
        this.ventas = ventas;
    }

    public double getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(double beneficio) {
        this.beneficio = beneficio;
    }

    public int getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(int precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getPorcentajeDeMercado() {
        return porcentajeDeMercado;
    }

    public void setPorcentajeDeMercado(int porcentajeDeMercado) {
        this.porcentajeDeMercado = porcentajeDeMercado;
    }

    public void calcular(int cantidadReal, int produccion, int utilidadNeta, int precioUnitario, int porcentajeDeMercado){
        this.ventas=  cantidadReal;
        this.beneficio=utilidadNeta;
        this.precioUnitario=precioUnitario;
        this.porcentajeDeMercado=porcentajeDeMercado;

    }

    public String getCodigoVision() {
        return codigoVision;
    }

    public void setCodigoVision(String codigoVision) {
        this.codigoVision = codigoVision;
    }

    public int getNumeroVision() {
        return numeroVision;
    }

    public void setNumeroVision(int numeroVision) {
        this.numeroVision = numeroVision;
    }

    public int getPuntajeMercado() {
        return puntajeMercado;
    }

    public void setPuntajeMercado(int puntajeMercado) {
        this.puntajeMercado = puntajeMercado;
    }

    public int getPuntajeBeneficio() {
        return puntajeBeneficio;
    }

    public void setPuntajeBeneficio(int puntajeBeneficio) {
        this.puntajeBeneficio = puntajeBeneficio;
    }
}
