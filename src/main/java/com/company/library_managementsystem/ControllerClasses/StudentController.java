/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.library_managementsystem.ControllerClasses;

import com.company.library_managementsystem.Enum.Card_Status;
import com.company.library_managementsystem.Enum.Department;
import com.company.library_managementsystem.Models.Student;
import com.company.library_managementsystem.ServiceClasses.StudentService;
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
@RequestMapping("/student")
@Slf4j
public class StudentController {
    
    @Autowired
    private StudentService studentService;
    
    
    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody Student student)
    {
       try{
           String result=studentService.addStudent(student);
           return new ResponseEntity(result,HttpStatus.CREATED);
           
       } 
       catch(Exception e)
       {
           log.error("Student not added",e.getMessage());
           return new ResponseEntity(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
           
       }
    }
    
    @GetMapping("/findDepartById")
    public ResponseEntity getDepartmentById(@RequestParam("rollno") Integer rollno){
        
        try{
            Department department=studentService.getDepartmentById(rollno);
            return new ResponseEntity(department,HttpStatus.OK);
        }
        catch(Exception e)
        {
            log.error("Department not found/Invaild Request",e.getMessage());
            return new ResponseEntity(null,HttpStatus.BAD_REQUEST);
            
        }
        
    }
    
    @GetMapping("/getCardStatusforStudent")
    public ResponseEntity getCardStatus(@RequestParam("studentId") Integer StudentId)
    {
        try{
            Card_Status result=studentService.getCardStatus(StudentId);
            return new ResponseEntity(result,HttpStatus.OK);
        }
        catch(Exception e)
        {
            log.error("Student Id invalid",e.getMessage());
            return new ResponseEntity(null,HttpStatus.BAD_REQUEST);
        }
    }
}
