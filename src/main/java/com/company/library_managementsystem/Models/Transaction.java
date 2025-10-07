/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.library_managementsystem.Models;

import com.company.library_managementsystem.Enum.TransactionStatus;
import com.company.library_managementsystem.Enum.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author Elangovan
 */
@Entity
@Table(name="transaction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;
    
    @CreationTimestamp
    private Date createdAt;
    
    
    @UpdateTimestamp
    private Date updatedAt;
    
     @Enumerated(value=EnumType.STRING)
     private TransactionStatus transactionStatus;
     
     @Enumerated(value = EnumType.STRING)
     private TransactionType transactionType;
     
    
     private Integer fineAmount;

    public Transaction(TransactionStatus transactionStatus, TransactionType transactionType, Integer fineAmount) {
        this.transactionStatus = transactionStatus;
        this.transactionType = transactionType;
        this.fineAmount = fineAmount;
    }
     
     
     
     
     @ManyToOne
     @JoinColumn
     @JsonIgnore
     private Book book;
     
     @ManyToOne
     @JoinColumn
     @JsonIgnore
     private LibraryCard libraryCard;
     
    
    
}
