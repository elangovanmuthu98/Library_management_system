/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.company.library_managementsystem.RepositoryClasses;

import com.company.library_managementsystem.Enum.TransactionStatus;
import com.company.library_managementsystem.Enum.TransactionType;
import com.company.library_managementsystem.Models.Book;
import com.company.library_managementsystem.Models.LibraryCard;
import com.company.library_managementsystem.Models.Transaction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Elangovan
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer>{


    @Query(nativeQuery = true,value = "SELECT * from transaction WHERE book_book_id LIKE %:b% AND library_card_card_no LIKE %:c% AND transaction_status LIKE %:ts% AND transaction_type LIKE %:tt%")
    List<Transaction> findTransactionsByBookAndLibraryCardAndTransactonStatusAndTransactionType(Integer b,Integer c,TransactionStatus ts, TransactionType tt);
    
    
}
