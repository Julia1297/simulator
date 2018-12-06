package application.controllers;

import application.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class EmpresaController {
    private final BimestreService bimestreService;
    private final BalanceGeneralService balanceGeneralService;
    private final CostosProduccionService costosProduccionService;
    private final EstadoResultadosService estadoResultadosService;
    private final VentasService ventasService;

    @Autowired
    public EmpresaController(BimestreService bimestreService, BalanceGeneralService balanceGeneralService, CostosProduccionService costosProduccionService, EstadoResultadosService estadoResultadosService, VentasService ventasService) {
        this.bimestreService = bimestreService;
        this.balanceGeneralService=balanceGeneralService;
        this.costosProduccionService=costosProduccionService;
        this.estadoResultadosService=estadoResultadosService;
        this.ventasService=ventasService;
    }

}
