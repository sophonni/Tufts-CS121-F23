package jrails;

import java.util.*;
import java.lang.reflect.*;
import java.io.*;

public class Model {
    public static Map<Integer, Map<String, Object>> Books_to_Info_Mapping = new LinkedHashMap<Integer, Map<String, Object>>();
    public static int bookID = 0;
    private int currBookID = 0;
    private static String dbFileName = "Book_DB.txt";
    public Model() {
        /* each model created has a unique ID */
        currBookID = bookID;
        Books_to_Info_Mapping.put(currBookID, null);
        bookID++;
    }

    public void save() {
        
        /* get the class of the newly created book */
        Class<?> runtimeClassOfGivenClass = this.getClass();

        /* get info of the newly created book and store them */
        Field[] publicFields = runtimeClassOfGivenClass.getFields();
        getBookInfoAndStore(publicFields);
        storeBookInDB();
        //System.err.println("Books: " + Books_to_Info_Mapping);
    }

    private void storeBookInDB()
    {
        String bookInfo = null;
        int currID = 0;
        System.out.println("Map size: " + Books_to_Info_Mapping.size());
        System.out.println("All Books: " + Books_to_Info_Mapping);
        System.err.println("ID: " +this.id());
        try
        {
            /* write all added models and their info, to the DB (i.e output file) */
            FileWriter myWriter = new FileWriter(dbFileName);
            for (Map.Entry<Integer, Map<String, Object>> book : Books_to_Info_Mapping.entrySet())
            {
                /* key KVP of model's ID and all of its info */
                int bookID = book.getKey();
                Map<String, Object> currBookInfo = book.getValue();
                bookInfo = "ID: " + bookID;
                System.out.println("Current Book: " + currBookInfo);

                /* iterate through all of the model's info and print it to the DB (i.e output file) */
                for (Map.Entry<String, Object> infoOfCurrBook : currBookInfo.entrySet())
                {
                    /* key KVP of model's info and its value */
                    String infoKey = infoOfCurrBook.getKey();
                    Object infoValue = infoOfCurrBook.getValue();
        
                    bookInfo += ", " + infoKey + ": " + infoValue;
                }
                /* each row of the DB (i.e output file) correspond to a model and its info*/
                bookInfo += "\n";
                myWriter.write(bookInfo);
            }
            myWriter.close();
        }
        catch (IOException e)
        {
            System.err.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void getBookInfoAndStore(Field[] publicFields)
    {
        Map<String, Object> bookInfo_to_valueOfInfo_mapping = new LinkedHashMap<String, Object>();
        String title = null;
        String author = null;
        Object num_copies = 0;
        Object otherField;
        for (Field field : publicFields)
        {
            if (field.isAnnotationPresent(Column.class))
            {
                Class<?> fieldType = field.getType();
                /* ensure that fields that use @Column as annotation can only be String, int, or boolean */
                if (fieldType == String.class || fieldType == int.class || fieldType == boolean.class)
                {
                    try
                    {
                        switch(field.getName())
                        {
                            case "title":
                                title = field.get(this).toString();
                                // /* ensure that the book has title */
                                // if (title == null || title.isEmpty())
                                // {
                                //     throw new IllegalArgumentException("Error in {getBookInfoAndStore} function: {title} can't be null or empty.");
                                // }
                                // else
                                // {
                                bookInfo_to_valueOfInfo_mapping.put(field.getName(), title);
                                // }
                                break;
                            case "author":
                                author = field.get(this).toString();
                                // /* ensure that the book has author name */
                                // if (author == null || author.isEmpty())
                                // {
                                //     throw new IllegalArgumentException("Error in {getBookInfoAndStore} function: {author} can't be null or empty.");
                                // }
                                // else
                                // {
                                bookInfo_to_valueOfInfo_mapping.put(field.getName(), author);
                                // }
                                break;
                            case "num_copies":
                                num_copies = field.get(this);
                                /* ensure that the book doesn't have negative number of copies */
                                if ((int)num_copies < 0)
                                {
                                    throw new IllegalArgumentException("Error in {getBookInfoAndStore} function: {num_copies} can't be negative.");
                                }
                                else
                                {
                                    bookInfo_to_valueOfInfo_mapping.put(field.getName(), num_copies);
                                }
                                break;
                            case "bookID":
                                //bookInfo_to_valueOfInfo_mapping.put(field.getName(), bookID);
                                break;
                            default:
                                otherField = field.get(this);
                                bookInfo_to_valueOfInfo_mapping.put(field.getName(), otherField);
                                break;
                        }
                    }
                    catch (Exception e)
                    {
                        System.err.println("Error in {getBookInfoAndStore} function: " + e);
                    }
                }
                else
                {
                    throw new IllegalArgumentException("Error in {getBookInfoAndStore} function: Field with @Column annotation should be of type Boolean, String, or Integer.");
                }

            }
            //System.err.println("Field: " + field.getName());
        }
        // System.err.println("title: " + title);
        // System.err.println("author: " + author);
        // System.err.println("num_copies: " + num_copies);

        /* ensure that the ID of a model exist before modifying the model's info */
        if (!Books_to_Info_Mapping.containsKey(this.currBookID))
        {
            throw new IllegalArgumentException("Error in {getBookInfoAndStore} function: book with ID {" + this.currBookID + "} does not exist in the DB.");
        }
        else
        {
            Books_to_Info_Mapping.put(this.currBookID, bookInfo_to_valueOfInfo_mapping);
        }
    }

    public int id() {
        return this.currBookID;
    }

    public static <T> T find(Class<T> c, int id) {
        throw new UnsupportedOperationException();
    }

    public static <T> List<T> all(Class<T> c) {
        // Returns a List<element type>
        throw new UnsupportedOperationException();
    }

    public void destroy() {
        throw new UnsupportedOperationException();
    }

    public static void reset() {
        try
        {
            PrintWriter writer = new PrintWriter(dbFileName);
            writer.print("");
            writer.close();
        }
        catch (IOException e)
        {
            System.err.println("An error occurred.");
            e.printStackTrace();
        }
        Books_to_Info_Mapping.clear();
    }
}
