//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package main.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import main.game.map.Map;
import main.game.map.Monster;
import main.game.map.Point;
import main.game.map.Rock;
import main.strategies.Strategy;

public class Player {
    public static final String CHARACTER = "W";
    private Strategy strategy;
    private Set<Point> visitedPoints;

    public Player(Strategy strategy) {
        this.strategy = strategy;
        this.visitedPoints = new HashSet();
    }

    public Point evaluatePossibleNextStep(Map map) {
        Point robotLocation = map.getRobotLocation();
        List<Point> possibleNextSteps = new ArrayList();
        possibleNextSteps.add(new Point(robotLocation.getPositionX(), robotLocation.getPositionY() + 1));
        possibleNextSteps.add(new Point(robotLocation.getPositionX() + 1, robotLocation.getPositionY()));
        possibleNextSteps.add(new Point(robotLocation.getPositionX() - 1, robotLocation.getPositionY()));
        possibleNextSteps.add(new Point(robotLocation.getPositionX(), robotLocation.getPositionY() - 1));
        List<Point> validSteps = new LinkedList();
        Iterator var5 = possibleNextSteps.iterator();

        while(true) {
            Point p;
            String space;
            do {
                do {
                    int[] scenarioSize;
                    do {
                        do {
                            do {
                                do {
                                    do {
                                        if (!var5.hasNext()) {
                                            this.visitedPoints.add(robotLocation);
                                            return validSteps.isEmpty() ? null : this.strategy.evaluatePossibleNextStep(validSteps, map);
                                        }

                                        p = (Point)var5.next();
                                        scenarioSize = map.getScenarioSize();
                                    } while(p.getPositionX() < 0);
                                } while(p.getPositionY() < 0);
                            } while(p.getPositionX() >= scenarioSize[0]);
                        } while(p.getPositionY() >= scenarioSize[1]);
                    } while(this.visitedPoints.contains(p));

                    space = map.get(p);
                } while(space != null && space.equals(Rock.CHARACTER));
            } while(space != null && space.equals(Monster.CHARACTER));

            validSteps.add(p);
        }
    }
}
