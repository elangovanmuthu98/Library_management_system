/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.library_managementsystem.ResponseDto;

import com.company.library_managementsystem.Enum.Genere;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Elangovan
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDto {
   
   
    
    private Boolean isAvailable;
    
    
    private String title;
    
    
    private Genere genere;
    private Date publicationDate;
    private Integer price;
    
    
    private String authorName;
    
}
