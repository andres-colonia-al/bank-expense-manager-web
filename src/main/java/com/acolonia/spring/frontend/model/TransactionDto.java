package com.acolonia.spring.frontend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDto {

    private Long idTransaction;

    private String nameTransaction;

    private String descriptionTransaction;

    private Double amountTransaction;
    private String category;
    private LocalDateTime dateTime;
    private Long idUser;

}