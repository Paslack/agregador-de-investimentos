package com.phc.agregador_de_investimentos.mapper;

import com.phc.agregador_de_investimentos.dtos.AccountResponseDTO;
import com.phc.agregador_de_investimentos.dtos.AccountStockResponseDTO;
import com.phc.agregador_de_investimentos.dtos.CreateAccountDTO;
import com.phc.agregador_de_investimentos.entities.Account;
import com.phc.agregador_de_investimentos.entities.AccountStock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account toEntityAccount(CreateAccountDTO dto);

    AccountResponseDTO toAccountResponseDTO(Account account);

    @Mapping(source = "id.stockId", target = "stockId")
    AccountStockResponseDTO toAccountStockResponseDTO(AccountStock accountStock);
}
