package com.example.library;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LibraryTest {
    private Library library;
    @BeforeEach
    public void setUp(){
        library = new Library();
    }
    @AfterEach
    public void tearDown(){
        library=null;
    }
    @Test
    public void shouldHaveOneBookWhenABookIsAdded() {
        // Añade libro a la instancia library.
        library.addBook(new Book("The Hobbit", "J.R.R. Tolkien", 1937));

        // Verifica que tiene un unico libro añadido.
        assertEquals(1, library.countBooks());
    }
    @Test
    public void shouldFindOneBookByAuthorWhenABookByTheAuthorIsAdded(){
        //Añade un libro a la libreria.
        library.addBook(new Book("The Hobbit", "J.R.R. Tolkien", 1937));

        // Buscamos libros con ese autor.
        List<Book> booksByAuthor = library.findBooksByAuthor("J.R.R. Tolkien");

        // Verify that one book by the author is found
        assertEquals(1, booksByAuthor.size());
        assertEquals("J.R.R. Tolkien", booksByAuthor.get(0).getAuthor());
    }
    @Test
    public void shouldDecreaseNumberofBooksWhenABookIsRemoved(){
        //Añade libros a la libreria (2 nuevos)
        Book aux = new Book("The Hobbit", "J.R.R. Tolkien", 1937);
        Book aux2 = new Book("Monkey", "SergiGamer", 2024);
        library.addBook(aux);
        library.addBook(aux2);
        //Quitamos un libro de library
        library.removeBook(aux);
        //Verificamos si el tamaño de library se ha modificado bien
        assertEquals(1, library.countBooks());
    }
    @Test
    public void shouldBeEmptyWhenLastBookIsRemoved(){
        //Añade un solo libro
        Book aux = new Book("Monkey", "SergiGamer", 2024);
        library.addBook(aux);
        //Lo quitamos
        library.removeBook(aux);
        //Comprobamos si esta vacio
        assertEquals(0, library.countBooks());
    }
}
