/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.library_managementsystem.ControllerClasses;

import com.company.library_managementsystem.CustomException.AuthorNotFoundException;
import com.company.library_managementsystem.Models.Author;
import com.company.library_managementsystem.RequestDto.UpdateNameAndPenNameRequest;
import com.company.library_managementsystem.ServiceClasses.AuthorService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/author")
@Slf4j
public class AuthorController {
    @Autowired
    private AuthorService authorservicce;
    
    @PostMapping("/add")
    public ResponseEntity addAuthor(@RequestBody Author author)
    {
        try{
            String result=authorservicce.addAuthor(author);
            return new ResponseEntity(result,HttpStatus.OK);
            
        }
        catch(Exception e)
        {
            log.error("Authot couldn't be added to the db {}",e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    
    
    @PutMapping("/updateNameAndPenName")
    public String updateAuthorNameAndPenName(@RequestBody UpdateNameAndPenNameRequest updateNameandPennameRequest)
    {
        try{
            String result=authorservicce.UpdateNameAndPenName(updateNameandPennameRequest);
            return result;
        }
        catch(Exception e)
        {
            return "Author Id is inValid"+e.getMessage();
            
        }
    }
    @GetMapping("/getAuthor")
    public ResponseEntity getAuthor(@RequestParam("authorId") Integer authorId){
        try{
            Author author= authorservicce.getAuthorById(authorId);
            return new ResponseEntity(author,HttpStatus.OK);
        }
        catch(AuthorNotFoundException e)
        {
           log.error("Author not exist",e.getMessage());
           return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        
      
        
    }
    
    @GetMapping("/getTitles")
    public ResponseEntity getTitles(@RequestParam("authorId") Integer authorId)
    {
        try{
            List<String> list=authorservicce.getTitles(authorId);
            
            return new ResponseEntity(list,HttpStatus.OK);
            
        }
        catch(Exception e)
        {
            log.error("Author not exist",e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
            
        }
    }
    
    
}
