package main.strategies;

import java.util.List;
import main.game.map.Map;
import main.game.map.Point;

public class FewerObstaclesAndShorterDistance implements Strategy {

	@Override
	public Point evaluatePossbileNextStep(List<Point> possibleNextSteps, Map map) {
		Point bestStep = null;
		double bestScore = Double.MAX_VALUE;
		Point target = map.getTreasureLocation();

		for (Point step : possibleNextSteps) {
			int obstacles = countObstaclesAround(step, map);
			double distance = calculateDistance(step, target);
			double score = distance + obstacles * 2; // Ajustar o peso conforme necessário

			if (score < bestScore) {
				bestScore = score;
				bestStep = step;
			}
		}
		return bestStep;
	}

	private int countObstaclesAround(Point point, Map map) {
		int obstacles = 0;
		// Lógica para contar obstáculos nos pontos ao redor
		return obstacles;
	}

	private double calculateDistance(Point a, Point b) {
		return Math.sqrt(Math.pow(a.getPositionX() - b.getPositionX(), 2) +
				Math.pow(a.getPositionY() - b.getPositionY(), 2));
	}
}
