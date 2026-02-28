package com.phc.agregador_de_investimentos.mapper;

import com.phc.agregador_de_investimentos.dtos.CreateStockDTO;
import com.phc.agregador_de_investimentos.entities.Stock;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMapper {

    Stock toStock(CreateStockDTO createStockDTO);
}
