package alekseev.pet.labs.Lab2;

import alekseev.pet.labs.Lab;
import alekseev.pet.labs.Lab2.Annotations.MyAnnotation;
import alekseev.pet.labs.Lab2.User.User;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.textfield.TextArea;
import org.springframework.scheduling.annotation.Async;

import java.io.DataOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.CompletableFuture;

public class Lab2 implements Lab {
    private volatile String output;

    public Lab2() { this.output = ""; }

    @Async
    @Override
    public CompletableFuture<String> start(UI ui, TextArea textArea) {
        User user = new User();
        MyAnnotation myAnno;
        try{
            Method[] method = user.getClass().getDeclaredMethods();
            for(int i = 0; i < method.length; ++i) {
                if(method[i].isAnnotationPresent(MyAnnotation.class)) {
                    method[i].setAccessible(true);
                    myAnno = method[i].getAnnotation(MyAnnotation.class);
                    for(int j = 0; j < myAnno.val(); ++j) {
                        output = output.concat(method[i].invoke(user, "name").toString() + "\n");
                    }
                }
            }
            ui.access(() -> {
                textArea.setValue(getOutput());
                ui.push();
            });
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println(e.getMessage());
        }
        return CompletableFuture.completedFuture(this.getName() + " is completed");
    }

    @Override
    public String getName() {
        return "Lab2";
    }

    @Override
    public String getDescription() {
        return "Explore Reflection API";
    }

    @Override
    public String getOutput() {
        return output;
    }
}
