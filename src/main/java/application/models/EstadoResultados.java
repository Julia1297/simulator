package application.models;

public class EstadoResultados {
    private String _id = null;
    private String _rev = null;
    private int ingresos;
    private int ventas;
    private int otrosIngresos;
    private int capitalAnterior;
    private int costos;
    private int materiaPrima;
    private int manoObra;
    private int costosIndirectos;
    private int utilidadBruta;
    private int gastosOperativos;
    private int inversionMarketing;
    private int inversionInvestigacion;
    private int inversionActivos;
    private int utilidadNeta;


    private String codigo;
    private int numero;
    private String empresa;


    public EstadoResultados(){
        this.ingresos=95000;
        this.ventas=75000;
        this.otrosIngresos=10000;
        this.capitalAnterior=10000;
        this.costos=48270;
        this.materiaPrima=17500;
        this.manoObra=8000;
        this.costosIndirectos=22770;
        this.utilidadBruta=46730;
        this.gastosOperativos=8500;
        this.inversionMarketing=3500;
        this.inversionInvestigacion=1500;
        this.inversionActivos=3500;
        this.utilidadNeta=38230;
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

    public int getIngresos() {
        return ingresos;
    }

    public void setIngresos(int ingresos) {
        this.ingresos = ingresos;
    }

    public int getVentas() {
        return ventas;
    }

    public void setVentas(int ventas) {
        this.ventas = ventas;
    }

    public int getOtrosIngresos() {
        return otrosIngresos;
    }

    public void setOtrosIngresos(int otrosIngresos) {
        this.otrosIngresos = otrosIngresos;
    }

    public int getCapitalAnterior() {
        return capitalAnterior;
    }

    public void setCapitalAnterior(int capitalAnterior) {
        this.capitalAnterior = capitalAnterior;
    }

    public int getCostos() {
        return costos;
    }

    public void setCostos(int costos) {
        this.costos = costos;
    }

    public int getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(int materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    public int getManoObra() {
        return manoObra;
    }

    public void setManoObra(int manoObra) {
        this.manoObra = manoObra;
    }

    public int getCostosIndirectos() {
        return costosIndirectos;
    }

    public void setCostosIndirectos(int costosIndirectos) {
        this.costosIndirectos = costosIndirectos;
    }

    public int getUtilidadBruta() {
        return utilidadBruta;
    }

    public void setUtilidadBruta(int utilidadBruta) {
        this.utilidadBruta = utilidadBruta;
    }

    public int getGastosOperativos() {
        return gastosOperativos;
    }

    public void setGastosOperativos(int gastosOperativos) {
        this.gastosOperativos = gastosOperativos;
    }

    public int getInversionMarketing() {
        return inversionMarketing;
    }

    public void setInversionMarketing(int inversionMarketing) {
        this.inversionMarketing = inversionMarketing;
    }

    public int getInversionInvestigacion() {
        return inversionInvestigacion;
    }

    public void setInversionInvestigacion(int inversionInvestigacion) {
        this.inversionInvestigacion = inversionInvestigacion;
    }

    public int getInversionActivos() {
        return inversionActivos;
    }

    public void setInversionActivos(int inversionActivos) {
        this.inversionActivos = inversionActivos;
    }

    public int getUtilidadNeta() {
        return utilidadNeta;
    }

    public void setUtilidadNeta(int utilidadNeta) {
        this.utilidadNeta = utilidadNeta;
    }

    public void calcular(int ventasRealizadasMonetario,int materiaPrima,int manoObra,int costosIndirectos,int inversionMarketing,int inversionInvestigacion, int inversionEnActivos){
        this.ventas=ventasRealizadasMonetario;
        this.otrosIngresos=10000;
        //utilidad neta
        this.capitalAnterior=10000;
        this.ingresos=this.ventas+this.otrosIngresos+this.capitalAnterior;
        this.materiaPrima=materiaPrima;
        this.manoObra=manoObra;
        this.costosIndirectos=costosIndirectos;
        this.costos=this.materiaPrima+this.manoObra+this.costosIndirectos;
        this.utilidadBruta=this.ingresos-this.costos;
        this.inversionMarketing=inversionMarketing;
        this.inversionInvestigacion=inversionInvestigacion;
        this.inversionActivos=inversionActivos;
        this.gastosOperativos=this.inversionActivos+this.inversionInvestigacion+this.inversionMarketing;
        this.utilidadNeta=this.utilidadBruta-this.gastosOperativos;

    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
