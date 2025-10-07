/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.library_managementsystem.RequestDto;

import com.company.library_managementsystem.Enum.Genere;
import com.company.library_managementsystem.Models.Author;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author Elangovan
 */
@Getter
@Setter
public class AddBookRequestDto {
    
    
    private Boolean isAvailable;
    private String title;
     private Genere genere;
    private Date publicationDate;
    private Integer price;
     private Integer authorid;

    
}
