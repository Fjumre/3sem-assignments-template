package app;


import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Streams {


    public static void main(String[] args) {

        List<Book> bookList = Arrays.asList(
                new Book("To Kill a Mockingbird", "Harper Lee", 1960, 281, 4.8),
                new Book("1984", "George Orwell", 1949, 328, 4.7),
                new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, 208, 4.4),
                new Book("Pride and Prejudice", "Jane Austen", 1813, 279, 4.6),
                new Book("The Catcher in the Rye", "J.D. Salinger", 1951, 234, 4.0),
                new Book("Sapiens: A Brief History of Humankind", "Yuval Noah Harari", 2011, 443, 4.7),
                new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", 1997, 309, 4.8),
                new Book("Harry Potter and the Chamber of Secrets", "J.K. Rowling", 1998, 251, 4.7)
        );

        //Find the average rating of all books.
        double totalRating = 0;
        for (Book book : bookList) {
            double rating = book.getRating();
            totalRating += rating;
        }
        double averageRating = totalRating / bookList.size();
        System.out.println("Average rating: " + averageRating + "\n");


        //Filter and display books published after a specific year.
        int year = 1959;
        System.out.println("Books published after " + year + ":");
        Predicate<Book> afterYear = book -> book.getPublicationYear() > year;
        bookList.stream().
                filter(afterYear).
                forEach(System.out::println);

        System.out.println();

        //Sort books by rating in descending order.
        System.out.println("By rating in descending order:");
        bookList.stream()
                .sorted(Comparator.comparingDouble(Book::getRating).reversed())
                .forEach(System.out::println);

        System.out.println();

        //Find and display the title of the book with the highest rating.
        bookList.stream().
                max(Comparator.comparingDouble(Book::getRating)).
                ifPresent(item -> System.out.println(item + "\n"));


        //Group books by author and calculate the average rating for each author's books.
        Map<String, Double> averageAuthorRating = bookList.stream()
                .collect(Collectors.groupingBy(Book::getAuthor, Collectors.averagingDouble(Book::getRating)));
        averageAuthorRating.forEach((author, authorRating) ->
                System.out.println("Average book rating for "+ author +": " + authorRating));

        System.out.println();

        //Calculate the total number of pages for all books (assuming each book has a fixed number of pages).
        int totalPages=0;
        for (Book book : bookList) {
            int pages = book.getPages();
            totalPages += pages;

        }
        System.out.println("Total number of pages: " + totalPages + "\n");

        List<String> filteredSortedTitles = bookList.stream()
                // Filter books published after 1950
                .filter(book -> book.getPublicationYear() > year)
                // Sort the remaining books by rating in descending order
                .sorted(Comparator.comparingDouble(Book::getRating).reversed())
                // Map each Book object to its title
                .map(Book::getTitle)
                // Collect the resulting titles into a list
                .collect(Collectors.toList());

        // Print the titles of filtered and sorted books
        System.out.println("Titles of books published after " + year + " and sorted by rating:");
        filteredSortedTitles.forEach(System.out::println);

        System.out.println();


        //Collect all book titles into a list/Collect
        List<String> titles = bookList.stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());

        System.out.println("Book Titles:");
        titles.forEach(System.out::println);

        System.out.println();

        //Print all books with a rating higher than 4.5/forEach
        System.out.println("Highly Rated Books:");
        bookList.stream()
                .filter(book -> book.getRating() > 4.5)
                .forEach(book -> System.out.println(book.getTitle() + " - Rating: " + book.getRating()));

        System.out.println();


        //Calculate the total number of pages in all books/Reduce
        int totalNumberOfPages = bookList.stream()
                .map(Book::getPages)
                .reduce(0, Integer::sum);

        System.out.println("Total Number of Pages in All Books: " + totalNumberOfPages);


    }

}