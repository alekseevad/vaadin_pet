package alekseev.pet.view;

import alekseev.pet.labs.Lab;
import alekseev.pet.labs.Lab1.Lab1;
import alekseev.pet.labs.Lab2.Lab2;
import alekseev.pet.labs.Lab3.Lab3;
import alekseev.pet.labs.Lab4.Lab4;
import alekseev.pet.labs.Lab5.Lab5;
import alekseev.pet.labs.Lab6.Supervisor.Supervisor;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Route("java_labs")
public class LabsView extends VerticalLayout {
    private final Div containerCheckBoxButton;
    private final Div textContainer;
    private final Button startButton;
    private final ComboBox<Lab> labsComboBox;
    private final TextArea textArea;
    private Lab currentLab;
    private CompletableFuture<String> future;

    public LabsView() {
        this.labsComboBox = new ComboBox<>("JavaLabs");
        this.containerCheckBoxButton = new Div();
        this.startButton = new Button("Start lab");
        this.textArea = new TextArea();
        this.textContainer = new Div();

        this.labsComboBox.setItems(List.of(new Lab1(), new Lab2(), new Lab3(), new Lab4(), new Lab5(), new Supervisor()));
        this.labsComboBox.setItemLabelGenerator(Lab::getName);

        this.textArea.setReadOnly(true);
        this.textArea.setMaxLength(2000);
        this.textArea.setVisible(false);


        this.startButton.addClickListener(n -> {
            this.textArea.setVisible(true);
            try {

                UI ui = this.textArea.getUI().orElseThrow();
                currentLab = labsComboBox.getValue();
                future = currentLab.start(ui, this.textArea);
            }
            catch (Exception e) {
                System.err.println(e.getMessage());
            }
        });
        this.startButton.setDisableOnClick(true);

        containerCheckBoxButton.add(this.labsComboBox, this.startButton);
        textContainer.add(this.textArea);
        add(containerCheckBoxButton, textContainer);
    }
}
