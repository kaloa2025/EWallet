package com.aalok.WalletService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Service;

import java.util.Date;

@Entity@Getter@Setter@NoArgsConstructor@AllArgsConstructor@Builder
public class Wallet {
    private Integer userId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private Double balance;
    @CreationTimestamp
    private Date createdOn;
    @UpdateTimestamp
    private Date UpdatedOn;
}
