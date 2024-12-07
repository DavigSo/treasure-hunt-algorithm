package main.strategies.binaryTree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import main.game.Player;
import main.game.map.GameMap;
import main.game.map.MapOfTreasure;
import main.game.map.Monster;
import main.game.map.Point;
import main.game.map.Rock;
import main.game.map.TreasureChest;
import main.strategies.Strategy;


public class BinaryTree implements Strategy{

    private NodeTree<String> root;
    private List<NodeTree<String>> sequenceSelected;
    private GameMap gameMap;


    public BinaryTree(GameMap map){
        map.print();
        System.out.println("");
        this.gameMap = map;
        this.root = new NodeTree<>();
        this.sequenceSelected = new LinkedList<>();

        buildTreeAndCalculatePath(map, 0, 0);
    }



    public void insert(String value){
        if(this.root.getValue()== null){
            this.root.setValue(value);
        }else{
            insertInTree(value, this.root);
        }
    }

    public void insertInTree (String value, NodeTree<String> node){
        if(node.getValue().equals(value)){
            if(node.getLeft()==null){
                NodeTree<String> newNode = new NodeTree<String>();
                newNode.setValue(value);
                node.setLeft(newNode);
            }else{
                insertInTree(value, node.getLeft());
            }
        }else{
            if(node.getRight()==null){
                NodeTree<String> newNode = new NodeTree<String>();
                newNode.setValue(value);
                node.setRight(newNode);
            } else{
                insertInTree(value, node.getRight());
            }
        }
    }

    public boolean findBFS (String value) {
        if(this.root == null) {
            System.out.println("Árvore Vazia");
            return false;
        }
        Queue<NodeTree<String>> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            NodeTree<String> nextNode = queue.poll();
            if(nextNode.getValue().equals(value)){
                return true;
            }
            if(nextNode.getLeft() != null){
                queue.add(nextNode.getLeft());
            }
            if(nextNode.getRight() != null){
                queue.add(nextNode.getRight());
            }
        }
        return false;

    }

    public void buildTreeAndCalculatePath(GameMap map, int startX, int startY) {
        boolean[][] visited = new boolean[map.getScenario().length][map.getScenario()[0].length];
        this.root = buildTree(map.getScenario(), startX, startY, visited);
        this.DFS(this.root); // Calcula o caminho até o tesouro
    }

    private NodeTree<String> buildTree(String[][] map, int i, int j, boolean[][] visited) {
        if (i < 0 || i >= map.length || j < 0 || j >= map[0].length || visited[i][j]) {
            return null;
        }

        String cell = map[i][j];
        if (cell.equals(Rock.CHARACTER) || cell.equals(Monster.CHARACTER)) {
            return null; // Não é possível passar por aqui
        }

        visited[i][j] = true; // Marca como visitado

        NodeTree<String> newNode = new NodeTree<>(cell, i, j);
        newNode.setLeft(buildTree(map, i + 1, j, visited)); // Abaixo
        newNode.setRight(buildTree(map, i, j + 1, visited)); // À direita

        return newNode;
    }


    private boolean treasureFound = false;

    public void DFS(NodeTree<String> node) {
        LinkedList<NodeTree<String>> path = new LinkedList<>();
        preOrder(node, TreasureChest.CHEST_TRESURE_CHARACTER, path);
        this.sequenceSelected = path;
    }

    public boolean preOrder(NodeTree<String> node, String target, LinkedList<NodeTree<String>> path) {
        if (node == null || treasureFound) {
            return false;
        }

        path.add(node);
        if (node.getValue().equals(target)) {
            treasureFound = true;
            return true;
        }

        if (preOrder(node.getLeft(), target, path) || preOrder(node.getRight(), target, path)) {
            return true;
        }

        path.removeLast();
        return false;
    }


    public NodeTree<String> buildTreeAndCalculatePath (String [][] map, int i,int j){
        if(i<0 || i >= map.length || j<0 || j>= map[0].length){
            return null;
        }

        if(!map[i][j].equals(Rock.CHARACTER) && !map[i][j].equals(Monster.CHARACTER)){
            NodeTree<String> newNode = new NodeTree<>(map[i][j], i, j);
            newNode.setLeft(buildTreeAndCalculatePath(map, i+1, j));
            newNode.setRight(buildTreeAndCalculatePath(map, i, j+1));
            return newNode;
        }
        return null;
    }

    @Override
    public Point evaluatePossibleNextStep(List<Point> possibleNextSteps, GameMap map) {
        if (!sequenceSelected.isEmpty()) {
            NodeTree<String> nextPoint = sequenceSelected.remove(0);
            if (nextPoint != null && !nextPoint.isNILL()) {
                return new Point(nextPoint.getI(), nextPoint.getJ());
            }
        }
        return null; // Sem movimentos possíveis
    }



}