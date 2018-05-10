package snake.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.control.Button;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.*;
import javafx.stage.*;
import javafx.animation.*;
import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import snake.database.Database;
import snake.database.ScoreDao;

import snake.parts.Apple;
import snake.parts.Boost;
import snake.parts.Snake;
import snake.parts.Direction;
import snake.parts.Worm;

/**
 * The main method that takes care if the animation and all the visuals.
 *
 * @author hallssus
 */
public class SnakeGame extends Application {

    private ScoreDao scoreDao;
    private Database database;
    private int seconds;
    private boolean classicbool;

    public static void main(String[] args) {

        launch(args);
    }

    public void start(Stage window) throws Exception {
        setStartWindow(window);
    }

    /**
     *
     * @param window that everything is being drawn to
     * @throws Exception if SQL doesn't go right
     */
    public void setStartWindow(Stage window) throws Exception {
        this.classicbool = false;
        database = new Database("jdbc:sqlite:database.db");
        scoreDao = new ScoreDao(database);

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

        single.setOnAction(singleevent -> {
            setWindowForSingleGame(window);
        });

        //if the other button is pressed
        duel.setOnAction((duelevent) -> {

            Label duelExplanation = new Label("Player 1 is the pink worm and uses arrows. Player 2 is the blue worm and uses WASD.");
            Label appleExplanation = new Label("The red apple makes the worm grom and gives points. The green apple is a boost in speed.");
            VBox texts = new VBox(10);
            VBox pl1 = new VBox(10);
            VBox pl2 = new VBox(10);
            texts.getChildren().addAll(duelExplanation, appleExplanation);
            Button gotitbutton = new Button("Got it!");
            Label pl1n = new Label("Player 1 name:");
            Label pl2n = new Label("Player 2 name:");
            TextField player1name = new TextField();
            TextField player2name = new TextField();
            BorderPane duelpregame = new BorderPane();
            pl1.getChildren().addAll(pl1n, player1name);
            pl2.getChildren().addAll(pl2n, player2name);
            
            duelpregame.setTop(texts);
            duelpregame.setLeft(pl1);
            duelpregame.setRight(pl2);
            duelpregame.setBottom(gotitbutton);

            Scene duelprescene = new Scene(duelpregame, 700, 150);

            window.setScene(duelprescene);
            window.show();

            gotitbutton.setOnAction((gogo) -> {
                goSnakeGo(window, 2, player1name.getText(), player2name.getText());
            });
        });
    }

    /**
     * Sets the window for single player
     *
     * @param window that everything is being drawn to
     */
    public void setWindowForSingleGame(Stage window) {
        Label explanation = new Label("Use WASD or arrows to move!");
        Button gotItButton = new Button("Got it!");
        Label question = new Label("Choose whether you want to play a classic game or not?");
        RadioButton classic = new RadioButton("Classic");
        BorderPane pregame = new BorderPane();
        TextField player1name = new TextField();
        Label playerName = new Label("Player name:");
        VBox nameAndType = new VBox(10);
        nameAndType.getChildren().addAll(question, classic, playerName, player1name);
        pregame.setLeft(nameAndType);
        pregame.setTop(explanation);
        pregame.setBottom(gotItButton);

        Scene prescene = new Scene(pregame, 400, 200);

        window.setScene(prescene);
        window.show();

        classic.setOnAction(a -> {
            this.classicbool = !classicbool;
        });

        gotItButton.setOnAction(go -> {
            goSnakeGo(window, 1, player1name.getText(), "");
        });
    }

    /**
     * Launches the Snake and animates it.
     *
     * @param window The window where to draw.
     * @param numberOfSnakes Number of players, one or two.
     * @param plr1name The name of the player one.
     * @param plr2name The name of the player two.
     */
    public void goSnakeGo(Stage window, int numberOfSnakes, String plr1name, String plr2name) {

        int width = 50;
        int height = 30;
        //size of one square = 20 px;
        int sizeofsquare = 20;

        Button scores = new Button("To scores");

        Canvas canvas = new Canvas(width * sizeofsquare, height * sizeofsquare);
        GraphicsContext drawer = canvas.getGraphicsContext2D();
        Snake snake;
        if (numberOfSnakes > 1) {
            snake = new Snake(height, width, plr1name, plr2name);
        } else {
            snake = new Snake(height, width, plr1name);
        }
        scores.setOnAction((endgame) -> {
            snake.setIsOn(false);

            setScoreWindow(window, snake);

        });

        new AnimationTimer() {
            private long previous;

            @Override
            public void handle(long now) {
                //this is how often it is updated
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
                    drawer.setFill(Color.AQUA);
                    if (!snake.getIsOn()) {
                        drawer.setFill(Color.ALICEBLUE);
                    }
                    snake.getWorm2().getPieces().stream().forEach(piece -> {
                        drawer.fillRect(piece.getX() * sizeofsquare, piece.getY() * sizeofsquare, sizeofsquare, sizeofsquare);
                    });
                    drawer.setFill(Color.CHARTREUSE);
                    Boost boost = snake.getBoost();
                    drawer.fillRect(boost.getX() * sizeofsquare, boost.getY() * sizeofsquare, sizeofsquare, sizeofsquare);
                }
                drawer.setFill(Color.RED);
                Apple apple = snake.getApple();
                drawer.fillRect(apple.getX() * sizeofsquare, apple.getY() * sizeofsquare, sizeofsquare, sizeofsquare);

            }
        }.start();

