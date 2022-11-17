package priv.klochkov.island.constants;

import java.util.List;
import java.util.Random;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    UP_AND_RIGHT,
    UP_AND_LEFT,
    DOWN_AND_RIGHT,
    DOWN_AND_LEFT;

    private static final List<Direction> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Direction randomDirection() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
