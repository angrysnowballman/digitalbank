package com.shark_industries.digitalbank.transferService.model;

import com.shark_industries.digitalbank.transferService.enums.StatusTransfer;

import java.rmi.server.UID;
import java.util.UUID;

public record TransferReponceResult(UUID id, StatusTransfer statusTransfer, String description) {

}
