package application.models;

public class Ventas {
    private String _id = null;
    private String _rev = null;

    private int producidoUnidades;
    private float producidoMonetario;
    private int ventasRealizadasUnidades;
    private int ventasRealizadasMonetario;
    private int inventarioUnidades;
    private int inventarioMonetario;
    private int pedidosNoAtendidosUnidades;
    private int pedidosNoAtendidosMonetario;


    private  int numero;
    private String codigo;
    public Ventas(){
        this.producidoUnidades=500;
        this.producidoMonetario=47270;
        this.ventasRealizadasUnidades=500;
        this.ventasRealizadasMonetario=75000;
        this.inventarioUnidades=0;
        this.inventarioMonetario=0;
        this.pedidosNoAtendidosMonetario=0;
        this.pedidosNoAtendidosUnidades=0;
    }

    public int getProducidoUnidades() {
        return producidoUnidades;
    }

    public void setProducidoUnidades(int producidoUnidades) {
        this.producidoUnidades = producidoUnidades;
    }



    public int getVentasRealizadasUnidades() {
        return ventasRealizadasUnidades;
    }

    public void setVentasRealizadasUnidades(int ventasRealizadasUnidades) {
        this.ventasRealizadasUnidades = ventasRealizadasUnidades;
    }

    public int getVentasRealizadasMonetario() {
        return ventasRealizadasMonetario;
    }

    public void setVentasRealizadasMonetario(int ventasRealizadasMonetario) {
        this.ventasRealizadasMonetario = ventasRealizadasMonetario;
    }

    public int getInventarioUnidades() {
        return inventarioUnidades;
    }

    public void setInventarioUnidades(int inventarioUnidades) {
        this.inventarioUnidades = inventarioUnidades;
    }

    public int getInventarioMonetario() {
        return inventarioMonetario;
    }

    public void setInventarioMonetario(int inventarioMonetario) {
        this.inventarioMonetario = inventarioMonetario;
    }

    public int getPedidosNoAtendidosUnidades() {
        return pedidosNoAtendidosUnidades;
    }

    public void setPedidosNoAtendidosUnidades(int pedidosNoAtendidosUnidades) {
        this.pedidosNoAtendidosUnidades = pedidosNoAtendidosUnidades;
    }

    public int getPedidosNoAtendidosMonetario() {
        return pedidosNoAtendidosMonetario;
    }

    public void setPedidosNoAtendidosMonetario(int pedidosNoAtendidosMonetario) {
        this.pedidosNoAtendidosMonetario = pedidosNoAtendidosMonetario;
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


    //corregir parametros pasados
    public  void calcular(int produccion, float costoUnitario, int inventarioUnidadesAnterior, int precioUnitario, int cantidadReal){
        this.producidoUnidades=produccion;
        this.producidoMonetario=produccion*costoUnitario;

        this.inventarioUnidades=this.producidoUnidades+inventarioUnidadesAnterior-this.ventasRealizadasUnidades;
        this.inventarioMonetario=this.inventarioUnidades*precioUnitario;

        this.ventasRealizadasUnidades=cantidadReal;
        this.ventasRealizadasMonetario=this.ventasRealizadasUnidades*precioUnitario;

        if(cantidadReal<(produccion+inventarioUnidadesAnterior))
            this.pedidosNoAtendidosUnidades=0;
        else
            this.pedidosNoAtendidosUnidades=cantidadReal-(produccion+inventarioUnidadesAnterior);
        this.pedidosNoAtendidosMonetario=this.pedidosNoAtendidosUnidades*precioUnitario;
    }


    public float getProducidoMonetario() {
        return producidoMonetario;
    }

    public void setProducidoMonetario(float producidoMonetario) {
        this.producidoMonetario = producidoMonetario;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
