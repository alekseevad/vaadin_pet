package alekseev.pet.labs.Lab1.Strategies;

public class MoveByFeet implements MoveStrategy {
    @Override
    public int move(int x) {
        x += 10;
        return x;
    }

    @Override
    public String getName() {
        return "moving on feet";
    }
}
