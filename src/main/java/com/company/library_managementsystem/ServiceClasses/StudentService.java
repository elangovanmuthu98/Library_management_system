/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.library_managementsystem.ServiceClasses;

import com.company.library_managementsystem.Enum.Card_Status;
import com.company.library_managementsystem.Enum.Department;
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
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private CardRepository cardrepository;
    
    public String addStudent(Student student) throws Exception
    {
        if(student.getRollno()!=null)
        {
            throw new Exception("Id should not be sent as a parameter");
        }
        studentRepository.save(student);
        return "Student has been added successfully";
    }
    
    public Department getDepartmentById(Integer rolno) throws Exception
    {
     Optional<Student> optstd=studentRepository.findById(rolno);
     
     if(!optstd.isPresent())
     {
         throw new Exception("Rollno Entered is Invalid");
     }
     
     Student student=optstd.get();
     
     return student.getDepartment();
    }
    
    public Card_Status getCardStatus(Integer studentid) throws Exception
    {
        Optional<Student> std=studentRepository.findById(studentid);
        if(!std.isPresent())
        {
            throw new Exception("Student id is not in the table");
        }
        
        Student student=std.get();
        Optional<LibraryCard> librarycardopt=cardrepository.findById(student.getLibrarycard().getCard_no());
        if(!librarycardopt.isPresent())
        {
            throw new Exception("Card Not present");
        }
        LibraryCard libraryCard=librarycardopt.get();
        
        return libraryCard.getCardstatus();
        
    }
}
