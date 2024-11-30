//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package main.game.map;

public class Point {
    private String id;
    private int positionX;
    private int positionY;
    private double weight;

    public Point(int x, int y) {
        this.setPositionX(x);
        this.setPositionY(y);
    }

    public int getPositionX() {
        return this.positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return this.positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean equals(Object obj) {
        Point p = (Point)obj;
        return this.positionX == p.getPositionX() && this.positionY == p.getPositionY();
    }
}
