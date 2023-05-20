package alekseev.pet;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@Push
@EnableAsync
public class LabsApplication implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(LabsApplication.class, args);
    }

}
