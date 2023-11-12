package jrails;

import java.util.*;
import java.lang.reflect.*;
import java.io.*;

public class Model {
    public static Map<Integer, Map<String, Object>> AllModels = new LinkedHashMap<Integer, Map<String, Object>>();
    public static int modelID = 0;
    private int currModelID = 0;
    private static String dbFileName = "Book_DB.txt";
    public Model() {
        /* each model created has a unique ID */
        currModelID = modelID;
        AllModels.put(currModelID, null);
        modelID++;
    }

    public void save() {
        
        /* get the class of the newly created model */
        Class<?> runtimeClassOfGivenClass = this.getClass();

        /* get info of the newly created model and store them */
        Field[] publicFields = runtimeClassOfGivenClass.getFields();
        getModelInfoAndStore(publicFields);
        storeBookInDB();
        //System.err.println("Books: " + AllModels);
    }

    private void storeBookInDB()
    {
        String modelInfo = null;
        int currID = 0;
        System.out.println("Map size: " + AllModels.size());
        System.out.println("All Books: " + AllModels);
        System.err.println("ID: " +this.id());
        try
        {
            /* write all added models and their info, to the DB (i.e output file) */
            FileWriter myWriter = new FileWriter(dbFileName);
            for (Map.Entry<Integer, Map<String, Object>> model : AllModels.entrySet())
            {
                /* key KVP of model's ID and all of its info */
                int modelID = model.getKey();
                Map<String, Object> currModelInfo = model.getValue();
                modelInfo = "ID: " + modelID;
                System.out.println("Current model: " + currModelInfo);

                /* iterate through all of the model's info and print it to the DB (i.e output file) */
                for (Map.Entry<String, Object> infoOfCurrModel : currModelInfo.entrySet())
                {
                    /* key KVP of model's info and its value */
                    String infoKey = infoOfCurrModel.getKey();
                    Object infoValue = infoOfCurrModel.getValue();
        
                    modelInfo += ", " + infoKey + ": " + infoValue;
                }
                /* each row of the DB (i.e output file) correspond to a model and its info*/
                modelInfo += "\n";
                myWriter.write(modelInfo);
            }
            myWriter.close();
        }
        catch (IOException e)
        {
            System.err.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void getModelInfoAndStore(Field[] publicFields)
    {
        Map<String, Object> infoOfAModel = new LinkedHashMap<String, Object>();
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
                                // /* ensure that the model has title */
                                // if (title == null || title.isEmpty())
                                // {
                                //     throw new IllegalArgumentException("Error in {getModelInfoAndStore} function: {title} can't be null or empty.");
                                // }
                                // else
                                // {
                                infoOfAModel.put(field.getName(), title);
                                // }
                                break;
                            case "author":
                                author = field.get(this).toString();
                                // /* ensure that the model has author name */
                                // if (author == null || author.isEmpty())
                                // {
                                //     throw new IllegalArgumentException("Error in {getModelInfoAndStore} function: {author} can't be null or empty.");
                                // }
                                // else
                                // {
                                infoOfAModel.put(field.getName(), author);
                                // }
                                break;
                            case "num_copies":
                                num_copies = field.get(this);
                                /* ensure that the model doesn't have negative number of copies */
                                if ((int)num_copies < 0)
                                {
                                    throw new IllegalArgumentException("Error in {getModelInfoAndStore} function: {num_copies} can't be negative.");
                                }
                                else
                                {
                                    infoOfAModel.put(field.getName(), num_copies);
                                }
                                break;
                            case "bookID":
                                //infoOfAModel.put(field.getName(), modelID);
                                break;
                            default:
                                otherField = field.get(this);
                                infoOfAModel.put(field.getName(), otherField);
                                break;
                        }
                    }
                    catch (Exception e)
                    {
                        System.err.println("Error in {getModelInfoAndStore} function: " + e);
                    }
                }
                else
                {
                    throw new IllegalArgumentException("Error in {getModelInfoAndStore} function: Field with @Column annotation should be of type Boolean, String, or Integer.");
                }

            }
            //System.err.println("Field: " + field.getName());
        }
        // System.err.println("title: " + title);
        // System.err.println("author: " + author);
        // System.err.println("num_copies: " + num_copies);

        /* ensure that the ID of a model exist before modifying the model's info */
        if (!AllModels.containsKey(this.currModelID))
        {
            throw new IllegalArgumentException("Error in {getModelInfoAndStore} function: model with ID {" + this.currModelID + "} does not exist in the DB.");
        }
        else
        {
            AllModels.put(this.currModelID, infoOfAModel);
        }
    }

    public int id() {
        return this.currModelID;
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
        AllModels.clear();
    }
}
