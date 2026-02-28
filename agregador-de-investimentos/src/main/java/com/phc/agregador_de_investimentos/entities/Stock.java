package com.phc.agregador_de_investimentos.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_stocks")
public class Stock {

    @Id
    private String stockId;

    String description;


    public Stock() {
    }

    public Stock(String stockId, String description) {
        this.stockId = stockId;
        this.description = description;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
