package com.meintrup.springcrud.entities;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Json object that contains an error message.
 */
@RequiredArgsConstructor
@Builder
@Data
public class ErrorDetails {
    private final String message;
}
