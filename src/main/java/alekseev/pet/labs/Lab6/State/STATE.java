package alekseev.pet.labs.Lab6.State;

import java.util.Random;

public enum STATE {
    UNKNOWN, STOPPING, RUNNING, FATAL_ERROR;
    private static final STATE[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random RANDOM = new Random();

    public static STATE randomState()  {
        return VALUES[RANDOM.nextInt(SIZE)];
    }
}
