package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class MainFile {

    Map<String,String> allEntityMap;
    Map<String,String> allFileContentMap;
    Scanner scanner;
    Drive drive;
    Folder folder;
    TextFile textFile;
    ZipFile zipFile;
    MoveResources moveResources;


    public static void main(String args[]) {
        new MainFile().showOption();
    }
    public void showOption()
    {
        allEntityMap=new HashMap<>();
        allFileContentMap=new HashMap<>();
        scanner = new Scanner(System.in);
        drive=new Drive();
        folder=new Folder();
        textFile=new TextFile();
        zipFile=new ZipFile();
        moveResources=new MoveResources();

        while(true) {

            System.out.println("Enter 1 for create \n 2 for delete \n 3 for Move \n 4 for WriteToFile \n 5 for Size");
            int x=Integer.parseInt(scanner.nextLine());
            if(x==1)

            {
                System.out.println("Enter 1 for create drive \n 2 for create folder \n 3 for Text File \n 4 for Zip File");
                int x1=Integer.parseInt(scanner.nextLine());

                if(x1==1) {
                    System.out.println("Enter Drive Name:");
                    String driveName=scanner.nextLine();

                    try {
                        int insertedValue=drive.createDrive(driveName, allEntityMap);
                        if(insertedValue==1) System.out.println("Drive created successfully");
                    }
                    catch (Exception e)
                    {
                        System.out.println("Exception Occurred:"+e.getMessage());
                    }
                }
                //

                if(x1==2) {


                    System.out.println("Enter Folder Name: and Path of parent:");
                    String folderDetails=scanner.nextLine();
                    System.out.println(folderDetails);
                    String folderName=folderDetails.split("\\ ")[0];
                    String pathOfParent=folderDetails.split("\\ ")[1];

                    try {
                        int  insertedValue=folder.createFolder(folderName,pathOfParent, allEntityMap);
                        if(insertedValue==1) System.out.println("Folder created successfully");
                    }
                    catch (Exception e)
                    {
                        System.out.println("Exception Occurred:"+e.getMessage());
                    }
                }
                ///


                if(x1==3) {


                    System.out.println("Enter text File Name: and Path of parent:");
                    String textFileDetails=scanner.nextLine();
                    //System.out.println(folderDetails);
                    String textFileName=textFileDetails.split("\\ ")[0];
                    String pathOfParent=textFileDetails.split("\\ ")[1];

                    try {
                        int  insertedValue=textFile.createTextFile(textFileName,pathOfParent, allEntityMap);
                        if(insertedValue==1) System.out.println("Text File created successfully");
                    }
                    catch (Exception e)
                    {
                        System.out.println("Exception Occurred:"+e.getMessage());
                    }
                }
                ///
                if(x1==4) {


                    System.out.println("Enter Zip File Name: and Path of parent:");
                    String zipFileDetails=scanner.nextLine();
                    //System.out.println(folderDetails);
                    String zipFileName=zipFileDetails.split("\\ ")[0];
                    String pathOfParent=zipFileDetails.split("\\ ")[1];

                    try {
                        int  insertedValue=zipFile.createZipFile(zipFileName,pathOfParent, allEntityMap);
                        if(insertedValue==1) System.out.println("Zip File created successfully");
                    }
                    catch (Exception e)
                    {
                        System.out.println("Exception Occurred:"+e.getMessage());
                    }
                }
            }
            if(x==2)
            {
                System.out.println("Enter 1 for delete drive \n 2 for delete folder \n 3 for Text File \n 4 for Zip File");
                int x1=Integer.parseInt(scanner.nextLine());

                if(x1==1) {
                    System.out.println("Enter Drive Path to delete:");
                    String driveName=scanner.nextLine();

                    try {
                        int insertedValue=drive.deleteDrive(driveName, allEntityMap);
                        if(insertedValue==1) System.out.println("Drive deleted successfully");
                    }
                    catch (Exception e)
                    {
                        System.out.println("Exception Occurred:"+e.getMessage());
                    }
                }
                //

                if(x1==2) {


                    System.out.println("Enter Folder  Path to delete:");
                    String folderPath=scanner.nextLine();


                    try {
                        int  insertedValue=folder.deleteFolder(folderPath, allEntityMap);
                        if(insertedValue==1) System.out.println("Folder deleted successfully");
                    }
                    catch (Exception e)
                    {
                        System.out.println("Exception Occurred:"+e.getMessage());
                    }
                }

                //

                if(x1==3) {


                    System.out.println("Enter Text File  Path to delete:");
                    String textFilePath=scanner.nextLine();


                    try {
                        int  insertedValue=textFile.deleteTextFile(textFilePath, allEntityMap);
                        if(insertedValue==1) System.out.println("Text File deleted successfully");
                    }
                    catch (Exception e)
                    {
                        System.out.println("Exception Occurred:"+e.getMessage());
                    }
                }

//


                if(x1==4) {


                    System.out.println("Enter Zip File  Path to delete:");
                    String zipFilePath=scanner.nextLine();


                    try {
                        int  insertedValue=zipFile.deleteZipFile(zipFilePath, allEntityMap);
                        if(insertedValue==1) System.out.println("Folder deleted successfully");
                    }
                    catch (Exception e)
                    {
                        System.out.println("Exception Occurred:"+e.getMessage());
                    }
                }
            }

            //Move
            if(x==3)
            {
                System.out.println("Enter Source Path");
                String source=scanner.nextLine();
                System.out.println("Enter Destination Path");
                String destination=scanner.nextLine();
                try {
                    int insertedValue=moveResources.moveFileOrFolder(source,destination,allEntityMap,allFileContentMap);
                    if(insertedValue==1) System.out.println("Resource has been moved successfully");
                } catch (Exception e) {
                    System.out.println("Exception Occurred :"+e.getMessage());
                }


            }
            // Write To File

            if(x==4)
            {
                System.out.println("Enter File Name: and Content Of the Files:");
                String fileDetails=scanner.nextLine();
                String filePath=fileDetails.split("\\ ")[0];
                String content=fileDetails.substring(fileDetails.indexOf(" ")+1,fileDetails.length());

                try {
                    int  insertedValue=textFile.writeToFile(filePath,content, allEntityMap,allFileContentMap);
                    if(insertedValue==1) System.out.println("File Content is Written  successfully");
                }
                catch (Exception e)
                {
                    System.out.println("Exception Occurred:"+e.getMessage());
                }
            }
// Size

            if(x==5)
            {
                System.out.println("Enter 1 for folder \n 2 for zip file \n 3 for Text File");
                int option=Integer.parseInt(scanner.nextLine());
                if(option==1)
                {
                    System.out.println(" Enter folder path to know size");
                    String folderPath=scanner.nextLine();
                    try {
                        int size=folder.getSize(folderPath,allEntityMap,allFileContentMap);
                        System.out.println("The Size of the folder Specified is :"+size+" kb");
                    }
                    catch (Exception e)
                    {
                        System.out.println("Exception Occurred:"+e);
                    }

                }
                if(option==2)
                {
                    System.out.println(" Enter Zip File  path to know size");
                    String zipFilePath=scanner.nextLine();
                    try {
                        int size=zipFile.getSize(zipFilePath,allEntityMap,allFileContentMap);
                        System.out.println("The size of the Zip file Specified is :"+size+" kb");
                    }
                    catch (Exception e)
                    {
                        System.out.println("Exception Occurred:"+e);
                    }
                }
                if(option==3)
                {
                    System.out.println(" Enter Text File  path to know size");
                    String textFilePath=scanner.nextLine();
                    try {
                        int size=textFile.getSize(textFilePath,allEntityMap,allFileContentMap);
                        System.out.println("The size of the File Specified is :"+size+" kb");
                    }
                    catch (Exception e)
                    {
                        System.out.println("Exception Occurred:"+e);
                    }
                }
            }

        }
    }
}
