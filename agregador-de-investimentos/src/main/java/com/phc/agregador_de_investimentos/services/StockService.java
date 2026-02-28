package com.phc.agregador_de_investimentos.services;

import com.phc.agregador_de_investimentos.dtos.CreateStockDTO;
import com.phc.agregador_de_investimentos.entities.Stock;
import com.phc.agregador_de_investimentos.mapper.StockMapper;
import com.phc.agregador_de_investimentos.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {

    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    public StockService(StockRepository stockRepository, StockMapper stockMapper) {
        this.stockRepository = stockRepository;
        this.stockMapper = stockMapper;
    }

    @Transactional
    public void createStock(CreateStockDTO createStockDTO) {
        Stock stock =  stockMapper.toStock(createStockDTO);
        stockRepository.save(stock);
    }
}
