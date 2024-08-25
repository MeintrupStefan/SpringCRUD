package com.meintrup.springcrud.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

public record TransferRequestDTO(Long senderId, Long receiverId, BigDecimal amount) {
}
