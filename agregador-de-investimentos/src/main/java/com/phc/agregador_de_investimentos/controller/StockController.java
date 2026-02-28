package com.phc.agregador_de_investimentos.controller;

import com.phc.agregador_de_investimentos.dtos.CreateStockDTO;
import com.phc.agregador_de_investimentos.services.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/stocks")
public class StockController {

    private final StockService stockServices;

    public StockController(StockService stockServices) {
        this.stockServices = stockServices;
    }

    @PostMapping
    public ResponseEntity<Void> createStock(@RequestBody CreateStockDTO createStockDTO) {
        stockServices.createStock(createStockDTO);
        return ResponseEntity.ok().build();
    }

}
