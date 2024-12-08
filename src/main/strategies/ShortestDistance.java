
package main.strategies;

import main.game.map.*;

import java.util.List;

public class ShortestDistance implements Strategy {

    @Override
    public Point evaluatePossibleNextStep(List<Point> possibleNextStep, GameMap gameMap) {
        String[][] scenario = gameMap.getScenario();
        Point treasureLocation = null;
        Point currentPoint = gameMap.getRobotLocation();

        for(int x = 0; x < scenario.length; x++) {
            for(int y = 0; y < scenario[x].length; y++) {
                if(scenario[x][y].equals(TreasureChest.CHARACTER)) {
                    treasureLocation = new Point(x, y);
                    break;
                }
            }
        }
        if(treasureLocation == null) {
            return null;
        }
        Point closestPoint = null;

        for(int i =0; i <possibleNextStep.size(); i++) {
            Point nextPoint = possibleNextStep.get(i);

            if (scenario[nextPoint.getPositionX()][nextPoint.getPositionY()].equals(Rock.CHARACTER) ||
                    scenario[nextPoint.getPositionX()][nextPoint.getPositionY()].equals(Monster.CHARACTER)) {
                continue;
            }

            if(isMovingCloser(currentPoint, nextPoint, treasureLocation)){
                closestPoint = nextPoint;
            }
        }
        return closestPoint;
    }

    private boolean isMovingCloser(Point current, Point next, Point target) {

        return next.getPositionX() <= target.getPositionX() &&
                next.getPositionY() <= target.getPositionY() &&
                next.getPositionX() >= current.getPositionX() &&
                next.getPositionY() >= current.getPositionY();
    }
}
