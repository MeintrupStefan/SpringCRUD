package com.meintrup.springcrud.controllers.rest;

import com.meintrup.springcrud.dto.TransferRequestDTO;
import com.meintrup.springcrud.dto.TransferResponseDTO;
import com.meintrup.springcrud.entities.Account;
import com.meintrup.springcrud.entities.ErrorDetails;
import com.meintrup.springcrud.repositories.AccountRepository;
import com.meintrup.springcrud.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController {
    private final AccountService accountService;

    @PostMapping("")
    @Operation(summary = "Creates a new account in the db.", responses = {
            @ApiResponse(responseCode = "200", description = "Okay", content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = ""))
            ),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = "{\"message\"=\"Some sort fo erro message\"}"))
            ),
            @ApiResponse(responseCode = "500", description = "Server Error", content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = "{\"message\"=\"Some sort fo erro message\"}"))
            ),
    })
    public ResponseEntity<Account> postAccount(@RequestBody Account account) throws URISyntaxException {
        Account acc = accountService.storeAccount(account);
        URI targetUri = new URI(String.format("/api/v1/account/%d", acc.getId()));
        return ResponseEntity.created(targetUri).body(
                acc
        );
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Returns the account of a given user", responses = {
            @ApiResponse(responseCode = "200", description = "Get the account of user with id", useReturnTypeSchema = true
            ),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = "{\"message\"=\"Error message.\"}"))
            ),
            @ApiResponse(responseCode = "500", description = "Server Error", content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = "{\"message\"=\"Error message\"}"))
            ),
    })
    public ResponseEntity<Account> getAccount(@PathVariable("userId") Long id) throws NoSuchElementException {
        return ResponseEntity.ok().body(
                accountService.findAccount(id)
        );
    }

    @PostMapping("/transfer")
    @Operation(summary = "Attempt to transfer x amount of money from one acc to another", responses = {
            @ApiResponse(responseCode = "200", description = "Get the account of user with id", useReturnTypeSchema = true),
    })
    public ResponseEntity<TransferResponseDTO> postTransfer(
            @RequestParam Long senderId,
            @RequestParam Long receiverId,
            @RequestParam String amount
    ) throws IllegalArgumentException {
        BigDecimal amountVal = new BigDecimal(amount);
        Account sender = accountService.findAccount(senderId);
        Account receiver = accountService.findAccount(senderId);
        boolean success = accountService.transferMoney(
                sender,
                receiver,
                amountVal
        );

        if (!success) {
            throw new IllegalArgumentException("The Given transaction is not possible");
        }

        return ResponseEntity.ok().body(
                new TransferResponseDTO(
                        sender.getId(),
                        receiver.getId(),
                        amountVal
                )
        );
    }
}

