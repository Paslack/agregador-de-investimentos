package com.phc.agregador_de_investimentos.controller;

import com.phc.agregador_de_investimentos.dtos.AccountStockResponseDTO;
import com.phc.agregador_de_investimentos.dtos.AssociateAccountStockDTO;
import com.phc.agregador_de_investimentos.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/{accountId}/stocks")
    public ResponseEntity<Void> associateStock(@PathVariable Long accountId,
                                               @RequestBody AssociateAccountStockDTO dto) {
        accountService.associateStock(accountId, dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{accountId}/stocks")
    public ResponseEntity<List<AccountStockResponseDTO>> getStocks(@PathVariable Long accountId) {
        return ResponseEntity.ok(accountService.getStocks(accountId));
    }
}
