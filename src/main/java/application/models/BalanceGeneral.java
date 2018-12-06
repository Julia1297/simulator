package application.models;

public class BalanceGeneral {
    private String _id = null;
    private String _rev = null;
    private int caja;
    private int bancos;
    private int inventario;
    private int corriente;
    private int totalActivos;
    private int capital;
    private int utilidadEjercicio;
    private int totalPatrimonio;
    private int totalPasivoPatrimonio;
    public BalanceGeneral(){
        this.caja=32711;
        this.bancos=14019;
        this.inventario=0;
        this.corriente=46730;
        this.totalActivos=46748;
        this.capital=7218;
        this.utilidadEjercicio=38230;
        this.totalPatrimonio=45448;
        this.totalPasivoPatrimonio=46748;
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

    public int getCaja() {
        return caja;
    }

    public void setCaja(int caja) {
        this.caja = caja;
    }

    public int getBancos() {
        return bancos;
    }

    public void setBancos(int bancos) {
        this.bancos = bancos;
    }

    public int getInventario() {
        return inventario;
    }

    public void setInventario(int inventario) {
        this.inventario = inventario;
    }

    public int getCorriente() {
        return corriente;
    }

    public void setCorriente(int corriente) {
        this.corriente = corriente;
    }

    public int getTotalActivos() {
        return totalActivos;
    }

    public void setTotalActivos(int totalActivos) {
        this.totalActivos = totalActivos;
    }

    public int getCapital() {
        return capital;
    }

    public void setCapital(int capital) {
        this.capital = capital;
    }

    public int getUtilidadEjercicio() {
        return utilidadEjercicio;
    }

    public void setUtilidadEjercicio(int utilidadEjercicio) {
        this.utilidadEjercicio = utilidadEjercicio;
    }

    public int getTotalPatrimonio() {
        return totalPatrimonio;
    }

    public void setTotalPatrimonio(int totalPatrimonio) {
        this.totalPatrimonio = totalPatrimonio;
    }

    public int getTotalPasivoPatrimonio() {
        return totalPasivoPatrimonio;
    }

    public void setTotalPasivoPatrimonio(int totalPasivoPatrimonio) {
        this.totalPasivoPatrimonio = totalPasivoPatrimonio;
    }
    public  void calcular(int utilidadBruta,int precioUnitario, int inventario, int utilidadNeta){
        this.caja= (int) (utilidadBruta*0.7);
        this.bancos=(int) (utilidadBruta*0.3);
        this.inventario=precioUnitario-inventario;
        this.corriente=this.bancos+this.caja+this.inventario;
        this.totalActivos=this.corriente+8500;
        this.utilidadEjercicio=utilidadNeta;
        this.capital=this.totalActivos-this.utilidadEjercicio+1300;
        this.totalPatrimonio=this.capital+this.utilidadEjercicio;
        this.totalPasivoPatrimonio=this.totalPatrimonio+1300;
    }
}







