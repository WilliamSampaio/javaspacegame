package com.mycompany.javaspacegame;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public final class Ship {

    private int x, y;
    private int dx, dy;
    private int largura, altura;
    private boolean isVisible;
    private Image imagem;
    private List<Missel> misseis;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public boolean isIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public List<Missel> getMisseis() {
        return misseis;
    }

    public void setMisseis(List<Missel> misseis) {
        this.misseis = misseis;
    }

    
    
    public Ship() {
        setImagem(new ImageIcon("./assets/img/nave1.gif").getImage());
        setAltura(imagem.getHeight(null) / 2);
        setLargura(imagem.getHeight(null) / 2);
        setMisseis(new ArrayList<>());// misseis = new ArrayList<Missel>();

        this.x = 100;
        this.y = 300;
    }

    public void mexer() {

        x += dx;  //
        y += dy;  //

        if (this.x < 1) {
            x = 1;
        }
        if (this.x > 1120) {
            x = 1120;
        }
        if (this.y < 1) {
            y = 1;
        }
        if (this.y > 455) {
            y = 455;
        }
    }

    public void atira() {
        this.misseis.add(new Missel(x + (largura / 3), y + (altura / 7)));
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

    public void keyPressed(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_SPACE) {
            atira();
        }

        if (codigo == KeyEvent.VK_UP) {
            dy = -3;

        }

        if (codigo == KeyEvent.VK_DOWN) {
            dy = 3;
        }

        if (codigo == KeyEvent.VK_LEFT) {
            dx = -3;

        }

        if (codigo == KeyEvent.VK_RIGHT) {
            dx = 3;
        }
    }

    public void keyReleased(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_UP) {
            dy = 0;

        }

        if (codigo == KeyEvent.VK_DOWN) {
            dy = 0;

        }

        if (codigo == KeyEvent.VK_LEFT) {
            dx = 0;

        }

        if (codigo == KeyEvent.VK_RIGHT) {
            dx = 0;

        }
    }

}
