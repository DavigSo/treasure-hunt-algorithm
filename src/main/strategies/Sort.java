package main.strategies;

import java.util.List;
import java.util.Random;

import main.game.map.GameMap;
import main.game.map.Monster;
import main.game.map.Point;
import main.game.map.Rock;

public class Sort implements Strategy {

    private Random random;

    public Sort() {
        this.random = new Random();
    }

    @Override
    public Point evaluatePossibleNextStep(List<Point> possibleNextSteps, GameMap gameMap) {
        if (possibleNextSteps == null || possibleNextSteps.isEmpty()) {
            return null;
        }

        Point nextStep = null;

        while (nextStep == null) {
            int index = random.nextInt(possibleNextSteps.size());
            nextStep = possibleNextSteps.get(index);

            String space = gameMap.get(nextStep);
            if (Rock.CHARACTER.equals(space) || Monster.CHARACTER.equals(space)) {
                nextStep = null;
            }
        }

        return nextStep;
    }
}