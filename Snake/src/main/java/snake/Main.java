package snake;

import parts.Snake;
import parts.Apple;

public class Main {

    public static void main(String[] args) {

        Snake snake = new Snake(10, 10);
        for (int i = 0; i < 10; i++) {
            System.out.println(snake);
            snake.update();
            if (!snake.getIsOn()){
                System.out.println("Peli loppui!");
                break;
            }
        }
    }

}
