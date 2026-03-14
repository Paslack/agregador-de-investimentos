package com.phc.agregador_de_investimentos.mapper;

import com.phc.agregador_de_investimentos.dtos.AccountResponseDTO;
import com.phc.agregador_de_investimentos.dtos.AccountStockResponseDTO;
import com.phc.agregador_de_investimentos.dtos.CreateAccountDTO;
import com.phc.agregador_de_investimentos.entities.Account;
import com.phc.agregador_de_investimentos.entities.AccountStock;
import com.phc.agregador_de_investimentos.entities.AccountStockId;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-14T16:08:33-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public Account toEntityAccount(CreateAccountDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Account account = new Account();

        account.setDescription( dto.description() );

        return account;
    }

    @Override
    public AccountResponseDTO toAccountResponseDTO(Account account) {
        if ( account == null ) {
            return null;
        }

        Long id = null;
        String description = null;

        id = account.getId();
        description = account.getDescription();

        AccountResponseDTO accountResponseDTO = new AccountResponseDTO( id, description );

        return accountResponseDTO;
    }

    @Override
    public AccountStockResponseDTO toAccountStockResponseDTO(AccountStock accountStock) {
        if ( accountStock == null ) {
            return null;
        }

        String stockId = null;
        int quantity = 0;

        stockId = accountStockIdStockId( accountStock );
        if ( accountStock.getQuantity() != null ) {
            quantity = accountStock.getQuantity();
        }

        double total = 0.0d;

        AccountStockResponseDTO accountStockResponseDTO = new AccountStockResponseDTO( stockId, quantity, total );

        return accountStockResponseDTO;
    }

    private String accountStockIdStockId(AccountStock accountStock) {
        if ( accountStock == null ) {
            return null;
        }
        AccountStockId id = accountStock.getId();
        if ( id == null ) {
            return null;
        }
        String stockId = id.getStockId();
        if ( stockId == null ) {
            return null;
        }
        return stockId;
    }
}
