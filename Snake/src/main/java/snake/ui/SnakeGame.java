package snake.ui;

import javafx.scene.control.Button;
import java.util.Scanner;
import javafx.scene.canvas.*;
import javafx.stage.*;
import javafx.animation.*;
import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import snake.parts.Apple;
import snake.parts.Snake;
import snake.parts.Direction;

public class SnakeGame extends Application {

    public void start(Stage window) {
        Button single = new Button("Alone :(");
        Button duel = new Button("With a friend! ");

        BorderPane startwindow = new BorderPane();

        Label question = new Label("Do you want to play alone or with a friend?");

        startwindow.setTop(question);
        startwindow.setLeft(single);
        startwindow.setRight(duel);

        Scene scene = new Scene(startwindow);

        window.setScene(scene);
        window.show();

        single.setOnAction((singleevent) -> {

            Label explanation = new Label("Use WASD or arrows to move!");
            Button gotitbutton = new Button("Got it!");
            BorderPane pregame = new BorderPane();
            pregame.setTop(explanation);
            pregame.setCenter(gotitbutton);
            Scene prescene = new Scene(pregame);

            window.setScene(prescene);
            window.show();

            gotitbutton.setOnAction((go) -> {
                goSnakeGo(window, 1);
            });

        });

        //if the other button is pressed
        duel.setOnAction((duelevent) -> {
            
            Label duelexplanation = new Label("Player 1 is the blue worm and uses WASD. Player 2 is the pink worm and uses arrows.");
            Button gotitbutton = new Button("Got it!");
            BorderPane duelpregame = new BorderPane();
            duelpregame.setTop(duelexplanation);
            duelpregame.setCenter(gotitbutton);
            Scene duelprescene = new Scene(duelpregame);

            window.setScene(duelprescene);
            window.show();
            
            gotitbutton.setOnAction((gogo) -> {
                goSnakeGo(window, 2);
            });
            
        });

    }

    public static void main(String[] args) {

        launch(args);

    }

    public void goSnakeGo(Stage window, int numberOfSnakes) {

        int width = 50;
        int height = 30;
        //size of one square = 20 px;
        int sizeofsquare = 20;

        Canvas canvas = new Canvas(width * 20, height * 20);
        GraphicsContext drawer = canvas.getGraphicsContext2D();

        Snake snake = new Snake(height, width, numberOfSnakes);

        new AnimationTimer() {
            private long previous;

            @Override
            public void handle(long now) {
                if (now - previous < 1000000000 / 60) {
                    return;
                }
                previous = now;
                drawer.setFill(Color.BLACK);
                drawer.fillRect(0, 0, width * sizeofsquare, height * sizeofsquare);
                drawer.setFill(Color.FUCHSIA);

                if (!snake.getIsOn()) {
                    drawer.setFill(Color.PINK);
                }
                snake.getWorm().getPieces().stream().forEach(piece -> {
                    drawer.fillRect(piece.getX() * sizeofsquare, piece.getY() * sizeofsquare, sizeofsquare, sizeofsquare);
                });
                if (numberOfSnakes > 1) {
                    if (!snake.getIsOn()) {
                        drawer.setFill(Color.ALICEBLUE);
                    }

                    drawer.setFill(Color.AQUA);
                    snake.getWorm2().getPieces().stream().forEach(piece -> {
                        drawer.fillRect(piece.getX() * sizeofsquare, piece.getY() * sizeofsquare, sizeofsquare, sizeofsquare);
                    });
                }
                drawer.setFill(Color.RED);
                Apple apple = snake.getApple();
                drawer.fillRect(apple.getX() * sizeofsquare, apple.getY() * sizeofsquare, sizeofsquare, sizeofsquare);
            }
        }.start();

        new AnimationTimer() {
            private long previous;

            @Override
            public void handle(long now) {
                if (now - previous < 2E8) {
                    return;
                }
                previous = now;
                snake.update();
                if (!snake.getIsOn()) {
                    stop();
                }

            }
        }.start();

        BorderPane scenery = new BorderPane();
        scenery.setCenter(canvas);

        Scene view = new Scene(scenery);

        view.setOnKeyPressed((e) -> {
            if (e.getCode().equals(KeyCode.UP)) {
                snake.getWorm().setDirection(Direction.UP);
            } else if (e.getCode().equals(KeyCode.DOWN)) {
                snake.getWorm().setDirection(Direction.DOWN);
            } else if (e.getCode().equals(KeyCode.LEFT)) {
                snake.getWorm().setDirection(Direction.LEFT);
            } else if (e.getCode().equals(KeyCode.RIGHT)) {
                snake.getWorm().setDirection(Direction.RIGHT);
            } else if (e.getCode().equals(KeyCode.W)) {
                snake.getWorm2().setDirection(Direction.UP);
            } else if (e.getCode().equals(KeyCode.A)) {
                snake.getWorm2().setDirection(Direction.LEFT);
            } else if (e.getCode().equals(KeyCode.S)) {
                snake.getWorm2().setDirection(Direction.DOWN);
            } else if (e.getCode().equals(KeyCode.D)) {
                snake.getWorm2().setDirection(Direction.RIGHT);
            } else if (e.getCode().equals(KeyCode.K)) {
                //in case you want to quit
                snake.setIsOn(false);

            }

        });
        window.setScene(view);
        window.show();
    }

}