package bookapp.core;

import java.util.Comparator;

public class BookComparator implements Comparator<Book>{
    public static final String RATING = "Rating";
    public static final String AUTHOR_NAME = "Author name";
    public static final String BOOK_TITLE = "Title";
    private String sortBy = RATING;

    public BookComparator(){}

    public BookComparator(String sortBy){
        this.sortBy = sortBy;
    }
    
    @Override
    public int compare(Book o1, Book o2) {
        if (sortBy.equals(RATING)) {
            return Float.compare(o2.getAverageRating(), o1.getAverageRating());
        } else if (sortBy.equals(AUTHOR_NAME)) {
            return o1.getAuthor().compareTo(o2.getAuthor());
        } 
        return o1.getTitle().compareTo(o2.getTitle());
    }
    
}
