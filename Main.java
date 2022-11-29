package asciimirror;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 *  This program shows mirror image of given text in file.
 *  @Input  - Name of file.
 *  @Output - input data and mirror image
 *
 *  @author   - Trapti Tiwari
 *  @email    - traptit1@yahoo.com
 *  @linkedin - https://www.linkedin.com/in/tiwari-trapti/
 */

public class Main {
    public static final Scanner readIp = new Scanner(System.in);

    public static void main(String[] args) {
        //  Input file name
        System.out.println("Input the file path:");
        String filePath = readIp.nextLine();
        ArrayList<String> fileData = new ArrayList<>();
        int maxLength = 0;

        //  Read file data
        //  Find maximum length of line in data
        File file = new File(filePath);
        try (Scanner readFile = new Scanner(file)) {
            if (file.exists()) {
                while (readFile.hasNextLine()) {
                    String str = readFile.nextLine();
                    if (str.length() > maxLength) {
                        maxLength = str.length();
                    }
                    fileData.add(str);
                }
            } else {
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error, File not found");
        }

        //  Convert all lines length to be equal to line of maximum length
        ArrayList<String> reverseFileData = new ArrayList<>();

        for (int i = 0; i < fileData.size(); i++) {
            StringBuilder str = new StringBuilder();
            str.append(fileData.get(i));
            if (str.length() < maxLength) {
                int difference = maxLength - str.length();
                str.append(" ".repeat(Math.max(0, difference)));
                fileData.set(i, str.toString());
            }
        }

        //  Create mirror image output for each line
        //  replace some special character to show correct image
        for (String str : fileData) {
            StringBuilder reverseStr = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                switch (str.charAt(i)) {
                    case '(' -> reverseStr.append(')');
                    case ')' -> reverseStr.append('(');
                    case '\\' -> reverseStr.append('/');
                    case '/' -> reverseStr.append('\\');
                    case '[' -> reverseStr.append(']');
                    case ']' -> reverseStr.append('[');
                    case '<' -> reverseStr.append('>');
                    case '>' -> reverseStr.append('<');
                    case '{' -> reverseStr.append('}');
                    case '}' -> reverseStr.append('{');
                    default -> reverseStr.append(str.charAt(i));
                }
            }
            reverseFileData.add(reverseStr.reverse().toString());
        }

        //  Print input data and it's mirror image
        for (int i = 0; i < reverseFileData.size(); i++) {
            System.out.println(fileData.get(i) + " | " + reverseFileData.get(i));
        }
    }
}
