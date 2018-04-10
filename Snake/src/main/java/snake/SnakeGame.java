
package snake;

import javafx.scene.canvas.*;
import javafx.stage.*;
import javafx.animation.*;
import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
        
import parts.Apple;
import parts.Snake;
import parts.Direction;

public class SnakeGame extends Application{
    
    public void start(Stage window){
        int width = 50;
        int height = 40;
        //size of one square = 20 px;
        int sizeofsquare = 20;
        
        Canvas canvas = new Canvas(width*20, height*20);
        GraphicsContext drawer = canvas.getGraphicsContext2D();
        
        Snake snake = new Snake(height, width);
        
        new AnimationTimer() {
            private long previous;
            @Override
            public void handle(long now) {
                if (now - previous < 1000000000/60){
                    return;
                }
                previous = now;
                drawer.setFill(Color.BLACK);
                drawer.fillRect(0, 0, width*20, height*20);
                drawer.setFill(Color.WHITE);
                
                if (!snake.getIsOn()){
                    drawer.setFill(Color.BEIGE);
                }
                snake.getWorm().getPieces().stream().forEach(piece -> {
                    drawer.fillRect(piece.getX()*sizeofsquare, piece.getY()*sizeofsquare, sizeofsquare, sizeofsquare);
                });
                drawer.setFill(Color.RED);
                Apple apple = snake.getApple();
                drawer.fillRect(apple.getX()*sizeofsquare, apple.getY()*sizeofsquare, sizeofsquare, sizeofsquare);
            }
        }.start();
        
        new AnimationTimer() {
            private long previous;
            @Override
            public void handle(long now) {
                if (now - previous < 1000000000/10){
                    return;
                }
                previous=now;
                snake.update();
                if (!snake.getIsOn()){
                    stop();
                }
                
            }
        }.start();
        
        BorderPane scenery = new BorderPane();
        scenery.setCenter(canvas);
        
        Scene view = new Scene(scenery);
        
        view.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.UP)){
                snake.getWorm().setDirection(Direction.UP);
            } //muut nappulat
        });
        
        window.setScene(view);
        window.show();
        
    }
    
        public static void main(String[] args) {

        launch(SnakeGame.class);
        
    }
    
}
