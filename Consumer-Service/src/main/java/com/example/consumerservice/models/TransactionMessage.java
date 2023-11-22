package com.example.consumerservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity(name = "transactions")
public class TransactionMessage {

    @Id
    private Long transactionId;
    private Event event;
    private Double amount;
    private Status status;

    public enum Event{
        WITHDRAW , DEPOSIT
    }

    public enum Status{
        SUBMITTED , STARTED , PENDING , FINISHED , TERMINATED
    }

}
