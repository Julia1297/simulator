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
            bimestre.set_id("bimestre"+bimestre.getNombreEmpresa()+bimestre.getNumero());

            balanceGeneral.set_id("BG"+bimestre.getNombreEmpresa()+bimestre.getNumero());
            costosProduccion.set_id("CP"+bimestre.getNombreEmpresa()+bimestre.getNumero());
            estadoResultados.set_id("ER"+bimestre.getNombreEmpresa()+bimestre.getNumero());
            ventas.set_id("V"+bimestre.getNombreEmpresa()+bimestre.getNumero());
            estadoResultados.setEmpresa(bimestre.getNombreEmpresa());
            if(bimestre.getNumero()!=0) {
            //COSTOS PRODUCCION
                costosProduccion.calcular(bimestre.getProduccion());
                //VENTAS
                String numeroBimestreAnterior= String.valueOf((bimestre.getNumero() - 1));
                Ventas ventasAnterior=ventasService.obtenerVentas("V"+bimestre.getNombreEmpresa()+numeroBimestreAnterior);
                ventas.calcular(bimestre.getProduccion(),costosProduccion.getCostoUnitario(),ventasAnterior.getInventarioUnidades(),bimestre.getPrecioUnitario());
                //ESTADO RESULTADOS
                estadoResultados.calcular(ventas.getVentasRealizadasMonetario(),costosProduccion.getMateriaPrima(),costosProduccion.getManoDeObraDirecta(),costosProduccion.getCostosIndirectos(),bimestre.getInversionEnMarketing(),bimestre.getInversionEnInvestigacion(),bimestre.getInversionEnActivos());
                //BALANCE GENERAL
                balanceGeneral.calcular(estadoResultados.getUtilidadBruta(),bimestre.getPrecioUnitario(),ventas.getInventarioUnidades(),estadoResultados.getUtilidadNeta());
            }
            bimestreService.save(bimestre);
            costosProduccionService.save(costosProduccion);
            ventasService.save(ventas);
            estadoResultadosService.save(estadoResultados);
            balanceGeneralService.save(balanceGeneral);

            //corregir
            Juego juego=juegoService.obtenerJuego(bimestre.get_id());

            String numeroBimestreAnterior= String.valueOf((bimestre.getNumero() - 1));
            Ventas ventasAnterior=ventasService.obtenerVentas("V"+bimestre.getNombreEmpresa()+numeroBimestreAnterior);
            List<Empresa> empresas=empresaService.listarEmpresas(empresaService.obtenerEmpresa(bimestre.getNombreEmpresa()).get_id());
            Empresa empresa =empresaService.obtenerEmpresa(bimestre.getNombreEmpresa()) ;
            empresa.calcularCantidadVendida(empresas,bimestre.getPrecioUnitario());

            //revisas empresas
            juego.calcularMercadoCubierto(empresas);
            empresa.calcularPorcentajeMercado(bimestre.getProduccion(),ventasAnterior.getInventarioUnidades(),juego.getMercadoCubierto());
            juego.calcularMercadoDesatendido(empresas);
            VisionGeneral visionGeneral=visionGeneralService.obtenerVisionGeneral("miid");
            visionGeneral.calcular(empresa.getCantidadVendida(),bimestre.getProduccion(),estadoResultados.getUtilidadNeta(),bimestre.getPrecioUnitario(),empresa.getPorcentajeDeMercado());

            Produccion produccion=produccionService.obtenerProduccion("miid");
            //corregir list
            List<CostosProduccion> costosProduccionList=costosProduccionService.obtenerEstadoResultaodsPorNumeroYJuego("cod",1);
            List<Bimestre> bimestreList =bimestreService.obtenerBimestrePorNumeroYJuego("cod",1);
            List<Ventas> ventasList =ventasService.obtenerVentasPorNumeroYJuego("cod",1);

            produccion.calcular(bimestreList,costosProduccionList);
             VentasIndustria ventasIndustria=ventasIndustriaService.obtenerVentasIndustria("miid");
             ventasIndustria.calcular(bimestreList,ventasList);

        }
    }
    @GetMapping(value="/estadoResultados/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EstadoResultados getEstadoResultados(@PathVariable String id) throws Exception {
        return estadoResultadosService.obtenerEstadoResultado(id);
    }

    @GetMapping(value="/balanceGeneral/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BalanceGeneral getBalance(@PathVariable String id) throws Exception {
        return balanceGeneralService.obtenerBalance(id);
    }

    @GetMapping(value="/ventas/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Ventas getVentas(@PathVariable String id) throws Exception {
        return ventasService.obtenerVentas(id);
    }

    @GetMapping(value="/costosProduccion/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CostosProduccion getCostos(@PathVariable String id) throws Exception {
        return costosProduccionService.obtenerCostoProduccion(id);
    }

    @GetMapping(value="/estadoResultadosEmpresa/{empresa}")
    @ResponseStatus(HttpStatus.OK)
    public List<EstadoResultados> getAll(@PathVariable String empresa) throws Exception {
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
                    empresa.set_id(empresa.getNombre()+codigo);
                    empresaService.save(empresa);
                    VisionGeneral visionGeneral =new VisionGeneral();
                    Produccion produccion= new Produccion();
                    VentasIndustria ventasIndustria=new VentasIndustria();
                    visionGeneral.setNombreEmpresa(empresa.getNombre());
                    visionGeneralService.save(visionGeneral);
                    produccion.setNombreEmpresa(empresa.getNombre());
                    produccionService.save(produccion);
                    ventasIndustria.setNombreEmpresa(empresa.getNombre());                    
                    ventasIndustriaService.save(ventasIndustria);

                }

        }
    }
    @PostMapping(value="/juego")
    @ResponseStatus(HttpStatus.OK)
    public void crearJuego(@RequestBody Juego juego) throws Exception {
        juegoService.save(juego);
    }

}
