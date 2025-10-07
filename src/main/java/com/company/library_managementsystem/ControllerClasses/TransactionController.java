/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.library_managementsystem.ControllerClasses;

import com.company.library_managementsystem.ServiceClasses.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Elangovan
 */
@RestController
@RequestMapping("/transaction")
public class TransactionController {
    
    @Autowired
    private TransactionService transactionService;
    
    @PostMapping("/issueBook")
    public ResponseEntity issueBook(@RequestParam("bookId") Integer bookId,@RequestParam("cardId")Integer cardId){
        try{
           String result=transactionService.issueBook(bookId,cardId);
           return new ResponseEntity(result,HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
            
        }
        
    }
    
    @PostMapping("/returnBook")
    public ResponseEntity returnBook(@RequestParam("bookId") Integer bookId,@RequestParam("cardId") Integer cardId){
       
        String result=transactionService.returnBook(bookId, cardId);
        return new ResponseEntity(result,HttpStatus.OK);
        
    }
    
    @GetMapping("/getFineAmount")
    public Integer getFineAmount()
    {
        Integer result=transactionService.getFineAmountOftheYear();
        return result;
    }
    
}
