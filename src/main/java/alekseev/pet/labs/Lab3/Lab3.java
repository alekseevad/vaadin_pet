package alekseev.pet.labs.Lab3;

import alekseev.pet.labs.Lab;
import alekseev.pet.labs.Lab3.Animals.*;
import alekseev.pet.labs.Lab3.Segregate.Segregate;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.textfield.TextArea;
import org.springframework.scheduling.annotation.Async;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class Lab3 implements Lab {
    private String output;

    public Lab3() { this.output = ""; }

    @Async
    @Override
    public CompletableFuture<String> start(UI ui, TextArea textArea) {
        Collection<Chordate> collection1 = new ArrayList<>();
        Collection<Chordate> collection2 = new ArrayList<>();
        Collection<Chordate> collection3 = new ArrayList<>();

        Segregate segregate = new Segregate();

        Chordate chordate = new Chordate();
        Mammal mammal = new Mammal();
        Predator predator = new Predator();
        Feline manul = new Manul();
        Feline feline = new Feline();
        Feline lynx = new Lynx();
        Insectivorous insectivorous = new Insectivorous();
        Hedgehog hedgehog = new Hedgehog();
        Hedgehog commonHedgehog = new CommonHedgehog();;

        Collection<Chordate> srcCollection = new ArrayList<>(Arrays.asList(lynx, feline, insectivorous, manul, mammal,
                commonHedgehog, hedgehog, chordate, predator));
        try {
            segregate.segregate(srcCollection, collection1, collection2, collection3);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }

        output = output.concat(srcCollection.toString() + "\n");
        output = output.concat(collection1.toString() + "\n");
        output = output.concat(collection2.toString() + "\n");
        output = output.concat(collection3.toString() + "\n");

        ui.access(() -> {
            textArea.setValue(output);
            ui.push();
        });

        return CompletableFuture.completedFuture(this.getName() + " is completed");
    }

    @Override
    public String getName() {
        return "Lab3";
    }

    @Override
    public String getDescription() {
        return "Segregation, Generics, Wildcards";
    }

    @Override
    public String getOutput() {
        return output;
    }
}
