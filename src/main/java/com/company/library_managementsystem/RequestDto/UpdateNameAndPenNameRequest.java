/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.library_managementsystem.RequestDto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Elangovan
 */
@Getter
@Setter
public class UpdateNameAndPenNameRequest {
    
    private Integer authorId;
    private String newName;
    private String newPenName;
    
}
