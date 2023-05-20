package alekseev.pet.labs.Lab4;

import alekseev.pet.labs.Lab;
import alekseev.pet.labs.Lab4.Exceptions.FileReadException;
import alekseev.pet.labs.Lab4.Exceptions.InvalidFileFormatException;
import alekseev.pet.labs.Lab4.ReadDict.ReadDict;
import alekseev.pet.labs.Lab4.Translate.Translate;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.textfield.TextArea;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class Lab4 implements Lab {
    private ReadDict readDict;
    private Translate translator;
    private String output;

    public Lab4() {
        this.readDict = new ReadDict();
        this.output = "";
        this.translator = new Translate();
    }

    @Async
    @Override
    public CompletableFuture<String> start(UI ui, TextArea textArea){
        try {
            HashMap<String, String> dictionary = new HashMap<>();
            readDict.readDict(dictionary, "src/main/resources/dictionary.txt");
            output = translator.translate(dictionary,"src/main/resources/text_to_be_translated.txt");
            ui.access(() -> {
                textArea.setValue(output);
                ui.push();
            });
        } catch(IOException | FileReadException | InvalidFileFormatException e) {
            System.err.println(e.getMessage());
        }

        return CompletableFuture.completedFuture(this.getName() + " is completed");
    }

    @Override
    public String getName() {
        return "Lab4";
    }

    @Override
    public String getDescription() {
        return "I/O Java Core, Exceptions";
    }

    @Override
    public String getOutput() {
        return output;
    }
}
