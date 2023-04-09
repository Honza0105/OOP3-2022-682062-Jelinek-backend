package testing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class OtherModuleTest {


    public static void main(String[] args){
        String fileName = "src/main/resources/test.txt"; // Replace with the actual file name or path
        File file = new File(fileName);
        System.out.println(file.getAbsoluteFile());
        System.out.println(ClassLoader.getSystemResource(fileName));

        try {
            // Create FileReader and BufferedReader objects
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            // Read file line by line
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line); // Print each line to console
            }

            // Close BufferedReader and FileReader
            bufferedReader.close();
            fileReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
