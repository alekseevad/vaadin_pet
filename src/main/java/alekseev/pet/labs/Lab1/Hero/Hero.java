package alekseev.pet.labs.Lab1.Hero;

import alekseev.pet.labs.Lab1.Strategies.MoveStrategy;

public class Hero {
    private MoveStrategy moveStrategy;
    private int x;

    Hero() {}
    public Hero(Integer x, MoveStrategy moveStrategy) {
        this.x = x;
        this.moveStrategy = moveStrategy;
    }

    public void setMoveStrategy(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public String move() {
        x = moveStrategy.move(x);
        return String.format("%s and x=%d" ,moveStrategy.getName(), x);
    }
}
