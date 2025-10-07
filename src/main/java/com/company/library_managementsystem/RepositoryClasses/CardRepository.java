/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.company.library_managementsystem.RepositoryClasses;

import com.company.library_managementsystem.Models.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Elangovan
 */
@Repository
public interface CardRepository extends JpaRepository<LibraryCard, Integer> {
    
}
