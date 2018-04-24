package snake.ui;

import javafx.scene.control.Button;
import java.util.Scanner;
import javafx.scene.canvas.*;
import javafx.stage.*;
import javafx.animation.*;
import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import snake.parts.Apple;
import snake.parts.Snake;
import snake.parts.Direction;
import snake.parts.Worm;

public class SnakeGame extends Application {

    public int seconds;

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
            TextField player1name = new TextField();
            VBox nameandfield = new VBox(8);
            Label playername = new Label("Player name:");
            nameandfield.getChildren().addAll(playername, player1name);
            
            pregame.setLeft(nameandfield);
            pregame.setTop(explanation);
            pregame.setBottom(gotitbutton);
            Scene prescene = new Scene(pregame);

            window.setScene(prescene);
            window.show();

            gotitbutton.setOnAction((go) -> {
                goSnakeGo(window, 1, player1name.getText());
            });

        });

        //if the other button is pressed
        duel.setOnAction((duelevent) -> {

            Label duelexplanation = new Label("Player 1 is the pink worm and uses arrows. Player 2 is the blue worm and uses WASD.");
            Button gotitbutton = new Button("Got it!");
            TextField player1name = new TextField();
            TextField player2name = new TextField();
            BorderPane duelpregame = new BorderPane();
            duelpregame.setTop(duelexplanation);
            duelpregame.setLeft(player1name);
            duelpregame.setRight(player2name);
            duelpregame.setBottom(gotitbutton);
            Scene duelprescene = new Scene(duelpregame);
            
            window.setScene(duelprescene);
            window.show();

            gotitbutton.setOnAction((gogo) -> {
                goSnakeGo(window, 2, player1name.getText());
            });

        });

    }

    public static void main(String[] args) {

        launch(args);

    }

    public void goSnakeGo(Stage window, int numberOfSnakes, String plr1name) {

        int width = 50;
        int height = 30;
        //size of one square = 20 px;
        int sizeofsquare = 20;

        Button scores = new Button("To scores");

        Canvas canvas = new Canvas(width * sizeofsquare, height * sizeofsquare);
        GraphicsContext drawer = canvas.getGraphicsContext2D();

        Snake snake = new Snake(height, width, numberOfSnakes, "", "");

        scores.setOnAction((endgame) -> {
            snake.setIsOn(false);
            int player1Length = snake.getWorm1length();
            int player2Length = snake.getWorm2length();
            System.out.println(player2Length);
            Worm worm1 = snake.getWorm();
            Worm worm2 = snake.getWorm2();
            setScoreWindow(window, player1Length, player2Length, worm1, worm2);

        });

        new AnimationTimer() {
            private long previous;

            @Override
            public void handle(long now) {
                //this is how often it is updatet
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
        //this calculates time
        new AnimationTimer() {

            private long starttime = 0;

            @Override
            public void handle(long now) {
                if (starttime != 0) {
                    if (now > starttime + 1_000_000_000) {
                        seconds++;
                        starttime = now;
                    }
                } else {
                    starttime = now;

                }
            }
        }.start();

        new AnimationTimer() {
            private long previous;

            @Override
            public void handle(long now) {
                //this is how fast the snake moves
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
        scenery.setTop(scores);

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

    public void setScoreWindow(Stage window, int player1length, int player2length, Worm worm1, Worm worm2) {
        BorderPane endwindow = new BorderPane();
        Label endtext = new Label("Game over! Here are the scores:");
        Label player1 = new Label("Player 1:");
        Label player2 = new Label("Player 2:");

        endwindow.setTop(endtext);
        
        int scorefor1 = seconds * player1length;
        if (worm1.getIsIsDead() == true){
            scorefor1 = scorefor1 - 10;
        }
        String strScore1  = "" + scorefor1;
        Label score1 = new Label(strScore1);
        VBox forPlayerOne = new VBox(8);
        forPlayerOne.getChildren().addAll(player1, score1);
        endwindow.setLeft(forPlayerOne);
        if (player2length != 0) {
            int scorefor2 = seconds * player2length;
        if (worm2.getIsIsDead() == true){
            scorefor2 = scorefor2 - 10;
        }
            String strScore2 = "" + scorefor2;
            Label score2 = new Label(strScore2);
            VBox forPlayerTwo = new VBox(8);
            forPlayerTwo.getChildren().addAll(player2, score2);
            endwindow.setRight(forPlayerTwo);
        }

        Scene afterscene = new Scene(endwindow);

        window.setScene(afterscene);
        window.show();
    }

}
