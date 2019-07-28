package sample;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;

public class Controller {
    public Canvas canvasId;
    public String direction;

    public void onload(){
        canvasId.onMouseReleasedProperty().setValue(null);
        direction = "";
        KeyHandler();
    }

    public void KeyHandler(){
        Move move = new Move(canvasId);
        Scene scene = canvasId.getScene();
        scene.setOnKeyPressed(e ->{
            switch(e.getCode()){
                    case UP:
                        if(!direction.equals("Down")){
                            direction = e.getCode().getName();
                            move.setDirection("up");
                        }
                        break;
                    case DOWN:
                        if(!direction.equals("Up")){
                            direction = e.getCode().getName();
                            move.setDirection("down");
                        }
                        break;
                    case LEFT:
                        if(!direction.equals("Right")){
                            direction = e.getCode().getName();
                            move.setDirection("left");
                        }
                        break;
                    case RIGHT:
                        if(!direction.equals("Left")){
                            direction = e.getCode().getName();
                            move.setDirection("right");
                        }
                        break;
                }
        });

        move.start();
    }
}
