/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.library_managementsystem.ServiceClasses;

import com.company.library_managementsystem.Models.LibraryCard;
import com.company.library_managementsystem.Models.Student;
import com.company.library_managementsystem.RepositoryClasses.CardRepository;
import com.company.library_managementsystem.RepositoryClasses.StudentRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Elangovan
 */
@Service
public class CardService {
    
    @Autowired
    private CardRepository cardrepository;
    
    @Autowired 
    private StudentRepository studentrepository;
    
    public String addCard(LibraryCard card) throws Exception
    {
        if(card.getCard_no()!=null)
        {
            throw new Exception("CardNo should not be sent as a parameter");
        }
        
        cardrepository.save(card);
      return "Card has successfully been added to the database";
    }
    
    
    
    public String assosiateStudent(Integer cardNo,Integer rollno) throws Exception
    {
     if(!studentrepository.existsById(rollno))
     {
         throw new Exception("Student Id is Invalid");
     }
        
     if(!cardrepository.existsById(cardNo))
     {
         throw new Exception("Card No is Invalid");
     }
     Optional<Student> opt=studentrepository.findById(rollno);
     Student studobj=opt.get();
     
     Optional<LibraryCard> optlib=cardrepository.findById(cardNo);
        LibraryCard librarycard=optlib.get();
        
        
        librarycard.setStudent(studobj);
        
        studobj.setLibrarycard(librarycard);
        
        
        studentrepository.save(studobj);
     
     return "Student and card saved successfully";
    }
    
}
