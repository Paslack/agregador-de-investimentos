package com.phc.agregador_de_investimentos.mapper;

import com.phc.agregador_de_investimentos.dtos.CreateStockDTO;
import com.phc.agregador_de_investimentos.entities.Stock;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-14T16:08:33-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class StockMapperImpl implements StockMapper {

    @Override
    public Stock toStock(CreateStockDTO createStockDTO) {
        if ( createStockDTO == null ) {
            return null;
        }

        Stock stock = new Stock();

        stock.setStockId( createStockDTO.stockId() );
        stock.setDescription( createStockDTO.description() );

        return stock;
    }
}
