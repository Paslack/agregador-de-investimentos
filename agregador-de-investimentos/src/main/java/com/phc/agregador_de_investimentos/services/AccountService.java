package com.phc.agregador_de_investimentos.services;

import com.phc.agregador_de_investimentos.client.BrapiClient;
import com.phc.agregador_de_investimentos.client.BrapiResponseDTO;
import com.phc.agregador_de_investimentos.dtos.AccountStockResponseDTO;
import com.phc.agregador_de_investimentos.dtos.AssociateAccountStockDTO;
import com.phc.agregador_de_investimentos.entities.Account;
import com.phc.agregador_de_investimentos.entities.AccountStock;
import com.phc.agregador_de_investimentos.entities.AccountStockId;
import com.phc.agregador_de_investimentos.entities.Stock;
import com.phc.agregador_de_investimentos.exceptions.ResourceNotFoundException;
import com.phc.agregador_de_investimentos.mapper.AccountMapper;
import com.phc.agregador_de_investimentos.repository.AccountRepository;
import com.phc.agregador_de_investimentos.repository.AccountStockRepository;
import com.phc.agregador_de_investimentos.repository.StockRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountService {

    @Value("#{environment.TOKEN}")
    private String TOKEN;

    private final AccountRepository accountRepository;
    private final StockRepository stockRepository;
    private final AccountStockRepository accountStockRepository;
    private final AccountMapper accountMapper;
    private final BrapiClient brapiClient;


    public AccountService(AccountRepository accountRepository, StockRepository stockRepository, AccountStockRepository accountStockRepository, AccountMapper accountMapper, BrapiClient brapiClient) {
        this.accountRepository = accountRepository;
        this.stockRepository = stockRepository;
        this.accountStockRepository = accountStockRepository;
        this.accountMapper = accountMapper;
        this.brapiClient = brapiClient;
    }

    @Transactional
    public void associateStock(Long accountId, AssociateAccountStockDTO dto) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(()-> new ResourceNotFoundException("Account not found"));

        Stock stock = stockRepository.findById(dto.stockId())
                .orElseThrow(()-> new ResourceNotFoundException("Stock not found"));

        AccountStockId accountStockId = new AccountStockId(account.getId(), stock.getStockId());
        AccountStock accountStock = new AccountStock(accountStockId, account, stock, dto.quantity());

        accountStockRepository.save(accountStock);
    }

    @Transactional(readOnly = true)
    public List<AccountStockResponseDTO> getStocks(Long accountId) {
       Account account = accountRepository.findById(accountId)
               .orElseThrow(()-> new ResourceNotFoundException("Account not found"));

       return account.getAccountStockList().stream()
               .map(as -> new AccountStockResponseDTO(
                       as.getStock().getStockId(),
                       as.getQuantity(),
                       getTotal(as.getQuantity(), as.getStock().getStockId())
               )).toList();
    }

    public double getTotal(Integer quantity, String stockId) {
        BrapiResponseDTO response = brapiClient.getQuote(TOKEN, stockId);
        Double price = response.results().getFirst().regularMarketPrice();
        return quantity * price;
    }
}
