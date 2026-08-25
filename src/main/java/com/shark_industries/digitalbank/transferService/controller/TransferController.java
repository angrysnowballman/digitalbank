package com.shark_industries.digitalbank.transferService.controller;


import com.shark_industries.digitalbank.accountservice.model.Account;
import com.shark_industries.digitalbank.authservice.model.User;
import com.shark_industries.digitalbank.transferService.model.TransferReponceResult;
import com.shark_industries.digitalbank.transferService.model.TransferRequest;
import com.shark_industries.digitalbank.transferService.services.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransferReponceResult> transfer(@RequestBody TransferRequest transferRequest) {

            return ResponseEntity.ok(transferService.transfer(transferRequest));
    }



}
