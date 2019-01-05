package application.controllers;

import application.models.*;
import application.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BimestreController {


    private final BimestreService bimestreService;
    private final BalanceGeneralService balanceGeneralService;
    private final CostosProduccionService costosProduccionService;
    private final EstadoResultadosService estadoResultadosService;
    private final VentasService ventasService;
    private final EmpresaService empresaService;
    private final JuegoService juegoService;
    private final ProduccionService produccionService;
    private final VentasIndustriaService ventasIndustriaService;
    private final  VisionGeneralService visionGeneralService;



    @Autowired
    public BimestreController(JuegoService juegoService, EmpresaService empresaService, BimestreService bimestreService, BalanceGeneralService balanceGeneralService, CostosProduccionService costosProduccionService, EstadoResultadosService estadoResultadosService, VentasService ventasService, ProduccionService produccionService, VentasIndustriaService ventasIndustriaService, VisionGeneralService visionGeneralService) {
        this.bimestreService = bimestreService;
        this.balanceGeneralService=balanceGeneralService;
        this.costosProduccionService=costosProduccionService;
        this.estadoResultadosService=estadoResultadosService;
        this.ventasService=ventasService;
        this.empresaService=empresaService;
        this.juegoService=juegoService;
        this.produccionService = produccionService;
        this.ventasIndustriaService = ventasIndustriaService;
        this.visionGeneralService = visionGeneralService;
    }

    @PostMapping(value="/bimestre",consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void bimestre( @Valid @RequestBody Bimestre bimestre) throws Exception {
        if(bimestre!=null){

            BalanceGeneral balanceGeneral=new BalanceGeneral();
            CostosProduccion costosProduccion=new CostosProduccion();
            EstadoResultados estadoResultados=new EstadoResultados();
            Ventas ventas=new Ventas();
            VisionGeneral visionGeneral =new VisionGeneral();
            Empresa empresa;
            List<Empresa> empresas;
            Juego juego;
            Ventas ventasAnterior;
            EstadoResultados estadoResultadosAnterior;



            bimestre.set_id("bimestre"+bimestre.getNombreEmpresa()+bimestre.getNumero()+bimestre.getCodigo());
            balanceGeneral.set_id("BG"+bimestre.getNombreEmpresa()+bimestre.getNumero()+bimestre.getCodigo());
            costosProduccion.set_id("CP"+bimestre.getNombreEmpresa()+bimestre.getNumero()+bimestre.getCodigo());
            estadoResultados.set_id("ER"+bimestre.getNombreEmpresa()+bimestre.getNumero()+bimestre.getCodigo());
            ventas.set_id("V"+bimestre.getNombreEmpresa()+bimestre.getNumero()+bimestre.getCodigo());
            estadoResultados.setEmpresa(bimestre.getNombreEmpresa());
            estadoResultados.setCodigoEstado(bimestre.getCodigo());
            estadoResultados.setNumeroEstado(bimestre.getNumero());
            ventas.setCodigo(bimestre.getCodigo());
            visionGeneral.set_id("VG"+bimestre.getNombreEmpresa()+bimestre.getNumero()+bimestre.getCodigo());
            visionGeneral.setNombreEmpresa(bimestre.getNombreEmpresa());
            visionGeneral.setCodigoVision(bimestre.getCodigo());
            visionGeneral.setNumeroVision(bimestre.getNumero());


            int ventasUnidades=0;
            int utilidadNeta=10000;
            if(bimestre.getNumero()!=0){
                String numeroBimestreAnterior= String.valueOf((bimestre.getNumero() - 1));
                ventasAnterior = ventasService.obtenerVentas("V" + bimestre.getNombreEmpresa() + numeroBimestreAnterior+bimestre.getCodigo());
                estadoResultadosAnterior=estadoResultadosService.obtenerEstadoResultado("ER"+bimestre.getNombreEmpresa()+numeroBimestreAnterior+bimestre.getCodigo());
                ventasUnidades=ventasAnterior.getInventarioUnidades();
                utilidadNeta=estadoResultadosAnterior.getUtilidadNeta();
            }

            //COSTOS PRODUCCION
            costosProduccion.calcular(bimestre.getProduccion());
            //Para ventas
            empresa=empresaService.obtenerEmpresa(bimestre.getNombreEmpresa()+""+bimestre.getCodigo()) ;
            empresa.setProduccion(bimestre.getProduccion());
            empresaService.update(empresa);
            empresas=empresaService.listarEmpresasPorCodigoJuego(bimestre.getCodigo());
            empresa=empresaService.obtenerEmpresa(bimestre.getNombreEmpresa()+""+bimestre.getCodigo()) ;
            empresa.calcular(empresas,bimestre.getPrecioUnitario(),bimestre.getInversionEnMarketing(),bimestre.getInversionEnInvestigacion(),bimestre.getInversionEnActivos(),ventasUnidades);
            empresaService.update(empresa);

            //VENTAS
            ventas.calcular(bimestre.getProduccion(),costosProduccion.getCostoUnitario(),ventasUnidades,bimestre.getPrecioUnitario(),empresa.getCantidadRealVendida());
            //ESTADO RESULTADOS
            estadoResultados.calcular(ventas.getVentasRealizadasMonetario(),costosProduccion.getMateriaPrima(),costosProduccion.getManoDeObraDirecta(),costosProduccion.getCostosIndirectos(),bimestre.getInversionEnMarketing(),bimestre.getInversionEnInvestigacion(),bimestre.getInversionEnActivos(),utilidadNeta);
            //BALANCE GENERAL
            balanceGeneral.calcular(estadoResultados.getUtilidadBruta(),bimestre.getPrecioUnitario(),ventas.getInventarioUnidades(),estadoResultados.getUtilidadNeta());

            bimestreService.save(bimestre);
            costosProduccionService.save(costosProduccion);
            ventasService.save(ventas);
            estadoResultadosService.save(estadoResultados);
            balanceGeneralService.save(balanceGeneral);
            visionGeneralService.save(visionGeneral);


            juego=juegoService.obtenerJuego(bimestre.getCodigo());
            juego.calcular(empresas);
            juegoService.update(juego);
            empresas=empresaService.listarEmpresasPorCodigoJuegoActualizandoDatos(bimestre.getCodigo(),juego.getMercado());
            juego=juegoService.obtenerJuego(bimestre.getCodigo());
            empresa =empresaService.obtenerEmpresa(bimestre.getNombreEmpresa()+bimestre.getCodigo()) ;
            empresa.calcularPorcentajeMercado(juego.getMercado());
            empresaService.update(empresa);
            //empresas=empresaService.listarEmpresasPorCodigoJuego(bimestre.getCodigoEstado());
            //juego.calcularMercadoDesatendido(empresas);

            empresa =empresaService.obtenerEmpresa(bimestre.getNombreEmpresa()+bimestre.getCodigo()) ;
            visionGeneral=visionGeneralService.obtenerVisionGeneral("VG"+bimestre.getNombreEmpresa()+bimestre.getNumero()+bimestre.getCodigo());
            visionGeneral.calcular(empresa.getCantidadRealVendida(),bimestre.getProduccion(),estadoResultados.getUtilidadNeta(),bimestre.getPrecioUnitario(),empresa.getPorcentajeDeMercado());
            visionGeneralService.update(visionGeneral);
            visionGeneralService.actualizarPorcentajesMercado(bimestre.getCodigo(),bimestre.getNumero(),empresas);
            Produccion produccion=produccionService.obtenerProduccion("PR"+empresa.getNombre()+bimestre.getCodigo());
            produccion.setNumero(bimestre.getNumero());
            //corregir list
            List<CostosProduccion> costosProduccionList=costosProduccionService.obtenerEstadoResultaodsPorNumeroYJuego(bimestre.getCodigo(),bimestre.getNumero());
            List<Bimestre> bimestreList =bimestreService.obtenerBimestrePorNumeroYJuego(bimestre.getCodigo(),bimestre.getNumero());
            List<Ventas> ventasList =ventasService.obtenerVentasPorNumeroYJuego(bimestre.getCodigo(),bimestre.getNumero());
            produccion.cambiarActualAnterior();
            produccion.calcular(bimestreList,costosProduccionList);
            produccionService.update(produccion);
            VentasIndustria ventasIndustria=ventasIndustriaService.obtenerVentasIndustria("VI"+empresa.getNombre()+bimestre.getCodigo());
            ventasIndustria.cambiarActualAnterior();
            ventasIndustria.calcular(bimestreList,ventasList);
            ventasIndustria.setNumero(bimestre.getNumero());
            ventasIndustriaService.update(ventasIndustria);

            if(ventas.getInventarioUnidades()>=juego.getMercado() || visionGeneral.getPorcentajeDeMercado()<2 || estadoResultados.getUtilidadNeta()<0)
                empresa.setEstado("Perdedor");
            if(visionGeneral.getPorcentajeDeMercado()>85  || estadoResultadosService.unicaEmpresaConValorPositivo(bimestre.getCodigo(),bimestre.getNombreEmpresa(),bimestre.getNumero()))
                empresa.setEstado("Ganador");

        }
    }


    @GetMapping(value="/balanceGeneral/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BalanceGeneral getBalance(@PathVariable String id) throws Exception {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return balanceGeneralService.obtenerBalance(id);
    }

    @GetMapping(value="/ventas/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Ventas getVentas(@PathVariable String id) throws Exception {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ventasService.obtenerVentas(id);
    }

    @GetMapping(value="/produccion/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Produccion getProduccion(@PathVariable String id) throws Exception {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return produccionService.obtenerProduccion(id);
    }

    @GetMapping(value="/produccionBimestres/{codigo}")
    @ResponseStatus(HttpStatus.OK)
    public List<Double> getProduccionBimestres(@PathVariable String codigo) throws Exception {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return produccionService.sumatoriaProduccionEmpresas(codigo);
    }
    @GetMapping(value="/precioUnitarioBimestres/{codigo}")
    @ResponseStatus(HttpStatus.OK)
    public List<Double> getPrecioUnitarioBimestres(@PathVariable String codigo) throws Exception {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return bimestreService.promedioPrecioUnitarioEmpresas(codigo);
    }

    @GetMapping(value="/utilidadNetaBimestres/{codigo}/{empresa}")
    @ResponseStatus(HttpStatus.OK)
    public List<Double> getUtilidadNetaBimestres(@PathVariable String codigo, @PathVariable String empresa) throws Exception {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return estadoResultadosService.promedioUtilidadNetaEmpresas(empresaService.listarEmpresasPorCodigoJuego(codigo),empresa);
    }

    @GetMapping(value="/ventasIndustria/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VentasIndustria getVentasIndustria(@PathVariable String id) throws Exception {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ventasIndustriaService.obtenerVentasIndustria(id);
    }

    @GetMapping(value="/ventasIndustriaBimestres/{nombre}")
    @ResponseStatus(HttpStatus.OK)
    public List<VentasIndustria> getVentasIndustriaBimestre(@PathVariable String nombre) throws Exception {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ventasIndustriaService.obtenerListaVentasIndustriaBimestre(nombre);
    }

    //id codigo juego
    @GetMapping(value="/visionGeneral/{id}/{numero}")
    @ResponseStatus(HttpStatus.OK)
    public List<VisionGeneral> getVisionesGenerales(@PathVariable String id, @PathVariable int numero) throws Exception {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return visionGeneralService.listarVisionesGeneralesJuegoNumeroBimestre(id,numero);
    }

    @GetMapping(value="/costosProduccion/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CostosProduccion getCostos(@PathVariable String id) throws Exception {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return costosProduccionService.obtenerCostoProduccion(id);
    }

    @GetMapping(value="/estadoResultadosEmpresa/{empresa}")
    @ResponseStatus(HttpStatus.OK)
    public List<EstadoResultados> getAll(@PathVariable String empresa) throws Exception {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return estadoResultadosService.listarEstadosEmpresa(empresa);
    }


    @PostMapping(value="/empresa/{codigo}")
    @ResponseStatus(HttpStatus.OK)
    public void crearEmpresa(@RequestBody Empresa empresa, @PathVariable String codigo ) throws Exception {

        if(empresa!=null && codigo!=null){
            Juego juego=juegoService.obtenerJuego(codigo);
                if(juego.getCantidadEmpresa()<=5) {
                    if (juego.getCantidadEmpresa() == 1) {
                        juego.setEmpresa1_id(empresa.getNombre()+codigo);
                    }
                    if (juego.getCantidadEmpresa() == 2) {
                        juego.setEmpresa2_id(empresa.getNombre()+codigo);
                    }
                    if (juego.getCantidadEmpresa() == 3) {
                        juego.setEmpresa3_id(empresa.getNombre()+codigo);
                    }
                    if (juego.getCantidadEmpresa() == 4) {
                        juego.setEmpresa4_id(empresa.getNombre()+codigo);
                    }
                    if (juego.getCantidadEmpresa() == 5) {
                        juego.setEmpresa5_id(empresa.getNombre()+codigo);
                    }
                    juego.setCantidadEmpresa(juego.getCantidadEmpresa() + 1);
                    empresa.setCodigoJuego(codigo);
                    empresa.set_id(empresa.getNombre()+codigo);
                    empresaService.save(empresa);

                    Produccion produccion= new Produccion();
                    VentasIndustria ventasIndustria=new VentasIndustria();

                    produccion.setNombreEmpresaProduccion(empresa.getNombre());
                    produccion.set_id("PR"+empresa.getNombre()+codigo);

                    produccionService.save(produccion);
                    ventasIndustria.setNombreEmpresaVentasI(empresa.getNombre());
                    ventasIndustria.set_id("VI"+empresa.getNombre()+codigo);
                    ventasIndustriaService.save(ventasIndustria);
                    juegoService.update(juego);

                }

        }
    }
    @PostMapping(value="/juego")
    @ResponseStatus(HttpStatus.OK)
    public void crearJuego(@RequestBody Juego juego) throws Exception {
        juegoService.save(juego);
    }

}
