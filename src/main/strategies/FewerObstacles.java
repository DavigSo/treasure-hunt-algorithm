package main.strategies;

import java.util.List;
import main.game.map.Map;
import main.game.map.Monster;
import main.game.map.Point;
import main.game.map.Rock;
import main.tracker.PathTracker;

public class FewerObstacles implements Strategy {

    private final PathTracker pathTracker;

    public FewerObstacles() {
        this.pathTracker = new PathTracker();
    }

    @Override
    public Point evaluatePossibleNextStep(List<Point> possibleNextSteps, Map map) {
        Point bestStep = null;
        int minObstacles = Integer.MAX_VALUE;

        for (Point step : possibleNextSteps) {
            int obstacles = countObstaclesAround(step, map);
            if (obstacles < minObstacles) {
                minObstacles = obstacles;
                bestStep = step;
            }
        }

        if (bestStep != null) {
            pathTracker.addPathPoint(bestStep);
            if (minObstacles > 0) {
                pathTracker.addObstacle(bestStep);
            }
        }

        return bestStep;
    }

    private int countObstaclesAround(Point point, Map map) {
        int obstacles = 0;
        int[][] directions = {
                {-1, 0}, {1, 0},  // cima e baixo
                {0, -1}, {0, 1},  // esquerda e direita
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1} // diagonais
        };

        for (int[] direction : directions) {
            int newX = point.getPositionX() + direction[0];
            int newY = point.getPositionY() + direction[1];
            if (newX >= 0 && newY >= 0 && newX < map.getScenarioSize()[0] && newY < map.getScenarioSize()[1]) {
                String cell = map.get(new Point(newX, newY));
                if (isObstacle(cell)) {
                    obstacles++;
                }
            }
        }
        return obstacles;
    }

    private boolean isObstacle(String cell) {
        return cell.equals(Rock.CHARACTER) || cell.equals(Monster.CHARACTER);
    }

    public PathTracker getPathTracker() {
        return pathTracker;
    }
}