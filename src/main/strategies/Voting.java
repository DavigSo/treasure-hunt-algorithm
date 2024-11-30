//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package main.strategies;

import java.io.PrintStream;
import java.util.List;
import main.game.map.Map;
import main.game.map.Monster;
import main.game.map.Point;
import main.game.map.Rock;

public class Voting implements Strategy {
    public Voting() {
    }

    public Point evaluatePossibleNextStep(List<Point> possibleNextSteps, Map map) {
        int[] votes = new int[possibleNextSteps.size()];
        System.out.println("Estratégia de Votação está sendo usada para escolher o próximo movimento.");

        int bestStepIndex;
        Point step;
        for(bestStepIndex = 0; bestStepIndex < votes.length; ++bestStepIndex) {
            step = (Point)possibleNextSteps.get(bestStepIndex);
            votes[bestStepIndex] = this.countObstaclesAround(step, map);
        }

        for(bestStepIndex = 0; bestStepIndex < votes.length; ++bestStepIndex) {
            step = (Point)possibleNextSteps.get(bestStepIndex);
            PrintStream var10000 = System.out;
            int var10001 = step.getPositionX();
            var10000.println("Voto para a direção (" + var10001 + ", " + step.getPositionY() + "): " + votes[bestStepIndex]);
        }

        bestStepIndex = 0;
        int minObstacles = votes[0];

        for(int i = 1; i < votes.length; ++i) {
            if (votes[i] < minObstacles) {
                minObstacles = votes[i];
                bestStepIndex = i;
            }
        }

        return (Point)possibleNextSteps.get(bestStepIndex);
    }

    private int countObstaclesAround(Point point, Map map) {
        int obstacles = 0;
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        int[][] var5 = directions;
        int var6 = directions.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            int[] direction = var5[var7];
            int newX = point.getPositionX() + direction[0];
            int newY = point.getPositionY() + direction[1];
            if (newX >= 0 && newY >= 0 && newX < map.getScenarioSize()[0] && newY < map.getScenarioSize()[1]) {
                String cell = map.get(new Point(newX, newY));
                if (this.isObstacle(cell)) {
                    ++obstacles;
                }
            }
        }

        return obstacles;
    }

    private boolean isObstacle(String cell) {
        return cell != null && (cell.equals(Rock.CHARACTER) || cell.equals(Monster.CHARACTER));
    }
}
