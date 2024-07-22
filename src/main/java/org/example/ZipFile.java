package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ZipFile {

    TextFile textFile;
    public int createZipFile(String zipFileName, String pathOfParent, Map<String, String> allEntityMap) throws Exception {

        try {
            FileUtility.isValidZipFilePathConfig(zipFileName, pathOfParent, allEntityMap);
        }
        catch (Exception e)
        {
            throw e;
        }
        allEntityMap.put(pathOfParent, zipFileName);
        return 1;
    }

    public int deleteZipFile(String zipFilePath, Map<String, String> allEntityMap)  throws  Exception{

        if(allEntityMap.containsKey(zipFilePath))

        {
            List<String> allPaths=new ArrayList<>();
            for(Map.Entry<String,String> entry:allEntityMap.entrySet())
            {
                String key=entry.getKey();
                String value=entry.getValue();
                if(key.startsWith(zipFilePath))
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

    public int getSize(String zipFilePath, Map<String, String> allEntityMap, Map<String, String> allFileContentMap) throws Exception{

        textFile=new TextFile();
        if(allEntityMap.containsKey(zipFilePath))
        {
            List<String> allPaths=new ArrayList<>();
            for(Map.Entry<String,String> entry:allEntityMap.entrySet())
            {
                String key=entry.getKey();
                String value=entry.getValue();
                if(key.startsWith(zipFilePath) && key.contains(".txt"))
                    allPaths.add(key);
            }
            int totalSize=0;
            for(int i=0;i<allPaths.size();i++)
            {
                totalSize+=textFile.getSize(allPaths.get(i), allEntityMap, allFileContentMap);
            }

            return totalSize/2;
        }
        else throw new Exception("Path Not Found");
    }
}
