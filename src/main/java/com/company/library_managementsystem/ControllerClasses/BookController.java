/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.library_managementsystem.ControllerClasses;

import com.company.library_managementsystem.Enum.Genere;
import com.company.library_managementsystem.RequestDto.AddBookRequestDto;
import com.company.library_managementsystem.ResponseDto.BookResponseDto;
import com.company.library_managementsystem.ServiceClasses.BookService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Elangovan
 */
@RestController
@RequestMapping("/book")
@Slf4j
public class BookController {
    
    @Autowired
    private BookService bookservice;
    
    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody AddBookRequestDto addBookRequestDto)
    {
       try{
           String result=bookservice.addBook(addBookRequestDto);
           return new ResponseEntity(result,HttpStatus.OK);
       } 
       catch(Exception e)
       {
           return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
           
       }
    }
    
    @GetMapping("/getByGenre")
    public ResponseEntity getBookListByGenre(@RequestParam("genre") Genere genre)
    {
     List<BookResponseDto> responseList=bookservice.getBookListByGenre(genre);
     return new ResponseEntity(responseList,HttpStatus.OK);
    }
    
    
}