        //Updates the first worm
        new AnimationTimer() {

            private long previous;

            @Override
            public void handle(long now) {
                //this is how fast the snake moves
                int speed = 5;
                if (!classicbool) {
                    speed = snake.getWorm().getSpeed();
                    if (speed > 12) {
                        speed = 12;
                    }
                }
                if (now - previous < 4E8 - 40000000 * speed) {
                    return;
                }
                previous = now;
                snake.update(snake.getWorm());
                if (!snake.getIsOn()) {
                    stop();
                }
            }
        }.start();

        if (numberOfSnakes > 1) {
            //Updates the second worm
            new AnimationTimer() {
                private long previous;

                @Override
                public void handle(long now) {

                    //this is how fast the snake moves
                    int speed = snake.getWorm2().getSpeed();
                    if (speed > 12) {
                        speed = 12;
                    }
                    if (now - previous < 4E8 - 40000000 * speed) {
                        return;
                    }
                    previous = now;
                    snake.update(snake.getWorm2());
                    if (!snake.getIsOn()) {
                        stop();
                    }
                }
            }.start();
        }
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
            }

        });
        window.setScene(view);
        window.show();
    }

    /**
     * After the game is over this shows the played games scores.
     *
     * @param window The window that this is being drawn to
     * @param snake The game that just finished
     */
    public void setScoreWindow(Stage window, Snake snake) {
        int player1length = snake.getWorm1length();
        int player2length = snake.getWorm2length();
        BorderPane endwindow = new BorderPane();
        Label endtext = new Label("Game over! Here are the scores:");

        Button toHighScores = new Button("To Highscores!");

        endwindow.setTop(endtext);

        int scorefor1 = this.seconds + player1length * 2;
        if (snake.getWorm().getIsIsDead() == true) {
            if (scorefor1 - 20 > 0) {
                scorefor1 = scorefor1 - 20;
            } else {
                scorefor1 = 0;
            }
        }
        
        String strScore1 = snake.getWorm().getPlayername() + ": " + scorefor1;
        Label score1 = new Label(strScore1);
        VBox forPlayerOne = new VBox(10);
        forPlayerOne.getChildren().addAll(score1);
        try {
            addPlayerToDatabase(snake.getWorm().getPlayername(), scorefor1);

        } catch (Exception ex) {
            Logger.getLogger(SnakeGame.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        endwindow.setLeft(forPlayerOne);
        if (player2length != 0) { // if there is only one player, pl2 length is 0
            int scorefor2 = this.seconds + player2length * 2;
            if (snake.getWorm2().getIsIsDead() == true) {
                if (scorefor2 - 20 > 0) {
                    scorefor2 = scorefor2 - 20;
                } else {
                    scorefor2 = 0;
                }
            }
            String strScore2 = snake.getWorm2().getPlayername() + ": " + scorefor2;
            Label score2 = new Label(strScore2);

            VBox forPlayerTwo = new VBox(10);
            forPlayerTwo.getChildren().addAll(score2);
            endwindow.setRight(forPlayerTwo);
            try {
                addPlayerToDatabase(snake.getWorm2().getPlayername(), scorefor2);

            } catch (Exception ex) {
                Logger.getLogger(SnakeGame.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

        endwindow.setBottom(toHighScores);

        Scene afterscene = new Scene(endwindow);

        window.setScene(afterscene);
        window.show();

        toHighScores.setOnAction(e -> {
            setHighScoreWindow(window);
        });
    }

    /**
     * Max 10 best all time high scores are gotten from the database and shown.
     *
     * @param window The window that everything is being drawn to
     */
    void setHighScoreWindow(Stage window) {
        BorderPane highScores = new BorderPane();

        Button newgame = new Button("New game!");
        newgame.setOnAction(e -> {
            try {
                this.seconds = 0;
                setStartWindow(window);

            } catch (Exception ex) {
                Logger.getLogger(SnakeGame.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        });

        VBox playerbox = new VBox(10);
        VBox scorebox = new VBox(10);

        Label explanation = new Label("Top 10 scores: ");
        List<String> scores = new ArrayList<>();
        try {
            scores = scoreDao.findTenBest();

        } catch (SQLException ex) {
            Logger.getLogger(SnakeGame.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        for (String nameandscore : scores) {
            String[] parts = nameandscore.split(":");
            Label player = new Label(parts[0]);
            Label score = new Label(parts[1]);
            playerbox.getChildren().add(player);
            scorebox.getChildren().add(score);
        }
        highScores.setLeft(playerbox);
        highScores.setRight(scorebox);
        highScores.setTop(explanation);
        highScores.setBottom(newgame);
        Scene highScoreScene = new Scene(highScores, 200, 300);
        window.setScene(highScoreScene);
        window.show();
    }

    /**
     * Adds given player to the database
     *
     * @param player The name of the player
     * @param score The score
     *
     */
    void addPlayerToDatabase(String player, Integer score) throws Exception {
        boolean added = false;
        if (scoreDao.getDatabaseSize() > 9) {
            for (String nameAndScore : scoreDao.findTenBest()) {
                String[] parts = nameAndScore.split(":");
                if (score > Integer.parseInt(parts[1])) {
                    scoreDao.save(player, score);
                    added = true;
                }
            }
        } else {
            scoreDao.save(player, score);
            return;
        }
        if (added) {
            scoreDao.deleteWorstScore();
        }
    }

}
