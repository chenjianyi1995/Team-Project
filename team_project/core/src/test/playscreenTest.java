package test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.Sprites.Bufflalo;

import static java.lang.System.in;
import static java.lang.System.out;
import static org.junit.Assert.*;
import org.junit.Test;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;


/**
 * Created by JonYoung on 4/6/17.
 */
public class playscreenTest {
    @Test
    public void handleInput() throws Exception {
        Scanner keyboard = new Scanner(in);
        out.println("Press one of the arrow keys.");
        int input = keyboard.nextInt();
        boolean run = true;
        while(run) {
            while (input != Input.Keys.UP || input != Input.Keys.DOWN || input != Input.Keys.LEFT || input != Input.Keys.RIGHT) {
                out.println("Wrong , try again.");
                input = keyboard.nextInt();
                if(input == KeyEvent.VK_X){
                    run = false;
                    break;
                }
            }
        }
    }

}