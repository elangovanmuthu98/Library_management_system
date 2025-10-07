/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.library_managementsystem.ServiceClasses;

import com.company.library_managementsystem.CustomException.AuthorNotFoundException;
import com.company.library_managementsystem.Models.Author;
import com.company.library_managementsystem.Models.Book;
import com.company.library_managementsystem.RepositoryClasses.AuthorRepository;
import com.company.library_managementsystem.RequestDto.UpdateNameAndPenNameRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Elangovan
 */
@Service
public class AuthorService {
    
    @Autowired
    private AuthorRepository authorrepository;
    
    public String addAuthor(Author author) throws Exception
    {
        if(author.getAuthorId()!=null)
        {
            throw new Exception("Author Id Should not be sent as Parameter");
        }
        
        authorrepository.save(author);
        return "Author has been Successfully added";
    }
    
    
    public String UpdateNameAndPenName(UpdateNameAndPenNameRequest request)throws Exception{
        Optional<Author> authorOptional=authorrepository.findById(request.getAuthorId());
        
        if(!authorOptional.isPresent())
        {
            throw new Exception("AuthorId is Invalid");
        }
        
        Author author=authorOptional.get();
        
        author.setName(request.getNewName());
        author.setPenName(request.getNewPenName());
        
        authorrepository.save(author);
        return "author name and penname has been updated";
        
    }
    
    public Author getAuthorById(Integer authorId) throws AuthorNotFoundException 
    {
        Optional<Author> authorOptional=authorrepository.findById(authorId);
         if(!authorOptional.isPresent())
         {
             throw new AuthorNotFoundException("Author Not In the List");
         }
        Author author=authorOptional.get();
       
        return  author;
    }
    
    public List<String> getTitles(Integer authorid) throws Exception
    {
        Optional<Author> optionalAuthor=authorrepository.findById(authorid);
        
        if(!optionalAuthor.isPresent())
        {
            throw new Exception("Autor not in table");
        }
        
        Author author=optionalAuthor.get();
        
        List<Book> listBook=author.getList();
        List<String> listTitle=new ArrayList<>();
        
        for(Book k:listBook)
        {
            listTitle.add(k.getTitle());
            
            
        }
        return listTitle;
    }
    
    
}
