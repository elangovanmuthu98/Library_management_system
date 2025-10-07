/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.library_managementsystem.ServiceClasses;

import com.company.library_managementsystem.CustomException.BookNotAvailableException;
import com.company.library_managementsystem.CustomException.BookNotFoundException;
import com.company.library_managementsystem.Enum.Card_Status;
import com.company.library_managementsystem.Enum.TransactionStatus;
import com.company.library_managementsystem.Enum.TransactionType;
import com.company.library_managementsystem.Models.Book;
import com.company.library_managementsystem.Models.LibraryCard;
import com.company.library_managementsystem.Models.Transaction;
import com.company.library_managementsystem.RepositoryClasses.BookRepository;
import com.company.library_managementsystem.RepositoryClasses.CardRepository;
import com.company.library_managementsystem.RepositoryClasses.TransactionRepository;
import java.util.Date;
import java.util.List;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Elangovan
 */
@Service
public class TransactionService {
    
    @Autowired
    private TransactionRepository transactionRepository;
    
//    @Value("${book.maxLimit}")
//    private Integer maxBookLimit;
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private CardRepository cardRepository;
    
    public static final Integer bookLimit=6;
    
    
    public String issueBook(Integer bookId,Integer cardId) throws Exception{
        
        Transaction transaction=new Transaction(TransactionStatus.PENDING,TransactionType.ISSUE,0);
        //book validation
        
        Optional<Book> optionalBook=bookRepository.findById(bookId);
        if(!optionalBook.isPresent())
        {
            throw new BookNotFoundException("Book Id is InCorrect");
        }
        Book book=optionalBook.get();
        if(book.getIsAvailable()==Boolean.FALSE)
        {
            throw new BookNotAvailableException("Book is Not Available");
        } 
        
        //card validation
        
        Optional<LibraryCard> optionalLibraryCard=cardRepository.findById(cardId);
        
        if(!optionalLibraryCard.isPresent())
        {
           throw new Exception("Card Id entered is Invalid"); 
        }
        
        LibraryCard card=optionalLibraryCard.get();
        
        if(!card.getCardstatus().equals(Card_Status.ACTIVATED))
        {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction=transactionRepository.save(transaction);
            
            throw new Exception("card is Not in Right Status");
        }
        
        if(card.getNoofBooksIsssued()>=bookLimit)
        {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction=transactionRepository.save(transaction);
            
            throw new Exception("Already max Limit Books are issued");
            
        }
        
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        
        book.setIsAvailable(Boolean.FALSE);
        card.setNoofBooksIsssued(card.getNoofBooksIsssued()+1);
        
        transaction.setBook(book);
        transaction.setLibraryCard(card);
        
        
        
        Transaction newTransactionWithId=transactionRepository.save(transaction);
        
        book.getTransactionList().add(newTransactionWithId);
        card.getTransactionList().add(newTransactionWithId);
        
        bookRepository.save(book);
        cardRepository.save(card);
        
        return "Transaction has been saved successfully";
        
        
        
        
    }
    
    public String returnBook(Integer bookId,Integer cardId){
        
        Book book=bookRepository.findById(bookId).get();
        LibraryCard card=cardRepository.findById(cardId).get();
        
        
        List<Transaction> transactionList=transactionRepository.findTransactionsByBookAndLibraryCardAndTransactonStatusAndTransactionType(book, card, TransactionStatus.SUCCESS, TransactionType.ISSUE);
        
        Transaction latestTransaction=transactionList.get(transactionList.size()-1);
        
        Date issueDate=latestTransaction.getCreatedAt();

          long milliSecondtime=Math.abs(System.currentTimeMillis()-issueDate.getTime());
          long no_of_days_issued=TimeUnit.DAYS.convert(milliSecondtime,TimeUnit.MILLISECONDS);
          
          int fineAmount=0;
          if(no_of_days_issued>15)
          {
              fineAmount=(int)((no_of_days_issued-15)*5);
          }
          
          book.setIsAvailable(Boolean.TRUE);
          card.setNoofBooksIsssued(card.getNoofBooksIsssued()-1);
          
          Transaction transaction=new Transaction(TransactionStatus.SUCCESS,TransactionType.RETURN,fineAmount);
          
          transaction.setBook(book);
          transaction.setLibraryCard(card);
          
          Transaction newTransactionbWithId=transactionRepository.save(transaction);
          
          book.getTransactionList().add(newTransactionbWithId);
          card.getTransactionList().add(newTransactionbWithId);
          
          bookRepository.save(book);
          cardRepository.save(card);
          
          return "Book has successfully been returned";
    }
    
    public Integer getFineAmountOftheYear()
    {
        List<Transaction> listOfTransaction=transactionRepository.findAll();

        int fine=0;
        for(Transaction transaction:listOfTransaction)
        {
            Date issueDate=transaction.getCreatedAt();
            Integer year=issueDate.getYear()-1900;
            
            if(year==2025)
            {
                fine+=transaction.getFineAmount();
            }
        }
        return fine;
    }
    
    
    
    
    
    
}
