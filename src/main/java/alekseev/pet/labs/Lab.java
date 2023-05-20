package alekseev.pet.labs;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.textfield.TextArea;

import java.util.concurrent.CompletableFuture;

public interface Lab {
     String getName();
     String getDescription();
     CompletableFuture<String> start(UI ui, TextArea textArea);
     String getOutput();
}
