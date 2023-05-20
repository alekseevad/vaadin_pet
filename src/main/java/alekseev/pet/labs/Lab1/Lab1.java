package alekseev.pet.labs.Lab1;

import alekseev.pet.labs.Lab;
import alekseev.pet.labs.Lab1.Hero.Hero;
import alekseev.pet.labs.Lab1.Strategies.MoveByCar;
import alekseev.pet.labs.Lab1.Strategies.MoveByFeet;
import alekseev.pet.labs.Lab1.Strategies.MoveByPlane;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.textfield.TextArea;
import org.springframework.scheduling.annotation.Async;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class Lab1 implements Lab {
    private String input;
    private volatile String output;
    private final Random random = new Random();

    public Lab1() {
        this.input = "car";
    }

    @Override
    public String getName() {
        return "Lab1";
    }

    @Override
    public String getDescription() {
        return "Strategy pattern implementation";
    }

    @Async
    @Override
    public CompletableFuture<String> start(UI ui, TextArea textArea) {
        Hero hero = new Hero(0, new MoveByCar());
        String[] movingStrategy = { "car", "quit", "plane", "feet" };

        try {
            while (!input.equals("quit")) {
                switch (input) {
                    case "car" -> hero.setMoveStrategy(new MoveByCar());
                    case "plane" -> hero.setMoveStrategy(new MoveByPlane());
                    case "feet" -> hero.setMoveStrategy(new MoveByFeet());
                    default -> {
                    }
                }
                output = hero.move();
                ui.access(() -> textArea.setValue(getOutput()));
                ui.push();
                Thread.sleep(2000);
                input = movingStrategy[random.nextInt(4)];
            }
        }
        catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
        return CompletableFuture.completedFuture(this.getName() + " is completed");
    }

    @Override
    public synchronized String getOutput() {
        return output;
    }
}