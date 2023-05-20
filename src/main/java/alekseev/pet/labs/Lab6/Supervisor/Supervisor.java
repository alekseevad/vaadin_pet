package alekseev.pet.labs.Lab6.Supervisor;

import alekseev.pet.labs.Lab;
import alekseev.pet.labs.Lab6.Runnables.RandomizerDaemon;
import alekseev.pet.labs.Lab6.Runnables.Program;
import alekseev.pet.labs.Lab6.State.STATE;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.textfield.TextArea;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Supervisor implements Lab {
    private final ExecutorService executorService;
    private final RandomizerDaemon randomizerDaemon;
    private final Program program;
    private String output;

    public Supervisor(){
        this.executorService = Executors.newFixedThreadPool(2);
        this.program = new Program(STATE.UNKNOWN);
        this.randomizerDaemon = new RandomizerDaemon(program);
    }

    @Async
    @Override
    public CompletableFuture<String> start(UI ui, TextArea textArea) {
        this.executorService.submit(randomizerDaemon);
        STATE programState;
        while(true) {
            synchronized (program) {
                programState = program.getState();
            }
            if(programState != STATE.RUNNING) {
                randomizerDaemon.setMainThreadStopped(true);
            }
            output = programState.toString();

            ui.access(() -> {
                textArea.setValue(output);
                ui.push();
            });

            if (programState == STATE.FATAL_ERROR) {
                System.out.println("Monitor closes");
                break;
            }
            else if(programState != STATE.RUNNING)
            {
                program.setState(STATE.RUNNING);
                executorService.submit(program);
                executorService.submit(randomizerDaemon);
            }
            executorService.shutdown();
        }
        return CompletableFuture.completedFuture(this.getName() + " is completed");
    }

    @Override
    public String getName() {
        return "Lab6";
    }

    @Override
    public String getDescription() {
        return "Multithreading lab";
    }

    @Override
    public String getOutput() {
        return output;
    }
}
