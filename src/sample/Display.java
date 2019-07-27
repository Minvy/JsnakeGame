package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class Display{

    private final double BOX_WIDTH = 30f;
    private final double BOX_HEIGHT = 29f;

    private ArrayList<Integer> tailX;
    private ArrayList<Integer> tailY;
    private ArrayList<ArrayList<DrawableRectangle>> grid;

    private int pointX;
    private int pointY;

    public Display(Canvas canvas){
        tailX = new ArrayList<>();
        tailY = new ArrayList<>();
        drawGrid(canvas);
        addPoint();
    }

    public void drawGrid(Canvas canvas){
        double currentXPos = 0;
        double currentYPos = 0;
        grid = new ArrayList<>();
        for(int i = 0; i < 17; i++){
            ArrayList<DrawableRectangle> row = new ArrayList<>();
            for(int j = 0; j < 24; j++){
                DrawableRectangle rect = new DrawableRectangle(new Rectangle(currentXPos,currentYPos, BOX_WIDTH, BOX_HEIGHT), canvas);
                rect.setColor(Color.BLACK);
                row.add(rect);
                currentXPos += BOX_WIDTH;
            }
            currentXPos = 0;
            currentYPos += BOX_HEIGHT;
            grid.add(row);
        }
    }

    public void resetBlockColor(){
        grid.get(tailY.get(0)).get(tailX.get(0)).setColor(Color.BLACK);
    }

    public void display(){
        for(int i = 0; i < tailX.size(); i++){
            grid.get(tailY.get(i)).get(tailX.get(i)).setColor(Color.YELLOW);
        }
    }

    public void addTailX(int coordinate){
        tailX.add(coordinate);
    }

    public void addTailY(int coordinate){
        tailY.add(coordinate);
    }

    public void addPoint(){
        Random random = new Random();

        pointX = random.nextInt(24);
        pointY = random.nextInt(17);

        grid.get(pointY).get(pointX).setColor(Color.GREEN);
    }

    public ArrayList<Integer> getTailX() {
        return tailX;
    }

    public ArrayList<Integer> getTailY() {
        return tailY;
    }

    public int getPointX() {
        return pointX;
    }

    public int getPointY() {
        return pointY;
    }
}
