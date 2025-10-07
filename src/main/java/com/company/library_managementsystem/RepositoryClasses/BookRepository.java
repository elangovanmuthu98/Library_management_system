/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.company.library_managementsystem.RepositoryClasses;

import com.company.library_managementsystem.Enum.Genere;
import com.company.library_managementsystem.Models.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Elangovan
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
    
    List<Book> findBooksByGenere(Genere genere);
    
    List<Book> findBooksByIsAvailableFalse();
    
}
