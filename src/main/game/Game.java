//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package main.game;

import main.game.map.GameMap;

import main.game.map.Point;
import main.game.map.Rock;
import main.strategies.Voting;
import main.strategies.binaryTree.BinaryTree;

public class Game {
    private GameMap map = new GameMap(8, 8);
    private Player player = new Player(new BinaryTree());

    public Game() {
    }

    public void run() {
        this.map.print();
        System.out.println();
        int moveCount = 0;

        while(moveCount < 100) {
            Point nextPoint = this.player.evaluatePossibleNextStep(this.map);
            if (nextPoint == null) {
                System.out.println("Não há mais movimentos possíveis. O jogo terminou.");
                break;
            }

            String space = this.map.get(nextPoint);
            if (space != null) {
                if (space.equals("T")) {
                    System.out.println("\nVocê morreu, caiu em uma armadilha.");
                    break;
                }

                if (space.equals("T")) {
                    this.map.openTreasureChest(nextPoint);
                    this.map.print();
                    System.out.println("\nTesouro encontrado! O jogo terminou.");
                    break;
                }

                if (space.equals(Rock.CHARACTER)) {
                    System.out.println("\nVocê encontrou uma pedra e mudou de rota. Tente outra direção.");
                    continue;
                }
            }

            this.map.moveRobot(nextPoint);
            this.map.print();
            System.out.println("\nMovimento número: " + (moveCount + 1));
            ++moveCount;
        }

        if (moveCount >= 100) {
            System.out.println("O número máximo de movimentos foi atingido. O jogo terminou.");
        }

    }
}
