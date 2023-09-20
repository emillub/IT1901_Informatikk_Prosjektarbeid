package gr2349.app;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileHandler {
    private final static String FILE_EXTENSION = ".bok";
    protected static String DIR_PATH = "src/main/resources/gr2349/app/books/book.txt";

    public void writeBookToFile(Book book){
        try {
            FileOutputStream fos = new FileOutputStream(DIR_PATH);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(book);
            oos.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    

    public void readBookFromFile(String filePath){
        try{
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            System.out.println(ois.readObject());
            ois.close();

        } catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public static void main(String[] args) {
        Book book = new Book("Title", "a", "author", 5);
        FileHandler fh = new FileHandler();
        fh.writeBookToFile(book);
        fh.readBookFromFile(DIR_PATH);
    }

}
