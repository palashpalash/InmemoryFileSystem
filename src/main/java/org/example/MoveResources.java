package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MoveResources {


    public int moveFileOrFolder(String source, String destination, Map<String, String> allEntityMap, Map<String, String> allFileContentMap) throws  Exception {

        String parentOfDest=destination.substring(0,destination.lastIndexOf("\\"));
        if(!allEntityMap.containsKey(source))
        {
            throw new Exception("source path does not exists");
        }
        if(allEntityMap.containsKey(destination)) {
            throw new Exception("destination path already exists");
        }
        if(!allEntityMap.containsKey(parentOfDest))
        {
            throw new Exception("parent of destination path does not  exists");
        }
        List<String> allpaths=new ArrayList<>();
        for(Map.Entry<String,String> entry:allEntityMap.entrySet())
        {
            String key=entry.getKey();
            String value=entry.getValue();

            if(key.startsWith(source))
                allpaths.add(key);
        }
        for(int i=0;i<allpaths.size();i++)
        {
            String key=allpaths.get(i);
            String value=allEntityMap.get(key);
            allEntityMap.remove(key);

            String key1=key.replace(source, destination);
            allEntityMap.put(key1,value);
            if(allFileContentMap.containsKey(key))

            {
                String content=allFileContentMap.get(key);
                allFileContentMap.remove(key);
                allFileContentMap.put(key1, content);
            }
        }
        return 1;
    }
}
