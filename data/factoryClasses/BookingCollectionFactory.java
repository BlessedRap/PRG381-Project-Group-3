package data.factoryClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import data.collectionClasses.BookingCollection;
import data.generalClasses.Booking;

public class BookingCollectionFactory {
    public static String defaultFile = "bookings.csv";

    public static BookingCollection loadFromFile(String fileName) {
        BookingCollection bookingCollection = new BookingCollection();
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.length() > 1) {
                    bookingCollection.add(new Booking(data));
                } 
            }

            System.out.println("Bookings loaded");
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return bookingCollection;
    }

    public static BookingCollection loadFromFile() {
        return loadFromFile(defaultFile);
    }

    public static void outputToFile(String fileName, BookingCollection bookingCollection) {
        try (FileWriter myWriter = new FileWriter(fileName)) {
            File myObj = new File(fileName);
            if (myObj.canWrite()) {                
                for (Booking book : bookingCollection) {
                    myWriter.write(String.format("%s%n", book.toLine()));
                }
            } else {
                System.out.println("Can't write to file.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void outputToFile(BookingCollection bookingCollection) {
        outputToFile(defaultFile, bookingCollection);
    }
}
