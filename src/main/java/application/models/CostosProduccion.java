package application.models;

public class CostosProduccion {

    private String _id = null;
    private String _rev = null;
    private int materiaPrima;
    private int manoDeObraDirecta;
    private int costosIndirectos;
    private int costoTotal;
    private float costoUnitario;

    public CostosProduccion(){
        this.materiaPrima=17500;
        this.manoDeObraDirecta=8000;
        this.costosIndirectos=22770;
        this.costoTotal=48270;
        this.costoUnitario= (float) 94.54;
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

    public int getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(int materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    public int getManoDeObraDirecta() {
        return manoDeObraDirecta;
    }

    public void setManoDeObraDirecta(int manoDeObraDirecta) {
        this.manoDeObraDirecta = manoDeObraDirecta;
    }

    public int getCostosIndirectos() {
        return costosIndirectos;
    }

    public void setCostosIndirectos(int costosIndirectos) {
        this.costosIndirectos = costosIndirectos;
    }

    public int getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(int costoTotal) {
        this.costoTotal = costoTotal;
    }

    public float getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(float costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public void calcular(int produccion){
        this.materiaPrima=produccion*35;
        this.costoTotal=this.materiaPrima+this.manoDeObraDirecta+this.costosIndirectos;
        this.costoUnitario=this.costoTotal/produccion;
    }
}
