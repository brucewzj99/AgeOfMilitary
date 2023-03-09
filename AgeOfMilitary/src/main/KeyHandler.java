package main;

import java.awt.event.*;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, shootPressed;
    private int up, down, left, right, shoot;

    public KeyHandler(int up, int down, int left, int right, int shoot) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.shoot = shoot;

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == up) {
            upPressed = true;
        }
        if (code == down) {
            downPressed = true;
        }
        if (code == left) {
            leftPressed = true;
        }
        if (code == right) {
            rightPressed = true;
        }
        if (code == shoot) {
            shootPressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == up) {
            upPressed = false;
        }
        if (code == down) {
            downPressed = false;
        }
        if (code == left) {
            leftPressed = false;
        }
        if (code == right) {
            rightPressed = false;
        }
        if (code == shoot) {
            shootPressed = false;
        }
    }

}
