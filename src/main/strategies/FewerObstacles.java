package main.strategies;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.game.map.*;

public class FewerObstacles implements Strategy {

    /**
     * N is the next location
     * p1 p2 p3
     * p4 N p5
     * p6 p7 p8
     */

    @Override
    public Point evaluatePossibleNextStep(List<Point> possibleNextStep, GameMap gameMap) {
        Iterator<Point> iterator = possibleNextStep.iterator();
        String [][] scenario = gameMap.getScenario();
        int min = Integer.MAX_VALUE;
        Point pointSelected = null;

        while (iterator.hasNext()) {
            Point p = iterator.next();


            if (scenario[p.getPositionX()][p.getPositionY()].equals("X") ||
                    scenario[p.getPositionX()][p.getPositionY()].equals("R") ||
                    scenario[p.getPositionX()][p.getPositionY()].equals("M")) {
                continue;
            }

            int count = evaluatePoint(p, gameMap);
            if (count < min) {
                min = count;
                pointSelected = p;
            }
        }
        return pointSelected;
    }

    private int evaluatePoint(Point p, GameMap gameMap) {
        List<Point> points = new ArrayList<>();
        int count = 0;
        points.add(new Point(p.getPositionX() - 1, p.getPositionY() - 1));
        points.add(new Point(p.getPositionX() - 1, p.getPositionY()));
        points.add(new Point(p.getPositionX() - 1, p.getPositionY() + 1));
        points.add(new Point(p.getPositionX(), p.getPositionY() - 1));
        points.add(new Point(p.getPositionX(), p.getPositionY() + 1));
        points.add(new Point(p.getPositionX() + 1, p.getPositionY() - 1));
        points.add(new Point(p.getPositionX() + 1, p.getPositionY()));
        points.add(new Point(p.getPositionX() + 1, p.getPositionY() + 1));

        for (int i = 0; i < points.size(); i++) {
            String [][] scenario = gameMap.getScenario();
            Point currentPoint = points.get(i);
            int[] scenarioSize = gameMap.getScenarioSize();


            if (currentPoint.getPositionX() < 0 || currentPoint.getPositionX() >= scenarioSize[0] ||
                    currentPoint.getPositionY() < 0 || currentPoint.getPositionY() >= scenarioSize[1]) {
                continue;
            } else {

                if (	scenario[p.getPositionX()][p.getPositionY()].equals("X") ||
                        gameMap.get(currentPoint).equals(Rock.CHARACTER) ||
                        gameMap.get(currentPoint).equals(Monster.CHARACTER)) {
                    count++;
                } else if (gameMap.get(currentPoint).equals(TreasureChest.CHARACTER)) {

                    count = 0;
                }
            }
        }
        return count;
    }
}