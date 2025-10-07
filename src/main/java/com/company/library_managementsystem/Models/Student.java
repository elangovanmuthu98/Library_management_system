/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.library_managementsystem.Models;

import com.company.library_managementsystem.Enum.Department;
import com.company.library_managementsystem.Enum.Gender;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
public class Student {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer rollno;
    
    @Enumerated(value=EnumType.STRING)
    private Gender gender;
    
    @Enumerated(value=EnumType.STRING)
    private Department department;
    
    
    private String name;
    
    @Column(unique=true)
    private String emailId;
    
    @OneToOne(mappedBy="student",cascade=CascadeType.ALL)
    private LibraryCard librarycard;
}
