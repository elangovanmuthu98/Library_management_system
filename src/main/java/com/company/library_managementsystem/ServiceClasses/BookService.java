/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.library_managementsystem.ServiceClasses;

import com.company.library_managementsystem.Enum.Genere;
import com.company.library_managementsystem.Models.Author;
import com.company.library_managementsystem.Models.Book;
import com.company.library_managementsystem.RepositoryClasses.AuthorRepository;
import com.company.library_managementsystem.RepositoryClasses.BookRepository;
import com.company.library_managementsystem.RequestDto.AddBookRequestDto;
import com.company.library_managementsystem.ResponseDto.BookResponseDto;
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
public class BookService {
    
    @Autowired
    private AuthorRepository authorrepository;
    
    @Autowired
    private BookRepository bookrepository;
    
    public String addBook(AddBookRequestDto request) throws Exception
    {
        
        Optional<Author> optionalauthor=authorrepository.findById(request.getAuthorid());
        
        if(!optionalauthor.isPresent())
        {
            throw new Exception("Author Entered is Incorrect");
        }
        
        Author author= optionalauthor.get();
        
        Book book=new Book(request.getTitle(),request.getIsAvailable(),request.getGenere(),request.getPublicationDate(),request.getPrice());
        
        book.setAuthor(author);
        
        List<Book> list=author.getList();
        list.add(book);
        author.setList(list);
        
        authorrepository.save(author);
        return "Book has been Successfully added";
        
        
        
    }
    public List<BookResponseDto> getBookListByGenre(Genere genre)
    {
        List<Book> bookList=bookrepository.findBooksByGenere(genre);
        List<BookResponseDto> responselist=new ArrayList<>();
        
        for(Book book:bookList)
        {
            BookResponseDto bookResponseDto=new BookResponseDto(book.getIsAvailable(),book.getTitle(),book.getGenere(),book.getPublicationDate(),book.getPrice(),book.getAuthor().getName());
            
            responselist.add(bookResponseDto);
        }
        return responselist;
    }
    
}
