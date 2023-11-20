package jrails;

import java.util.*;
import java.lang.reflect.*;
import java.io.*;

public class Model {
    private static Map<Integer, Map<String, Object>> AllModels = new LinkedHashMap<Integer, Map<String, Object>>();
    public static int modelID = 0;
    public int currModelID = 0;
    private static int lastIDFromDB = 0;
    private static String dbFileName = "Book_DB.txt";
    public Model() {
        currModelID = 0;
    }

    public static void TestHelper()
    {
        System.out.println("All Books: " + AllModels);
    }

    public void save() {
        // int lastKey = 0;
        // for (Map.Entry<Integer, Map<String, Object>> entry : AllModels.entrySet()) {
        //     lastKey = entry.getKey();
        // }
        // modelID = lastKey;

        // if (modelID != 0)
        // {
        Model.readAndStoreData();
        // }

        /* get the class of the newly created model */
        Class<?> runtimeClassOfGivenClass = this.getClass();

        /* get info of the newly created model and store them */
        Field[] publicFields = runtimeClassOfGivenClass.getFields();
        Map<String, Object> infoOfAModel = getModelInfoAndStore(publicFields, runtimeClassOfGivenClass);
        System.out.println("ID is: " + this.id());
        if (this.currModelID == 0)
        {
            modelID = this.lastIDFromDB + 1;
            this.currModelID = modelID;
            AllModels.put(this.currModelID, infoOfAModel);
        }
        else
        {
            System.out.println("Already Exist");
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
        storeBookInDB(this.currModelID);
        TestHelper();
    }

    private static void readAndStoreData()
    {
        try
        {
            File file = new File(dbFileName);
            if(file.exists() && (file.length() != 0))
            {
                BufferedReader reader = new BufferedReader(new FileReader(dbFileName));
                String currLine = reader.readLine();

                Map<String, Object> fieldNameAndToValueKVP = new LinkedHashMap<String, Object>();
                List<Object> currLineInfo;
                while (currLine != null)
                {
                    currLineInfo = new ArrayList<>();
                    String currInfoOfCurrLine = "";
                    for (int i = 0; i < currLine.length(); i++)
                    {
                        char currChar = currLine.charAt(i);
                        //String fieldName = "";
                        if (currChar != ',' )
                        {
                            currInfoOfCurrLine += currChar;
                            //System.out.println("Curr: " + currInfoOfCurrLine);
                        }
                        else
                        {
                            //fieldName = currInfoOfCurrLine.substring(0, currInfoOfCurrLine.indexOf(':'));
                            currInfoOfCurrLine = currInfoOfCurrLine.trim();
                            currLineInfo.add(currInfoOfCurrLine);
                            currInfoOfCurrLine = "";
                            
                        }
                    }
                    /* add the last segment to currLineInfo after the loop ends */
                    if (!currInfoOfCurrLine.isEmpty()) {
                        currLineInfo.add(currInfoOfCurrLine);
                    }
                    
                    int id = 0;
                    Map<String, Object> currLineModel = new LinkedHashMap<String, Object>();
                    //System.out.println("CurrLine:" + currInfoOfCurrLine);
                    for (int j = 0; j < currLineInfo.size(); j++)
                    {
                        String fullInfo = (String)currLineInfo.get(j);
                        String fieldName = fullInfo.substring(0, fullInfo.indexOf(":"));
                        Object valueOfField = fullInfo.substring(fullInfo.indexOf(":") + 2, fullInfo.length());
                        //System.out.println("Full Info:" + fullInfo);

                        if (fieldName.contains("ID"))
                        {
                            id = Integer.valueOf((String) valueOfField);
                            lastIDFromDB = id;

                        }
                        else if (fieldName.contains("Model_Type"))
                        {
                            currLineModel.put("Model_Type", valueOfField);
                        }
                        else
                        {
                            if (valueOfField.toString().matches("[0-9]+"))
                            {
                                currLineModel.put(fieldName, Integer.valueOf((String) valueOfField));
                            }
                            else
                            {
                                currLineModel.put(fieldName, valueOfField);
                            }
                        }
                    }
                    AllModels.put(id, currLineModel);
                    
                    currLine = reader.readLine();
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void storeBookInDB(int currModelID)
    {
        try
        {
            String modelInfo = null;
            /* write all added models and their info, to the DB (i.e output file) */
            BufferedWriter writer = new BufferedWriter(new FileWriter(dbFileName));

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
                    //System.out.println("VAL: " + infoValue);
                    // String subStr = (String)infoValue;
                    // subStr = subStr.substring(1, subStr.length());
        
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
            System.out.println("An error occurred.");
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
                                if (field.get(this) != null || !field.get(this).toString().isEmpty())
                                {
                                    otherField = field.get(this);
                                    infoOfAModel.put(field.getName(), otherField);
                                    break;
                                }
                                else
                                {
                                    throw new IllegalArgumentException("Error in {getModelInfoAndStore} function: {}" + field.getName() + "} is null or empty.");
                                }
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error in {getModelInfoAndStore} function: " + e);
                    }
                }
                else
                {
                    throw new IllegalArgumentException("Error in {getModelInfoAndStore} function: Field with @Column annotation should be of type Boolean, String, or Integer.");
                }

            }
            //System.out.println("Field: " + field.getName());
        }
        // System.out.println("title: " + title);
        // System.out.println("author: " + author);
        // System.out.println("num_copies: " + num_copies);
        infoOfAModel.put("Model_Type", runtimeClassOfGivenClass);
        //System.out.println("RUN TIME TYpe: " + runtimeClassOfGivenClass);
        return infoOfAModel;
    }

    public int id() {
        Model.readAndStoreData();
        return this.currModelID;
    }

    public static <T> T find(Class<T> c, int id) {
        // Model m = new Model();
        Model.readAndStoreData();
        Object instanceOfAModel = null;
    
        try {
            Map<String, Object> infoOfAModel = AllModels.get(id);
            //System.out.println("HERE: " + infoOfAModel);
            
            /* given ID does not exist in the DB (i.e output file) */
            if (infoOfAModel == null)
            {
                return null;
            }
            else
            {
                /* get a model with the given ID, from the DB and duplicate (create) a new instance of it */
                Object test = infoOfAModel.get("Model_Type");
                //System.out.println("Find ID: " + id + "of ModelType: " + test);
                String classType = infoOfAModel.get("Model_Type").toString();
                classType = classType.substring("class ".length());
                Class<?> aModelRuntimeClass = Class.forName(classType);
                //Class<?> aModelRuntimeClass = (Class<?>) infoOfAModel.get("Model_Type");
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
                            //System.out.println("FIELD: " + field);
                            /* set the static modelID field of the newly duplicated model to the same value of the exist model that was found from the DB */
                            if (field.getName() == "currModelID")
                            {
                                field.set(instanceOfAModel, id);
                                setTo += ", ID: " + id;
                            }
                            else
                            {
                                if (field.getType().toString().contains("Object"))
                                {
                                    /* set all other public fields of the newly duplicated model to the same value of the exist model that was found from the DB */
                                    Object valueOfAnInfo = infoOfAModel.get(field.getName());
                                    field.set(instanceOfAModel, valueOfAnInfo);
                                    setTo += ", " + field.getName() + ": " + valueOfAnInfo;
                                }
                                else if (field.getType().toString().contains("String"))
                                {
                                    /* set all other public fields of the newly duplicated model to the same value of the exist model that was found from the DB */
                                    Object valueOfAnInfo = infoOfAModel.get(field.getName());
                                    field.set(instanceOfAModel, valueOfAnInfo.toString());
                                    setTo += ", " + field.getName() + ": " + valueOfAnInfo;
                                }
                                else if (field.getType().toString().contains("boolean") || (field.getType().toString().contains("Boolean")))
                                {
                                    /* set all other public fields of the newly duplicated model to the same value of the exist model that was found from the DB */
                                    Object valueOfAnInfo = infoOfAModel.get(field.getName());
                                    field.set(instanceOfAModel, Boolean.valueOf(valueOfAnInfo.toString()));
                                    setTo += ", " + field.getName() + ": " + valueOfAnInfo;
                                }
                                else if ((field.getType().toString().contains("int") || (field.getType().toString().contains("Integer"))))
                                {
                                    /* set all other public fields of the newly duplicated model to the same value of the exist model that was found from the DB */
                                    Object valueOfAnInfo = infoOfAModel.get(field.getName());
                                    field.set(instanceOfAModel, Integer.parseInt(valueOfAnInfo.toString()));
                                    setTo += ", " + field.getName() + ": " + valueOfAnInfo;
                                }
                                else
                                {
                                    /* set all other public fields of the newly duplicated model to the same value of the exist model that was found from the DB */
                                    Object valueOfAnInfo = infoOfAModel.get(field.getName());
                                    field.set(instanceOfAModel, field.getType().cast(valueOfAnInfo));
                                    setTo += ", " + field.getName() + ": " + valueOfAnInfo;
                                }
                            }
                        }
                        
                    }
                    //System.out.println("Set Fields to --> " + setTo);
                    /* use c.cast to safely cast the instance to the specified type */
                    //System.out.println("Creating: " + instanceOfAModel);
                    T instanceOfAModelClass = c.cast(instanceOfAModel);
                    return instanceOfAModelClass;
                }
                else
                {
                    return null;
                }
                
            }
        } catch (Exception e) {
            System.out.println("Error in {find} function: " + e);
            return null; // Or handle the error as needed
        }
    }

    public static <T> List<T> all(Class<T> c) {
        // Model m = new Model();
        Model.readAndStoreData();
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
        Model.readAndStoreData();
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
            storeBookInDB(this.currModelID);
        }
        // throw new UnsupportedOperationException();
    }

    public static void reset() {
        AllModels.clear();
        try
        {
            PrintWriter writer = new PrintWriter(dbFileName);
            writer.print("");
            writer.close();
        }
        catch (IOException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        AllModels.clear();
    }
}
