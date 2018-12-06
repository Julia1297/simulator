package application.models;

public class VisionGeneral {

    private String _id = null;
    private String _rev = null;

    private String nombreEmpresa;
    private float ventas;
    private float beneficio;
    private int precioUnitario;
    private int porcentajeDeMercado;

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

    public float getVentas() {
        return ventas;
    }

    public void setVentas(float ventas) {
        this.ventas = ventas;
    }

    public float getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(float beneficio) {
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

    public void calcular(int cantidadVendida, int produccion,int utilidadNeta, int precioUnitario, int porcentajeDeMercado){
        this.ventas= Math.min(cantidadVendida,produccion);
        this.beneficio=utilidadNeta;
        this.precioUnitario=precioUnitario;
        this.porcentajeDeMercado=porcentajeDeMercado;

    }

}
