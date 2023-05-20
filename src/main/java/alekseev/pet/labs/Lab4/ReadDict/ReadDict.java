package alekseev.pet.labs.Lab4.ReadDict;

import alekseev.pet.labs.Lab4.Exceptions.FileReadException;
import alekseev.pet.labs.Lab4.Exceptions.InvalidFileFormatException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;
import java.util.regex.Pattern;

public class ReadDict {
    public void readDict(HashMap<String, String> dictionary, String fileName) throws IOException, FileReadException, InvalidFileFormatException {
        Optional<String> fileLine;
        File file = new File(fileName);
        if(!file.exists() || file.isDirectory())
            throw new InvalidFileFormatException("Wrong format of file");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        fileLine = Optional.ofNullable(bufferedReader.readLine());
        String pattern = "[a-z]+ \\| [a-z]+";

        while(fileLine.isPresent()) {
            if(!Pattern.matches(pattern, fileLine.get()))
                throw new FileReadException("Wrong dictionary format");

            String[] splitLine = fileLine.get().split(" . ");
            dictionary.put(splitLine[0], splitLine[1]);
            fileLine = Optional.ofNullable(bufferedReader.readLine());
        }
        bufferedReader.close();
    }
}
