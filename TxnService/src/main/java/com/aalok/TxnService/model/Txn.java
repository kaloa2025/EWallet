package com.aalok.TxnService.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Txn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;
    @CreationTimestamp
    private Data createdOn;
    @UpdateTimestamp
    private Data updatedOn;
    private String txnId;
    private double amount;
    private String sender;
    private String reciver;
    private String purpose;
    @Enumerated(value = EnumType.STRING)
    private TxnStatus txnStatus;
}
