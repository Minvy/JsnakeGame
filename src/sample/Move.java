package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class Move extends Thread{

    private int currentX;
    private int currentY;

    private int tickDelay;

    private boolean running;

    private String direction;

    private Display display;

    public Move(Canvas canvas){
        display = new Display(canvas);

        direction = "right";

        tickDelay = 250;

        running = true;

        currentX = 0;
        currentY = 0;

        display.addTailX(0);
        display.addTailY(0);

        display.addTailX(1);
        display.addTailY(0);

    }

    public void moveUp(){
        currentY--;
    }
    public void moveDown(){
        currentY++;
    }
    public void moveLeft(){
        currentX--;
    }
    public void moveRight(){
        currentX++;
    }

    public boolean indexChecks(){
        if((currentY > -1 && currentY < 17) && (currentX > -1 && currentX < 24)){
            return true;
        }
        return false;
    }

    public void setDirection(String direction){
        this.direction = direction;
    }


    public void move(){

        if(!indexChecks()){
            running = false;
        }else if(snakeReached()){
            running = false;
        }else{
            if(pointReached()){
                display.addTailY(currentY);
                display.addTailX(currentX);
                display.addPoint();
                if(tickDelay > 100){
                    tickDelay -= 10;
                }
            }

            //As the chain moves forward the last block is reset to black color
            display.resetBlockColor();

            display.getTailX().set(display.getTailX().size()-1,currentX);
            display.getTailY().set(display.getTailY().size()-1,currentY);

            for(int i = 0; i < (display.getTailX().size()-1) ; i++){
                display.getTailX().set(i, display.getTailX().get(i+1));
                display.getTailY().set(i, display.getTailY().get(i+1));
            }

            display.refresh();
        }
    }

    public boolean pointReached(){
        if(currentX == display.getPointX() && currentY == display.getPointY()){
           return true;
        }
        return false;
    }

    public boolean snakeReached(){
       if(display.getRectangle(currentY,currentX).getColor() == Color.YELLOW){
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        while(running){
            try{
                switch(this.direction){
                    case "up": this.moveUp();
                    break;
                    case "down": this.moveDown();
                    break;
                    case "left": this.moveLeft();
                    break;
                    case "right": this.moveRight();
                    break;
                }
                this.move();
                Thread.sleep(tickDelay);
            }catch(InterruptedException e){
                System.out.println("Interrupted thread sleep error");
            }
        }
        //refresh.gameOver();
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
