package application.models;

public class Bimestre {

    private String _id = null;
    private String _rev = null;

    private int precioUnitario;
    private int produccion;
    private int inversionEnMarketing;
    private int inversionEnInvestigacion;
    private int inversionEnActivos;

    private int numero;
    private String nombreEmpresa;
    private String balanceGeneral_id;
    private String costosProduccion_id;
    private String estadoResultados_id;
    private String ventas_id;
    

    public Bimestre(){
        this.precioUnitario=150;
        this.produccion=500;
        this.inversionEnMarketing=3500;
        this.inversionEnInvestigacion=1500;
        this.inversionEnActivos=3500;
    }

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

    public int getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(int precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getProduccion() {
        return produccion;
    }

    public void setProduccion(int produccion) {
        this.produccion = produccion;
    }

    public int getInversionEnMarketing() {
        return inversionEnMarketing;
    }

    public void setInversionEnMarketing(int inversionEnMarketing) {
        this.inversionEnMarketing = inversionEnMarketing;
    }

    public int getInversionEnInvestigacion() {
        return inversionEnInvestigacion;
    }

    public void setInversionEnInvestigacion(int inversionEnInvestigacion) {
        this.inversionEnInvestigacion = inversionEnInvestigacion;
    }

    public int getInversionEnActivos() {
        return inversionEnActivos;
    }

    public void setInversionEnActivos(int inversionEnActivos) {
        this.inversionEnActivos = inversionEnActivos;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }



    public String getBalanceGeneral_id() {
        return balanceGeneral_id;
    }

    public void setBalanceGeneral_id(String balanceGeneral_id) {
        this.balanceGeneral_id = balanceGeneral_id;
    }

    public String getCostosProduccion_id() {
        return costosProduccion_id;
    }

    public void setCostosProduccion_id(String costosProduccion_id) {
        this.costosProduccion_id = costosProduccion_id;
    }

    public String getEstadoResultados_id() {
        return estadoResultados_id;
    }

    public void setEstadoResultados_id(String estadoResultados_id) {
        this.estadoResultados_id = estadoResultados_id;
    }

    public String getVentas_id() {
        return ventas_id;
    }

    public void setVentas_id(String ventas_id) {
        this.ventas_id = ventas_id;
    }
    
     public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
    
}
