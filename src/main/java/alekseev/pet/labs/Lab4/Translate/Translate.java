package alekseev.pet.labs.Lab4.Translate;

import alekseev.pet.labs.Lab4.Exceptions.InvalidFileFormatException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Translate {
    public String translate(HashMap<String, String> dictionary, String fileName) throws IOException, InvalidFileFormatException {
        String translatedText = "";
        String line;
        File file = new File(fileName);

        if(!file.exists() || file.isDirectory())
            throw new InvalidFileFormatException("Wrong format of file");

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        ArrayList<String> words = new ArrayList<>();

        while ((line = bufferedReader.readLine()) != null) {
            String[] lineWords = line.split("\\s+");
            Collections.addAll(words, lineWords);
            words.add("\n");
        }
        bufferedReader.close();

        for(String word : words)
            if (word.equals("\n"))
                translatedText = translatedText.concat("\n");
            else
                translatedText = translatedText.concat(dictionary.getOrDefault(word.toLowerCase(), word) + " ");
        return translatedText;
    }
}
