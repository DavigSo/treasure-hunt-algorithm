//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package main.game.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Map {
    private static final int[] ROCK_POSITIONS_X = new int[]{0, 1, 2, 3, 4, 5, 6};
    private static final int[] ROCK_POSITIONS_Y = new int[]{0, 1, 2, 3, 4, 5, 6};
    private String[][] scenario;
    private Point robotLocation;
    private HashMap<String, Point> treasureChests = new HashMap();

    public Map(int scenarioSizeX, int scenarioSizeY) {
        this.scenario = new String[scenarioSizeX][scenarioSizeY];
        this.robotLocation = new Point(0, 0);
        this.generateMap();
    }

    private String[][] generateMap() {
        this.scenario[this.robotLocation.getPositionX()][this.robotLocation.getPositionY()] = "W";
        this.generateRocks();
        this.generateTreasureChests();
        this.generateMapOfTreasure();
        this.generateMonsters();
        return this.scenario;
    }

    private void generateMapOfTreasure() {
        Random random = new Random();
        int mapOfTreasureCount = 0;

        while(mapOfTreasureCount < 1) {
            int mapRandomX = random.nextInt(2, this.scenario.length);
            int mapRandomY = random.nextInt(2, this.scenario.length);
            if (this.scenario[mapRandomX][mapRandomY] == null) {
                this.scenario[mapRandomX][mapRandomY] = MapOfTreasure.CHARACTER;
                ++mapOfTreasureCount;
            }
        }

    }

    private void generateTreasureChests() {
        Random random = new Random();
        int treasureChestCount = 0;
        List<String> treasureCharacters = new LinkedList();
        treasureCharacters.add("E");
        treasureCharacters.add("A");
        treasureCharacters.add("F");

        while(treasureChestCount < 3) {
            int treasureChestsX = random.nextInt(this.scenario.length);
            int treasureChestsY;
            if (treasureChestsX == this.scenario[0].length - 1) {
                treasureChestsY = random.nextInt(this.scenario[0].length);
            } else {
                treasureChestsY = this.scenario.length - 1;
            }

            if (this.scenario[treasureChestsX][treasureChestsY] == null) {
                this.scenario[treasureChestsX][treasureChestsY] = "T";
                int index = random.nextInt(treasureCharacters.size());
                this.treasureChests.put((String)treasureCharacters.get(index), new Point(treasureChestsX, treasureChestsY));
                treasureCharacters.remove(index);
                ++treasureChestCount;
            }
        }

    }

    private void generateMonsters() {
        Random random = new Random();
        List<Monster> monsters = new ArrayList();
        int monsterCount = 0;

        int i;
        while(monsterCount < 3) {
            i = random.nextInt(2, this.scenario.length - 1);
            int monsterRandomY = random.nextInt(2, this.scenario[0].length - 1);
            if (this.scenario[i][monsterRandomY] == null) {
                this.scenario[i][monsterRandomY] = Monster.CHARACTER;
                ++monsterCount;
            }
        }

        for(i = 0; i < monsters.size(); ++i) {
            Point coordinate = ((Monster)monsters.get(i)).getPoints();
            this.scenario[coordinate.getPositionX()][coordinate.getPositionY()] = Monster.CHARACTER;
        }

    }

    private void generateRocks() {
        Random random = new Random();
        List<Rock> rocks = new ArrayList();
        int rockCount = 0;

        int indexRandomX;
        int j;
        while(rockCount < 3) {
            indexRandomX = random.nextInt(ROCK_POSITIONS_X.length);
            int indexRandomY;
            if (indexRandomX < 2) {
                indexRandomY = random.nextInt(2, ROCK_POSITIONS_Y.length);
            } else {
                indexRandomY = random.nextInt(ROCK_POSITIONS_Y.length);
            }

            j = ROCK_POSITIONS_X[indexRandomX];
            int positionY1 = ROCK_POSITIONS_X[indexRandomY];
            int positionX2 = ROCK_POSITIONS_X[indexRandomX];
            int positionY2 = ROCK_POSITIONS_X[indexRandomY] + 1;
            int positionX3 = ROCK_POSITIONS_X[indexRandomX] + 1;
            int positionY3 = ROCK_POSITIONS_X[indexRandomY];
            int positionX4 = ROCK_POSITIONS_X[indexRandomX] + 1;
            int positionY4 = ROCK_POSITIONS_X[indexRandomY] + 1;
            List<Point> rockPoints = new LinkedList();
            rockPoints.add(new Point(j, positionY1));
            rockPoints.add(new Point(positionX2, positionY2));
            rockPoints.add(new Point(positionX3, positionY3));
            rockPoints.add(new Point(positionX4, positionY4));
            if (!rocks.isEmpty()) {
                boolean conflict = false;

                for(int i = 0; i < rocks.size(); ++i) {
                    Rock c = (Rock)rocks.get(i);
                    if (c.hasConflict(rockPoints)) {
                        conflict = true;
                        break;
                    }
                }

                if (conflict) {
                    continue;
                }
            }

            rocks.add(new Rock(rockPoints));
            ++rockCount;
        }

        for(indexRandomX = 0; indexRandomX < rocks.size(); ++indexRandomX) {
            List<Point> points = ((Rock)rocks.get(indexRandomX)).getPoints();

            for(j = 0; j < points.size(); ++j) {
                Point point = (Point)points.get(j);
                this.scenario[point.getPositionX()][point.getPositionY()] = Rock.CHARACTER;
            }
        }

    }

    public void print() {
        for(int i = 0; i < this.scenario.length; ++i) {
            for(int j = 0; j < this.scenario[i].length; ++j) {
                if (this.scenario[i][j] == null) {
                    this.scenario[i][j] = "*";
                }

                if (j == this.scenario[i].length - 1) {
                    System.out.println(this.scenario[i][j]);
                } else {
                    System.out.print(this.scenario[i][j] + " ");
                }
            }
        }

    }

    public Point getRobotLocation() {
        return this.robotLocation;
    }

    public String get(Point point) {
        int x = point.getPositionX();
        int y = point.getPositionY();
        return x >= 0 && y >= 0 && x < this.scenario.length && y < this.scenario[0].length ? this.scenario[x][y] : null;
    }

    public void moveRobot(Point nextPoint) {
        if (nextPoint.getPositionX() >= 0 && nextPoint.getPositionY() >= 0 && nextPoint.getPositionX() < this.scenario.length && nextPoint.getPositionY() < this.scenario[0].length) {
            this.scenario[nextPoint.getPositionX()][nextPoint.getPositionY()] = "W";
            this.scenario[this.robotLocation.getPositionX()][this.robotLocation.getPositionY()] = "*";
            this.robotLocation = nextPoint;
        } else {
            System.out.println("Movimento inválido. O robô não pode se mover para fora do mapa.");
        }
    }

    public void openTreasureChest(Point nextPoint) {
        Iterator<String> it = this.treasureChests.keySet().iterator();

        while(it.hasNext()) {
            String key = (String)it.next();
            if (((Point)this.treasureChests.get(key)).equals(nextPoint)) {
                if (key.equals("F")) {
                    System.out.println("Parabéns você encontrou o tesouro!");
                } else if (key.equals("A")) {
                    System.out.println("O jogo acabou! Você morreu, caiu em uma armadilha");
                } else {
                    System.out.println("Aqui não tem nada");
                    this.scenario[nextPoint.getPositionX()][nextPoint.getPositionY()] = key;
                }
                break;
            }
        }

    }

    public int[] getScenarioSize() {
        int[] size = new int[]{this.scenario.length, this.scenario[0].length};
        return size;
    }

    public Point getTreasureLocation() {
        return (Point)this.treasureChests.get("F");
    }
}
