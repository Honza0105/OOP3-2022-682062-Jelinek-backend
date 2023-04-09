package domain.util;

import java.io.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class WordSet{
    private static WordSet instance = null;
    private final Set<String> stringSet = new HashSet<>();
    private int processedWords;

    private WordSet() {
        String source = "src/main/resources/words.txt";
        File file = new File(source);
        System.out.println(file.getAbsoluteFile());
        if (file.exists()){
            System.out.println("hello");
            readWordSet("src/main/resources/words.txt");
            System.out.println(stringSet.size());
        }
        else {
            createWordSet("/Users/janjelinek/Library/CloudStorage/OneDrive-HogeschoolInholland/Inholland/AM2/Term 3/OOP 3/Project Gotenberg");
        }
    }

    private void readWordSet(String source){
        try (BufferedReader br = new BufferedReader(new FileReader(source))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split(" "); // split words by whitespace
                Collections.addAll(stringSet, words);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createWordSet(String source){
        File folder = new File(source);
        System.out.println(folder);

        File[] files = folder.listFiles();
        int totalFiles = files.length;
        int processedFiles = 0;
        processedWords = 0;

        for (File file : files) {
            if (file.isFile()) { // process only files, not directories
                processedFiles++;
                System.out.println("Processing file: " + file.getName() + " (" + processedFiles + "/" + totalFiles + ")");
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] words = line.split("\\s+"); // split words by whitespace
                        Collections.addAll(stringSet, words);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Processed file: " + file.getName() + " (" + processedFiles + "/" + totalFiles + ")");
                if (processedFiles == 25000){
                    //I ran out of Heap space, I think 25 000 is enough anyways
                    break;
                }
            }
        }
        saveToFile("src/main/resources/words.txt");
    }

    public static WordSet getWordSet() {
        if (instance == null){
            instance = new WordSet();
        }
        return instance;
    }

    public void saveToFile(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (String word : stringSet) {
                writer.write(word + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks whether the String is an existing word
     * @param wordToCheck String which is suspect to being a word
     * @return True if the string is an existing word. False if it is not.
     */
    public boolean isWord(String wordToCheck){
        return stringSet.contains(wordToCheck);
    }

    public boolean isSentence(String sentenceToCheck) {
        String[] individualWords = sentenceToCheck.split(" ");
        long total = individualWords.length;
        long exist = 0;
        for (String word: individualWords
             ) {
            if (isWord(word)){
                exist++;
            }
        }
        return (double) exist / total > 0.95;
    }

    public static void main(String[] args) {
        WordSet wordSet = getWordSet();
//        System.out.println( wordSet.isWord(""));
//        System.out.println(wordSet.stringSet.size());
//        System.out.println(wordSet.processedWords);
       ;
    }
}
