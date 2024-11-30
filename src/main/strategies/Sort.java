//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package main.strategies;

import java.util.List;
import java.util.Random;
import main.game.map.Map;
import main.game.map.Point;

public class Sort implements Strategy {
    public Sort() {
    }

    public Point evaluatePossibleNextStep(List<Point> possibleNextSteps, Map map) {
        Random random = new Random();
        return (Point)possibleNextSteps.get(random.nextInt(possibleNextSteps.size()));
    }
}
