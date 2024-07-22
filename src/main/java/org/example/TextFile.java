package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TextFile {
    public int createTextFile(String textFileName, String pathOfParent, Map<String, String> allEntityMap) throws Exception {

        try {
            FileUtility.isValidTextFilePathConfig(textFileName, pathOfParent, allEntityMap);
        }
        catch (Exception e)
        {
            throw e;
        }
        allEntityMap.put(pathOfParent, textFileName);
        return 1;
    }

    public int deleteTextFile(String textFilePath, Map<String, String> allEntityMap) throws Exception{

        if(allEntityMap.containsKey(textFilePath))

        {
            List<String> allPaths=new ArrayList<>();
            for(Map.Entry<String,String> entry:allEntityMap.entrySet())
            {
                String key=entry.getKey();
                String value=entry.getValue();
                if(key.equals(textFilePath))
                    allPaths.add(key);
            }
            for(int i=0;i<allPaths.size();i++)
            {
                allEntityMap.remove(allPaths.get(i));
            }

            return 1;
        }
        throw new Exception("Path not found");
    }

    public int writeToFile(String filePath, String content, Map<String, String> allEntityMap, Map<String, String> allFileContentMap) throws Exception{
        try {
            FileUtility.isValidTextFilePathConfigExistance(filePath, allEntityMap);
        }
        catch (Exception e)
        {
            throw e;
        }
        allFileContentMap.put(filePath, content);
        return 1;
    }

    public int getSize(String textFilePath, Map<String, String> allEntityMap, Map<String, String> allFileContentMap) throws  Exception{
        try {
            FileUtility.isValidTextFilePathConfigExistance(textFilePath, allEntityMap);
        }
        catch (Exception e)
        {
            throw e;
        }
        return allFileContentMap.get(textFilePath).length();
    }
}
