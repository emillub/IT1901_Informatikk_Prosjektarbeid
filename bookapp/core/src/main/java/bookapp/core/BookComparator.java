package bookapp.core;

import java.util.Comparator;

public class BookComparator implements Comparator<Book>{
    protected static final String RATING = "rating";
    protected static final String AUTHOR_NAME = "authorName";
    protected static final String BOOK_TITLE = "ttile";
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
            System.out.println("Sorting by author");
            return o1.getAuthor().compareTo(o2.getAuthor());
        } 
        return o1.getTitle().compareTo(o2.getTitle());
    }
    
}
