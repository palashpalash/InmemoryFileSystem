package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Folder {
    TextFile textFile;
    public int createFolder(String folderName, String pathOfParent, Map<String,String> allEntityMap) throws Exception{


        try {
            FileUtility.isValidFolderPathConfig(folderName, pathOfParent, allEntityMap);
        }
        catch (Exception e)
        {
            throw e;
        }
        allEntityMap.put(pathOfParent, folderName);
        return 1;




    }

    public int deleteFolder(String folderPath, Map<String, String> allEntityMap) throws Exception {
        if(allEntityMap.containsKey(folderPath))

        {
            List<String> allPaths=new ArrayList<>();
            for(Map.Entry<String,String> entry:allEntityMap.entrySet())
            {
                String key=entry.getKey();
                String value=entry.getValue();
                if(key.startsWith(folderPath))
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

    public int getSize(String folderPath, Map<String, String> allEntityMap, Map<String, String> allFileContentMap) throws  Exception{
        textFile=new TextFile();
        if(allEntityMap.containsKey(folderPath))
        {
            List<String> allPaths=new ArrayList<>();
            for(Map.Entry<String,String> entry:allEntityMap.entrySet())
            {
                String key=entry.getKey();
                String value=entry.getValue();
                if(key.startsWith(folderPath) && key.contains(".txt"))
                    allPaths.add(key);
            }
            int totalSize=0;
            for(int i=0;i<allPaths.size();i++)
            {
                totalSize+=textFile.getSize(allPaths.get(i), allEntityMap, allFileContentMap);
            }

            return totalSize;
        }
        else throw new Exception("Path Not Found");
    }
}
