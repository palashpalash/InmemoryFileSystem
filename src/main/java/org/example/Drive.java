package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Drive {

    public  int createDrive(String driveName, Map<String,String> allEntityMap) throws Exception
    {

        try {
            FileUtility.isValidDrivePathConfig(driveName,allEntityMap);
        }
        catch (Exception e)
        {
            throw e;
        }

        allEntityMap.put(driveName,driveName);
        return 1;
    }

    public int deleteDrive(String driveName, Map<String, String> allEntityMap) throws Exception {

        if(allEntityMap.containsKey(driveName))

        {
            List<String> allPaths=new ArrayList<>();
            for(Map.Entry<String,String> entry:allEntityMap.entrySet())
            {
                String key=entry.getKey();
                String value=entry.getValue();
                if(key.startsWith(driveName))
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
}
