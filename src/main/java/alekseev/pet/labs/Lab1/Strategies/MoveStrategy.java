package alekseev.pet.labs.Lab1.Strategies;

public interface MoveStrategy {
    public int move(int x);
    public default String getName() {
        return "This is a moving strategy";
    }
    int number_of_objects = 0;
}
