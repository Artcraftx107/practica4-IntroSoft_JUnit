package com.example.library;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;

public class LibraryManagerTest {
    private LibraryManager libraryManager;
    private Library library;

    @BeforeEach
    public void setUp(){
        library = new Library();
        libraryManager = new LibraryManager(library);
    }

    @AfterEach
    public void tearDown(){
        library = null;
        libraryManager = null;
    }

    @Test
    public void shouldRemoveOnlyOneBookWithTitleAndReturnsTrue(){
        // Añadimos dos libros con mismo titulo pero distinto autor
        libraryManager.addNewBook("The Hobbit", "J.R.R. Tolkien", 1937);
        libraryManager.addNewBook("The Hobbit", "SergiGamer", 1937);

        // Intentamos eliminar un libro con ese titulo
        boolean isRemoved = libraryManager.removeBook("The Hobbit");

        // Verificamos que solo uno de los libors con ese titulo se ha borrado
        assertEquals(1, libraryManager.getTotalBooksInLibrary());
        assertTrue(isRemoved);
    }
    @Test
    public void shouldBeEmptyAfterAddingAndRemoving1000BooksInLessThan10Miliseconds(){
        //Verifica si el codigo ejecutado supera o no el tiempo limite de 10 ms
        assertTimeout(Duration.ofMillis(10), () -> {
            //Añadimos los 1000 libros
            for (int i = 0; i < 1000; i++) {
                libraryManager.addNewBook("Titulo"+i, "Autor"+i, 2024);
            }
            //Eliminamos los 1000 libros
            for (int j = 0; j < 1000; j++) {
                libraryManager.removeBook("Titulo"+j);
            }
            //verificamos que, en efecto, esos 1000 libros han sido eliminados
            assertEquals(0, libraryManager.getTotalBooksInLibrary());
        });
    }
}
