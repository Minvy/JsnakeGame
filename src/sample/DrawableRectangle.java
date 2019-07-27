package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DrawableRectangle extends Rectangle {
    Canvas canvas;
    Color color;

    public DrawableRectangle(Rectangle rect, Canvas canvas){
        super(rect.getX(),rect.getY(),rect.getWidth(),rect.getHeight());
        this.canvas = canvas;
    }

    private void draw(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(color);
        gc.fillRect(getX(),getY(),getWidth(),getHeight());
    }

    public void setColor(Color color) {
        this.color = color;
        draw();
    }

    public Color getColor() {
        return color;
    }
}
