//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package main.strategies;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import main.game.map.Map;
import main.game.map.Point;

public class ShortestDistance implements Strategy {
    List<Point> visitedPoint = new ArrayList();

    public ShortestDistance() {
    }

    public Point evaluatePossibleNextStep(List<Point> possibleNextSteps, Map map) {
        Point bestStep = null;
        double shortestDistance = Double.MAX_VALUE;
        Point target = map.getTreasureLocation();
        Iterator var7 = possibleNextSteps.iterator();

        while(var7.hasNext()) {
            Point step = (Point)var7.next();
            if (!this.pointValid(step)) {
                double distance = this.calculateDistance(step, target);
                if (distance < shortestDistance) {
                    shortestDistance = distance;
                    bestStep = step;
                }
            }
        }

        this.visitedPoint.add(map.getRobotLocation());
        return bestStep;
    }

    private double calculateDistance(Point a, Point b) {
        return Math.sqrt(Math.pow((double)(a.getPositionX() - b.getPositionX()), 2.0) + Math.pow((double)(a.getPositionY() - b.getPositionY()), 2.0));
    }

    private boolean pointValid(Point p) {
        boolean isValidPoint = false;
        Iterator var3 = this.visitedPoint.iterator();

        while(var3.hasNext()) {
            Point point = (Point)var3.next();
            if (p.getPositionX() == point.getPositionX() || p.getPositionY() == point.getPositionY()) {
                isValidPoint = true;
                break;
            }
        }

        return isValidPoint;
    }
}
