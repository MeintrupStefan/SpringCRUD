package com.meintrup.springcrud.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

public record TransferResponseDTO(Long senderId, Long receiverId, BigDecimal amount) {
}
