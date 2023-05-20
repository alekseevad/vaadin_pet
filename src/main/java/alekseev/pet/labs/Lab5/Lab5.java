package alekseev.pet.labs.Lab5;

import alekseev.pet.labs.Lab;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.textfield.TextArea;
import org.springframework.scheduling.annotation.Async;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Lab5 implements Lab {
    private String output;

    public Lab5() { this.output = ""; }

    @Async
    @Override
    public CompletableFuture<String> start(UI ui, TextArea textArea) {
        //1. Среднее целых чисел
        List<Integer> numbers = List.of(1, 2, 3);
        double average = numbers.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);

        output = output.concat("#1\n" + average);

        //2. Все строки в верхний регистр и добавить _new_
        List<String> strings = List.of("string1", "string2", "string3", "string4");
        output = output.concat("\n#2\n");
        strings.stream()
                .map(element -> {
                    return "_new_".concat(element.toUpperCase());
                })
                .toList()
                .forEach(n -> output = output.concat(n));


        //3. Список квадратов всех встречающихся только один раз элементов списка
        List<Integer> numbers2 = List.of(1, 2, 4, 1, 3, 2);
        output = output.concat("\n#3\n");
        numbers2.stream()
                .filter(element -> Collections.frequency(numbers2, element) == 1)
                .map(element-> Math.pow(element, 2.0) )
                .toList()
                .forEach(n -> output = output.concat(n.toString()));

        //4. метод, принимающий на вход коллекцию строк и возвращает все
        //строки, начинающиеся с заданной буквы, отсортированные по
        //алфавиту

        output = output.concat("\n#4\n");
        Collection<String> strings2 = Arrays.asList("alc", "cbd", "abl", "heh", "meh", "java", "beach");
        strings2.stream()
                .filter(element -> element.startsWith("a"))
                .toList()
                .stream().sorted()
                .forEach(n -> output = output.concat(n));

        //5. метод, принимающий на вход коллекцию и возвращающий ее
        //последний элемент или кидающий исключение, если коллекция
        //пуста

        output = output.concat("\n#5\n");
        Collection<Double> doubles = List.of(0.0, 3.4,  1.2);
        output = output.concat(doubles.stream()
                .reduce((a, b) -> b )
                .orElseThrow(() -> new RuntimeException("List is empty")).toString());

        //6. метод, принимающий на вход массив целых чисел,
        //возвращающий сумму чётных чисел или 0, если чётных чисел
        //нет
        output = output.concat("\n#6\n");
        int[] numbers3 = {1, 2, 3, 4, 5, 6};
        output = output.concat(String.valueOf(Arrays.stream(numbers3)
                .filter(n -> n % 2 == 0)
                .sum())); //reduce(0, Integer::sum)

        //7. метод, преобразовывающий все строки в списке в Map, где
        //первый символ – ключ, оставшиеся – значение
        output = output.concat("\n#7");
        List<String> strings3 = List.of("1string1", "2string2", "3string3");
        output = output.concat(strings3.stream()
                .collect(Collectors.toMap(s -> s.charAt(0), s -> s.substring(1))).toString());
        ui.access(() -> {
            textArea.setValue(output);
            ui.push();
        });

        return CompletableFuture.completedFuture(this.getName() + " is completed");
    }

    @Override
    public String getName() {
        return "Lab5";
    }

    @Override
    public String getDescription() {
        return "Stream API";
    }

    @Override
    public String getOutput() {
        return output;
    }
}
