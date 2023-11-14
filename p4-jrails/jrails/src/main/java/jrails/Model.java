package jrails;

import java.util.*;
import java.lang.reflect.*;
import java.io.*;

public class Model {
    private static Map<Integer, Map<String, Object>> AllModels = new LinkedHashMap<Integer, Map<String, Object>>();
    public static int modelID = 0;
    public int currModelID = 0;
    private static String dbFileName = "Book_DB.txt";
    public Model() {
        currModelID = 0;
    }

    public static void TestHelper()
    {
        System.out.println("All Books: " + AllModels);
    }

    public void save() {
        
        /* get the class of the newly created model */
        Class<?> runtimeClassOfGivenClass = this.getClass();

        /* get info of the newly created model and store them */
        Field[] publicFields = runtimeClassOfGivenClass.getFields();
        Map<String, Object> infoOfAModel = getModelInfoAndStore(publicFields, runtimeClassOfGivenClass);
        if (this.currModelID == 0)
        {
            modelID++;
            this.currModelID = modelID;
            AllModels.put(this.currModelID, infoOfAModel); 
        }
        else
        {
            /* ensure that the ID of a model exist before modifying the model's info */
            if (!AllModels.containsKey(this.currModelID))
            {
                throw new IllegalArgumentException("Error in {getModelInfoAndStore} function: model with ID {" + this.modelID + "} does not exist in the DB.");
            }
            else
            {
                AllModels.put(this.currModelID, infoOfAModel);
            }
        }
        //TestHelper();
        storeBookInDB();
    }

    private void storeBookInDB()
    {
        String modelInfo = null;
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

    private Map<String, Object> getModelInfoAndStore(Field[] publicFields, Class<?> runtimeClassOfGivenClass)
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
        infoOfAModel.put("Model Type", runtimeClassOfGivenClass);
        return infoOfAModel;
    }

    public int id() {
        return this.currModelID;
    }

    public static <T> T find(Class<T> c, int id) {
        Object instanceOfAModel = null;
    
        try {
            Map<String, Object> infoOfAModel = AllModels.get(id);
            //System.err.println("HERE: " + infoOfAModel);
            
            /* given ID does not exist in the DB (i.e output file) */
            if (infoOfAModel == null)
            {
                return null;
            }
            else
            {
                /* get a model with the given ID, from the DB and duplicate (create) a new instance of it */
                Class<?> aModelRuntimeClass = (Class<?>) infoOfAModel.get("Model Type");
                Constructor<?> constructorOfGivenClass = aModelRuntimeClass.getDeclaredConstructor();
                instanceOfAModel = constructorOfGivenClass.newInstance();
                String setTo = "";
                /* ensure that the class of the model matches with the given class 'c' */
                if (c.isInstance(instanceOfAModel))
                {
                    /* get all field of the newly duplicated model */
                    Field[] allFields = aModelRuntimeClass.getFields();
                    for (Field field : allFields)
                    {
                        /* ensure that the field is public before accessing it */
                        if (Modifier.isPublic(field.getModifiers()) && !Modifier.isStatic(field.getModifiers()))
                        {
                            //System.err.println("FIELD: " + field);
                            /* set the static modelID field of the newly duplicated model to the same value of the exist model that was found from the DB */
                            if (field.getName() == "currModelID")
                            {
                                field.set(instanceOfAModel, id);
                                setTo += ", ID: " + id;
                            }
                            else
                            {
                                /* set all other public fields of the newly duplicated model to the same value of the exist model that was found from the DB */
                                Object valueOfAnInfo = infoOfAModel.get(field.getName());
                                field.set(instanceOfAModel, valueOfAnInfo);
                                setTo += ", " + field.getName() + ": " + valueOfAnInfo;
                            }
                        }
                        
                    }
                    //System.out.println("Set Fields to --> " + setTo);
                    /* use c.cast to safely cast the instance to the specified type */
                    T instanceOfAModelClass = c.cast(instanceOfAModel);
                    return instanceOfAModelClass;
                }
                else
                {
                    return null;
                }
                
            }
        } catch (Exception e) {
            System.err.println("Error in {find} function: " + e);
            return null; // Or handle the error as needed
        }
    }

    public static <T> List<T> all(Class<T> c) {
        List<T> allModelsWithTargetClass = new ArrayList<T>();
        for (Map.Entry<Integer, Map<String, Object>> model : AllModels.entrySet())
        {
            int modelID = model.getKey();
            T instanceOfModel = Model.find(c, modelID);
            if (instanceOfModel != null)
            {
                allModelsWithTargetClass.add(instanceOfModel);
            }
        }
        return allModelsWithTargetClass;
    }

    public void destroy() {
        Map<String, Object> infoOfAModel = AllModels.get(this.id());
        if (infoOfAModel == null)
        {
            throw new IllegalArgumentException("Error in {destroy} function: this book with ID {" + this.id() + "} does not exist in the DB.");
        }
        else
        {
            AllModels.remove(this.id());

            // int nextID = this.id() + 1;
            // while(AllModels.containsKey(nextID))
            // {
            //     int currID = nextID - 1;
            //     Map<String, Object> nextModel = AllModels.remove(nextID);
            //     AllModels.put(currID, nextModel);
            //     nextID++;
            // }
            storeBookInDB();
        }
        // throw new UnsupportedOperationException();
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
