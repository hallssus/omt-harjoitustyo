package snake.ui;

import java.sql.SQLException;
import java.util.HashMap;
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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import snake.database.Database;
import snake.database.ScoreDao;

import snake.parts.Apple;
import snake.parts.Snake;
import snake.parts.Direction;
import snake.parts.Worm;

/**
 * The main method that takes care if the animation and all the visuals.
 *
 * @author hallssus
 */
public class SnakeGame extends Application {

    private int seconds;
    private ScoreDao scoreDao;
    private Database database;

    public static void main(String[] args) {

        launch(args);

    }

    /**
     * Sets the startwindow
     *
     * @param window the window that everithing is being drawn to
     *
     */
    public void start(Stage window) throws Exception {
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
                goSnakeGo(window, 1, player1name.getText(), "");
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
                goSnakeGo(window, 2, player1name.getText(), player2name.getText());
            });

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

        Snake snake = new Snake(height, width, numberOfSnakes, plr1name, plr2name);

        scores.setOnAction((endgame) -> {
            snake.setIsOn(false);

            try {
                addPlayerToDatabase(plr1name, snake.getWorm1length() * seconds);
                if (numberOfSnakes > 1) {
                    addPlayerToDatabase(plr2name, snake.getWorm2length() * seconds);
                }
            } catch (Exception ex) {
                Logger.getLogger(SnakeGame.class.getName()).log(Level.SEVERE, null, ex);
            }

            Worm worm1 = snake.getWorm();
            Worm worm2 = snake.getWorm2();
            setScoreWindow(window, worm1, worm2);

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

    /**
     * After the game is over this shows the played games scores.
     *
     * @param window The window that this is being drawn to
     * @param worm1 Player 1's worm
     * @param worm2 Player 2's worm
     */
    public void setScoreWindow(Stage window, Worm worm1, Worm worm2) {
        int player1length = worm1.getLength();
        int player2length = worm2.getLength();
        BorderPane endwindow = new BorderPane();
        Label endtext = new Label("Game over! Here are the scores:");

        Label name1 = new Label(worm1.getPlayername());
        Label name2 = new Label(worm2.getPlayername());

        Button toHighScores = new Button("To Highscores!");

        endwindow.setTop(endtext);

        int scorefor1 = seconds * player1length;
        if (worm1.getIsIsDead() == true) {
            scorefor1 = scorefor1 - 10;
        }
        String strScore1 = worm1.getPlayername() + ": " + scorefor1;
        Label score1 = new Label(strScore1);
        VBox forPlayerOne = new VBox(8);
        forPlayerOne.getChildren().addAll(score1);
        endwindow.setLeft(forPlayerOne);
        if (player2length != 0) {
            int scorefor2 = seconds * player2length;
            if (worm2.getIsIsDead() == true) {
                scorefor2 = scorefor2 - 10;
            }
            String strScore2 = "" + scorefor2;
            Label score2 = new Label(worm2.getPlayername() + ": " + strScore2);
            Label nimi = new Label(worm2.getPlayername());
            VBox forPlayerTwo = new VBox(8);
            forPlayerTwo.getChildren().addAll(score2);
            endwindow.setRight(forPlayerTwo);
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
        VBox playerbox = new VBox(15);
        VBox scorebox = new VBox(15);
        Map<String, Integer> scores = new HashMap();
        try {
            scores = scoreDao.findTenBest();
        } catch (SQLException ex) {
            Logger.getLogger(SnakeGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(scores);

        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            Label player = new Label(entry.getKey());
            Label score = new Label("" + entry.getValue());
            playerbox.getChildren().add(player);
            scorebox.getChildren().add(score);
        }
        highScores.setLeft(playerbox);
        highScores.setRight(scorebox);
        Scene highScoreScene = new Scene(highScores);
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
        scoreDao.save(player, score);
    }

}
