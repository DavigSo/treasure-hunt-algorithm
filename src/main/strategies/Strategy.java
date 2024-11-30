//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package main.strategies;

import java.util.List;
import main.game.map.Map;
import main.game.map.Point;

public interface Strategy {
    Point evaluatePossibleNextStep(List<Point> var1, Map var2);
}
