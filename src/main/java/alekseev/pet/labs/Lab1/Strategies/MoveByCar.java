package alekseev.pet.labs.Lab1.Strategies;

public class MoveByCar implements MoveStrategy {
    @Override
    public int move(int x) {
        x += 30;
        return x;
    }


    public String getName() {
        return "moving by car";
    }
}
