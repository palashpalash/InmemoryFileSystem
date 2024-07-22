package org.example;

import java.util.Map;

public class FileUtility {

    public static void isValidFolderPathConfig(String folderName, String pathOfParent, Map<String,String> allEntityMap) throws Exception
    {
        String parentPath=pathOfParent.substring(0,pathOfParent.lastIndexOf("\\"));
        if(!allEntityMap.containsKey(parentPath))
            throw new Exception("Path not found");
        if(allEntityMap.containsKey(pathOfParent))
            throw new Exception("Path already exists");
        if(parentPath.contains(".txt"))
            throw new Exception("Illegal File System Operation");
        if(!checkAlphaNumericFilePath(folderName))
            throw new Exception("Folder Name is not Alphanumeric");
    }
    public static void isValidDrivePathConfig(String driverName, Map<String,String> allEntityMap) throws Exception
    {


        if(allEntityMap.containsKey(driverName))
            throw new Exception("Driver already exists");
        if(!checkAlphaNumericFilePath(driverName))
            throw new Exception("Driver Name is not Alphanumeric");
    }

    public static void isValidTextFilePathConfig(String folderName, String pathOfParent, Map<String,String> allEntityMap) throws Exception
    {
        String parentPath=pathOfParent.substring(0,pathOfParent.lastIndexOf("\\"));
        if(!allEntityMap.containsKey(parentPath))
            throw new Exception("Path not found");
        if(allEntityMap.containsKey(pathOfParent))
            throw new Exception("Path already exists");
        if(parentPath.contains(".txt"))
            throw new Exception("Illegal File System Operation");
        //if(!checkAlphaNumericFilePath(folderName))
           // throw new Exception("Text File Name is not Alphanumeric");
    }



    public static void isValidZipFilePathConfig(String zipFileName, String pathOfParent, Map<String, String> allEntityMap) throws Exception{

        String parentPath=pathOfParent.substring(0,pathOfParent.lastIndexOf("\\"));
        if(!allEntityMap.containsKey(parentPath))
            throw new Exception("Path not found");
        if(allEntityMap.containsKey(pathOfParent))
            throw new Exception("Path already exists");
        if(parentPath.contains(".txt"))
            throw new Exception("Illegal File System Operation");
        //if(!checkAlphaNumericFilePath(zipFileName))
           // throw new Exception("Text File Name is not Alphanumeric");
    }
    public static void isValidTextFilePathConfigExistance(String filePath, Map<String, String> allEntityMap) throws Exception{
        if(!allEntityMap.containsKey(filePath))
            throw new Exception("Path not found");
        if(!filePath.contains(".txt"))
            throw new Exception("Not a Text File");
    }


    private static boolean checkAlphaNumericFilePath(String folderName) {
        boolean result = true;
        for (int i = 0; i < folderName.length(); ++i) {
            int codePoint = folderName.codePointAt(i);
            if (!isAlphanumeric(codePoint)) {
                result = false;
                break;
            }
        }
        return result;
    }

    private static boolean isAlphanumeric(int codePoint) {
        return (codePoint >= 65 && codePoint <= 90) ||
                (codePoint >= 97 && codePoint <= 122) ||
                (codePoint >= 48 && codePoint <= 57);
    }


}
