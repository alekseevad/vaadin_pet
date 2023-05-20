package alekseev.pet.labs.Lab1.Strategies;

public class MoveByPlane implements MoveStrategy {
    @Override
    public int move(int x) {
        x += 100;
        return x;
    }

    @Override
    public String getName() {
        return "moving by plane";
    }
}
