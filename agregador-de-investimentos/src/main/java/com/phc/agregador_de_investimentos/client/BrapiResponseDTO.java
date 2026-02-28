package com.phc.agregador_de_investimentos.client;

import java.util.List;

public record BrapiResponseDTO(List<StockDTO> results) {
}
