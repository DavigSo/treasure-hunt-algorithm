package main.game;

import main.game.map.GameMap;
import main.game.map.Point;
import main.game.map.TreasureChest;

import main.strategies.Sort;
import main.strategies.ShortestDistance;
import main.strategies.FewerObstacles;
import main.strategies.Voting;
import main.strategies.binaryTree.BinaryTree;

public class Game {
    private GameMap gameMap;
    private Player player;
    public Game() {
        this.gameMap = new GameMap(8, 8);
        this.player = new Player(new BinaryTree(this.gameMap));
        //this.player = new Player(new Sort());
    }

    public void run() {
        int num_moves = 0;
        int[] chestCounter = {0, 0};

        this.gameMap.print();
        System.out.println();
        int moveCount = 0 + 1;
        while(moveCount <= 100) {
            Point nextPoint = this.player.evaluatePossibleNextStep(gameMap);
            if (nextPoint == null) {
                break;
            } else {
                String space = this.gameMap.get(nextPoint);

                if (space != null && space.equals(TreasureChest.CHARACTER)) {
                    this.gameMap.openTreasureChest(nextPoint);

                    this.gameMap.print();
                    System.out.println();
                    break;
                } else {
                    this.gameMap.moveRobot(nextPoint);
                    num_moves++;
                    System.out.println(num_moves + " movimentos");

                }
            }
            System.out.println();
            this.gameMap.print();
        }
        System.out.println("Foram necessários |" + num_moves + "| movimentos, para finalizar.");
    }

}