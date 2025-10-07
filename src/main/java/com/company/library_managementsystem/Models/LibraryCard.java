/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.library_managementsystem.Models;

import com.company.library_managementsystem.Enum.Card_Status;
import com.company.library_managementsystem.Models.Student;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
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
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class LibraryCard {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer card_no;
    
    @Enumerated(value=EnumType.STRING)
    private Card_Status cardstatus;
    
    private Integer noofBooksIsssued;
    
    @OneToOne
    @JoinColumn
    private Student student;
    
    @OneToMany(mappedBy = "libraryCard",cascade = CascadeType.ALL)
    private List<Transaction> transactionList=new ArrayList<>();
    
}
