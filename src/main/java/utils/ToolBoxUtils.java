package utils;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ToolBoxUtils {

    /**
     * This method is used to transform email name into First name + Last name with capital first letters
     * e.g. john.doe@jdoe.com --> John Doe
     *
     * @param email is an email name.familyName@domain.com
     */
    public static String emailNameToUserName(String email) {
        String userNames = email.substring(0, email.lastIndexOf("@")).replace(".", " ");
        return Stream.of(userNames.trim().split("\\s"))
                .filter(word -> word.length() > 0)
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
                .collect(Collectors.joining(" "));
    }

    /**
     * This method builds a directory path in project user dir
     *
     * @param DirPathInUserDir a String of type "\\target\\downloads\\"
     * @param customFolder     is the name of the folder
     */
    public static String assembleDirectoryPath(String DirPathInUserDir, String customFolder) {
        return String.format("%s%s%s", System.getProperty("user.dir"), DirPathInUserDir, customFolder);
    }

    /**
     * This method is used get current Date/Time and return it as a String according to provided pattern
     * e.g. pattern "ddMMyyyy_HHmms" will result in a String like "29042022_124823"
     *
     * @param pattern
     * @return current Date/Time as String
     */
    public static String getCurrentDateTime(String pattern) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String formattedDate = now.format(formatter);
        return formattedDate;
    }

    /**
     * This method is used for extracting data from .csv file.
     *
     * @param filePath directory file path
     * @param fileName the name of the file as String
     * @return extracted data as List<List<String>> ( e.g. [[column1, column2], [a1, a2], [b1, b2], [c1, c2]] )
     */
    public static List<List<String>> extractDataFromCSV(String filePath, String fileName) {
        List<List<String>> csvListOfLines = new ArrayList<List<String>>();
        List<String> stringsInLine;
        try {
            CSVReader reader = new CSVReader(new FileReader(filePath + "\\" + fileName));
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                stringsInLine = new ArrayList<String>();
                for (String s : nextLine) {
                    stringsInLine.add(s.toUpperCase(Locale.ROOT));
                }
                csvListOfLines.add(stringsInLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return csvListOfLines;
    }

    public static File getLastModifiedFileInFolder(String directoryFilePath)
    {
        File directory = new File(directoryFilePath);
        File[] files = directory.listFiles(File::isFile);
        long lastModifiedTime = Long.MIN_VALUE;
        File chosenFile = null;

        if (files != null)
        {
            for (File file : files)
            {
                if (file.lastModified() > lastModifiedTime)
                {
                    chosenFile = file;
                    lastModifiedTime = file.lastModified();
                }
            }
        }

        return chosenFile;
    }

    /**
     * This Method is used to generate the Integer Random number
     * @return intRandomNumber
     */
    public static int getRandomNumber(){
        Random r=new Random();
        int RandomNumber=r.nextInt(10000);
        return RandomNumber;
    }

    /**
     * This method is used to Convert from String[] to Linked List
     *
     * @param input
     * @return
     */
    public LinkedList<String> convertToLinkedList(String[] input) {
        LinkedList<String> actualList = new LinkedList<String>();
        String[] lst = Arrays.toString(input).replace('[', ',').replace(']', ',').split(",");
        for (String s : lst) {
            if (!s.equalsIgnoreCase("")) actualList.add(s);
        }
        System.out.println("actualList :" + actualList);
        return actualList;
    }
}
