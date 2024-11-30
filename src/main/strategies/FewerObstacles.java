//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package main.strategies;

import java.util.Iterator;
import java.util.List;
import main.game.map.Map;
import main.game.map.Monster;
import main.game.map.Point;
import main.game.map.Rock;
import main.tracker.PathTracker;

public class FewerObstacles implements Strategy {
    private final PathTracker pathTracker = new PathTracker();

    public FewerObstacles() {
    }

    public Point evaluatePossibleNextStep(List<Point> possibleNextSteps, Map map) {
        Point bestStep = null;
        int minObstacles = Integer.MAX_VALUE;
        Iterator var5 = possibleNextSteps.iterator();

        while(var5.hasNext()) {
            Point step = (Point)var5.next();
            int obstacles = this.countObstaclesAround(step, map);
            if (obstacles < minObstacles) {
                minObstacles = obstacles;
                bestStep = step;
            }
        }

        if (bestStep != null) {
            this.pathTracker.addPathPoint(bestStep);
            if (minObstacles > 0) {
                this.pathTracker.addObstacle(bestStep);
            }
        }

        return bestStep;
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
        return cell.equals(Rock.CHARACTER) || cell.equals(Monster.CHARACTER);
    }

    public PathTracker getPathTracker() {
        return this.pathTracker;
    }
}
