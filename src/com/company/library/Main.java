package com.company.library;

import java.util.List;

// Obtenir la llista de llibres de cada autor (amb el gènere i el número de copies en cada biblioteca)
public class Main {
    class Author {
        List<Book> books;
    }

    class Book {
        String title;
        List<Genere> generes;
        List<BookInLibrary> libraries;
    }

    class Genere {
        String title;
    }

    class Library {
        String name, city;
    }

    class BookInLibrary {
        Library library;
        int copies;
    }
}


//Obtenir la llista de llibres de cada biblioteca (amb el gènere i els autors).
class Main2 {
    class Library {
        List<Book> books;
    }

    class Book {
        String title;
        List<Author> authors;
        List<Genere> generes;
    }

    class Author{
        String name;
    }

    class Genere {
        String title;
    }
}

// Obtenir la llista de biblioteques on trobem cada llibre (amb el gènere, els autors i el número de còpies)
class Main3{
    class Book {
        List<BookInLibrary> libraries;
        List<Genere> generes;
        List<Author> authors;
    }

    class Library {
        String name, city;
    }

    class BookInLibrary {
        Library library;
        int copies;
    }

    class Author{
        String name;
    }

    class Genere {
        String title;
    }
}

// Obtenir la llista d'autors de cada gènere.
class Main4 {
    class Genere {
        String title;
        List<Author> authors;
    }

    class Author{
        String name;
    }
}