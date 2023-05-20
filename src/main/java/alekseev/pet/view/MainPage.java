package alekseev.pet.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "")
public class MainPage extends VerticalLayout {
    private final Div container = new Div();
    private final Button navigateToLabs = new Button("To Java Core Labs");
    public MainPage() {
        navigateToLabs.addClickListener(e ->
                navigateToLabs.getUI().ifPresent(ui -> ui.navigate("java_labs")));

        container.add(navigateToLabs);
        add(container);
    }
}
