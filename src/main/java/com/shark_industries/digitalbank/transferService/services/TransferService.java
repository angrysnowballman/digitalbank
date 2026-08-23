package com.shark_industries.digitalbank.transferService.services;

import com.shark_industries.digitalbank.accountservice.model.Account;
import com.shark_industries.digitalbank.accountservice.services.AccountService;
import com.shark_industries.digitalbank.transferService.enums.StatusTransfer;
import com.shark_industries.digitalbank.transferService.model.Transfer;
import com.shark_industries.digitalbank.transferService.model.TransferReponceResult;
import com.shark_industries.digitalbank.transferService.model.TransferRepository;
import com.shark_industries.digitalbank.transferService.model.TransferRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TransferService {
    private final AccountService accountService;
    private final TransferRepository transferRepository;

    public TransferService(AccountService accountService, TransferRepository transferRepository) {
        this.accountService = accountService;
        this.transferRepository = transferRepository;
    }

    public boolean transferAccount(UUID fromAccount, UUID toAccount){
        if (accountService.getAccountByUUID(fromAccount) != null && accountService.getAccountByUUID(toAccount) != null){
            return true;
        }
        return false;
    }

    public boolean checkBalance(TransferRequest request){
        Account fromUser = accountService.getAccountByUUID(request.fromAccount());
        Account toUser = accountService.getAccountByUUID(request.toAccount());
        if(fromUser.getBalance().compareTo(request.amount()) >= 0){
            return true;
        } return false;
    }

    public BigDecimal transferMoney(TransferRequest request) {
        Account fromUser = accountService.getAccountByUUID(request.fromAccount());
        Account toUser = accountService.getAccountByUUID(request.toAccount());
        if(checkBalance(request)){
            toUser.setBalance(toUser.getBalance().add(request.amount()));
            fromUser.setBalance(fromUser.getBalance().subtract(request.amount()));
            //подсмотрел
            accountService.saveAccount(fromUser);
            accountService.saveAccount(toUser);
        }
        return request.amount();
    }

// TODO добавить поле UID
    public TransferReponceResult transfer(TransferRequest request) {
        if(checkBalance(request)){
            transferMoney(request);
            Transfer successTransfer = Transfer.builder()
                    .transferId(UUID.randomUUID())
                    .fromAccount(accountService.getAccountByUUID(request.fromAccount()))
                    .toAccount(accountService.getAccountByUUID(request.toAccount()))
                    .amount(request.amount())
                    .status(StatusTransfer.SUCCESS)
                    .createdAt(LocalDateTime.now())
                    .build();
            transferRepository.save(successTransfer);

            return new TransferReponceResult(request.toAccount(), StatusTransfer.SUCCESS, "payment success");
        }Transfer failedTransfer =  Transfer.builder()
                .transferId(UUID.randomUUID())
                .fromAccount(accountService.getAccountByUUID(request.fromAccount()))
                .toAccount(accountService.getAccountByUUID(request.toAccount()))
                .amount(request.amount())
                .status(StatusTransfer.FAILURE)
                .createdAt(LocalDateTime.now())
                .build();
        transferRepository.save(failedTransfer);
        return null;

//
    }
}
