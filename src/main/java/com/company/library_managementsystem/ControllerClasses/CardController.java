/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.library_managementsystem.ControllerClasses;

import com.company.library_managementsystem.Models.LibraryCard;
import com.company.library_managementsystem.ServiceClasses.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Elangovan
 */
@RestController
@RequestMapping("/card")
@Slf4j
public class CardController {
    
    @Autowired
    private CardService cardservice;
    
    @PostMapping("/create")
    public ResponseEntity addCard(@RequestBody LibraryCard card)
    {
        try{
            String result=cardservice.addCard(card);
            
            return new ResponseEntity(result,HttpStatus.OK);
        }
        catch(Exception e)
        {
            log.error("The card details are invalid/alread here",e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
        
    }
    
    @PutMapping("/issueToStudent")
    public ResponseEntity issueStudent(@RequestParam("cardId") Integer cardId, @RequestParam("rollno") Integer rollno)
    {
        try{
            String result=cardservice.assosiateStudent(cardId,rollno);
            return new ResponseEntity(result,HttpStatus.OK);
        }
        catch(Exception e)
        {
            log.error("Error in assosiating card to student",e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
        
    }
    
    
}
