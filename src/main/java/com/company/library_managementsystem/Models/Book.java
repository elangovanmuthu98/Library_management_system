/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.library_managementsystem.Models;

import com.company.library_managementsystem.Enum.Genere;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Elangovan
 */
@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer bookId;
     public Book( String title, Boolean isAvailable, Genere genere, Date publicationDate, Integer price) {
        this.isAvailable = isAvailable;
        this.title = title;
        this.genere = genere;
        this.publicationDate = publicationDate;
        this.price = price;
    }
    
    private Boolean isAvailable;
    
    @Column(unique=true)
    private String title;
    
    @Enumerated(value=EnumType.STRING)
    private Genere genere;
    private Date publicationDate;
    private Integer price;
    
    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Author author;

   
   @OneToMany(mappedBy = "book",cascade=CascadeType.ALL)
   private List<Transaction> transactionList=new ArrayList<>();
    
}
