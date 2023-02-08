package com.mycompany.javaspacegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.io.File;

public final class Fases extends JPanel implements ActionListener {

    final int ESTADO_MENU = 4;
    final int ESTADO_EMJOGO = 1;
    final int ESTADO_FASECONCLUIDA = 2;
    final int ESTADO_GAMEOVER = 0;

    private int naveAtual = 1;
    private int vidas = 3;
    private int faseAtual;
    private Image fundo;
    private Ship spaceship;
    private Timer timer;
    private List<Inimigo> inimigos;
    private List<Fases> fases;
    private int emJogo;
    private boolean parabens;
    private int fundoLargura, fundoAltura;
    private int fundox = 0;
    private int fundoy = 0;
    private int FPS = 50;
    private Sequencer player;
    private String musica = "./assets/sound/backsound.mid";

    public int getFundoLargura() {
        return fundoLargura;
    }

    public void setFundoLargura(int fundoLargura) {
        this.fundoLargura = fundoLargura;
    }

    public int getFundoAltura() {
        return fundoAltura;
    }

    public void setFundoAltura(int fundoAltura) {
        this.fundoAltura = fundoAltura;
    }

    public Image getFundo() {
        return fundo;
    }

    public void setFundo(Image fundo) {
        this.fundo = fundo;
    }

    public Ship getSonic() {
        return spaceship;
    }

    public void setSonic(Ship sonic) {
        this.spaceship = sonic;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public List<Inimigo> getInimigos() {
        return inimigos;
    }

    public void setInimigos(List<Inimigo> inimigos) {
        this.inimigos = inimigos;
    }

    public int getEmJogo() {
        return emJogo;
    }

    public void setEmJogo(int emJogo) {
        this.emJogo = emJogo;
    }

    public boolean isParabens() {
        return parabens;
    }

    public void setParabens(boolean parabens) {
        this.parabens = parabens;
    }

    public int getFundox() {
        return fundox;
    }

    public void setFundox(int fundox) {
        this.fundox = fundox;
    }

    public int getFundoy() {
        return fundoy;
    }

    public void setFundoy(int fundoy) {
        this.fundoy = fundoy;
    }

    public int getFPS() {
        return FPS;
    }

    public void setFPS(int FPS) {
        this.FPS = FPS;
    }

    public int[][] getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(int[][] coordenadas) {
        this.coordenadas = coordenadas;
    }

    public void moveFundo() {
        fundox -= 4;
        if (fundox <= -600) {
            fundox = 0;
        }
    }

    public List<Fases> getFases() {
        return fases;
    }

    public void setFases(List<Fases> fases) {
        this.fases = fases;
    }

    public Sequencer getPlayer() {
        return player;
    }

    public void setPlayer(Sequencer player) {
        this.player = player;
    }

    public String getMusica() {
        return musica;
    }

    public void setMusica(String musica) {
        this.musica = musica;
    }

    private int[][] coordenadas = {
        {2380, 400},
        {2600, 378},
        {1380, 405},
        {1780, 425},
        {1280, 410},
        {1380, 345},
        {1200, 350},
        {1760, 380},
        {1790, 415},
        {1980, 320},
        {1560, 370},
        {1510, 340},
        {2380, 400},
        {1560, 370},
        {1380, 405},
        {1380, 345},
        {1510, 340},
        {2380, 400},
        {1560, 370},
        {1380, 405},
        {2380, 400},
        {2600, 378},
        {1380, 405},
        {1780, 425},
        {1280, 410},
        {1380, 345},
        {1200, 350},
        {1760, 380},
        {1790, 415},
        {1980, 320},
        {1560, 370},
        {1510, 340},
        {2380, 400},
        {1560, 370},
        {1380, 405},
        {1380, 345},
        {1510, 340},
        {2380, 400},
        {1560, 370},
        {1380, 405},
        {2380, 400},
        {2600, 378},
        {1380, 405},
        {1780, 425},
        {1280, 410},
        {1380, 345},
        {1200, 350},
        {1760, 380},
        {1790, 415},
        {1980, 320},
        {1560, 370},
        {1510, 340},
        {2380, 400},
        {1560, 370},
        {1380, 405},
        {1380, 345},
        {1510, 340},
        {2380, 400},
        {1560, 370},
        {1380, 405},
        {2380, 400},
        {2600, 378},
        {1380, 405},
        {1780, 425},
        {1280, 410},
        {1380, 345},
        {1200, 350},
        {1760, 380},
        {1790, 415},
        {1980, 320},
        {1560, 370},
        {1510, 340},
        {2380, 400},
        {1560, 370},
        {1380, 405},
        {1380, 345},
        {1510, 340},
        {2380, 400},
        {1560, 370},
        {1380, 405}
    };

    public void tocarMusica(String nome, int repetir) {
        try {
            player = MidiSystem.getSequencer();
            Sequence musica = MidiSystem.getSequence(new File(nome));
            player.open();
            player.setSequence(musica);
            player.setLoopCount(repetir);
            player.start();
        } catch (IOException | InvalidMidiDataException | MidiUnavailableException e) {
            System.out.println("Erro ao tocar: " + e);
        }
    }

    public Fases() throws LineUnavailableException, IOException {

        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(new TecladoAdapter());

        this.setFundo(new ImageIcon("./assets/img/background_menu.jpg").getImage());
        this.setFundoLargura(600);
        this.setFundoAltura(600);
        setSonic(new Ship());
        inicializaInimigos();
        setEmJogo(ESTADO_MENU);
        tocarMusica(getMusica(), 1);
        setTimer(new Timer(250 / getFPS(), this));
        timer.start();
    }

    public void setFase(int fase) {
        switch (fase) {
            case 1 -> {
                spaceship.setImagem(new ImageIcon("./assets/img/nave1.gif").getImage());
                setFundo(new ImageIcon("./assets/img/background_fase1.jpg").getImage());
                setSonic(new Ship());
                inicializaInimigos();
            }
            case 2 -> {
                spaceship.setImagem(new ImageIcon("./assets/img/nave2.gif").getImage());
                setFundo(new ImageIcon("./assets/img/background_fase2.jpg").getImage());
                setSonic(new Ship());
                inicializaInimigos();
            }
            case 3 -> {
                spaceship.setImagem(new ImageIcon("./assets/img/nave1.gif").getImage());
                setFundo(new ImageIcon("./assets/img/background_fase3.jpg").getImage());
                setSonic(new Ship());
                inicializaInimigos();
            }
            case 4 -> {
                spaceship.setImagem(new ImageIcon("./assets/img/nave2.gif").getImage());
                setFundo(new ImageIcon("./assets/img/background_fase4.jpg").getImage());
                setSonic(new Ship());
                inicializaInimigos();
            }
            case 5 -> {
                setFundo(new ImageIcon("./assets/img/background_menu.jpg").getImage());
                inicializaInimigos();
                setSonic(new Ship());
            }

        }
    }

    public void inicializaInimigos() {


        inimigos = new ArrayList<>();
        for (int[] coordenada : coordenadas) {
            inimigos.add(new Inimigo(coordenada[0], coordenada[1]));
            //inimigos.add(new Inimigo(gerador.nextInt(2389), gerador.nextInt(425)));
        }
    }

    @Override
    public void paint(Graphics g) {

        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(this.fundo, this.fundox, this.fundoy, this.getFundoLargura(), this.getFundoAltura(), this);
        graficos.drawImage(this.fundo, this.fundox + this.getFundoLargura(), this.fundoy, this.getFundoLargura(), this.getFundoAltura(), this);
        graficos.drawImage(this.fundo, this.fundox + (this.getFundoLargura() * 2), this.fundoy, this.getFundoLargura(), this.getFundoAltura(), this);

        switch (emJogo) {
            case ESTADO_EMJOGO -> {
                graficos.drawImage(spaceship.getImagem(), spaceship.getX(), spaceship.getY(), spaceship.getAltura() / 2, spaceship.getLargura() / 2, this);

                List<Missel> misseis = spaceship.getMisseis();
                for (int i = 0; i < misseis.size(); i++) {

                    Missel m = (Missel) misseis.get(i);
                    graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
                }
                for (int i = 0; i < inimigos.size(); i++) {

                    Inimigo in = inimigos.get(i);
                    graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
                }
                graficos.setFont(new Font("arial", Font.BOLD, 20));
                graficos.setColor(Color.WHITE);
                graficos.drawString("INIMIGOS: " + inimigos.size(), 5, 20);

                graficos.setFont(new Font("arial", Font.BOLD, 20));
                graficos.setColor(Color.WHITE);
                graficos.drawString("VIDAS: " + vidas, 200, 20);

                graficos.setFont(new Font("arial", Font.BOLD, 20));
                graficos.setColor(Color.WHITE);
                graficos.drawString("NAVE: " + naveAtual, 350, 20);

                graficos.setFont(new Font("arial", Font.BOLD, 20));
                graficos.setColor(Color.WHITE);
                graficos.drawString("FASE: " + faseAtual, 500, 20);

                if (faseAtual == 5) {
                    graficos.setColor(Color.BLACK);
                    graficos.fillRect(0, 50, 1200, 160);

                    graficos.setFont(new Font("arial", Font.BOLD, 20));
                    graficos.setColor(Color.WHITE);
                    graficos.drawString("EQUIPE:", 300, 100);

                    graficos.setFont(new Font("arial", Font.BOLD, 20));
                    graficos.setColor(Color.WHITE);
                    graficos.drawString(" >  LEVY LIRA", 300, 120);

                    graficos.setFont(new Font("arial", Font.BOLD, 20));
                    graficos.setColor(Color.WHITE);
                    graficos.drawString(" >  LISVANETE GARCIA", 300, 140);

                    graficos.setFont(new Font("arial", Font.BOLD, 20));
                    graficos.setColor(Color.WHITE);
                    graficos.drawString(" >  GABRIEL BEZERRA", 300, 160);

                    graficos.setFont(new Font("arial", Font.BOLD, 20));
                    graficos.setColor(Color.WHITE);
                    graficos.drawString(" >  WILLIAM SAMPAIO", 300, 180);

                    ImageIcon unip = new ImageIcon("./assets/img/unip.png");
                    graficos.drawImage(unip.getImage(), 600, 75, null);
                }
            }
            case ESTADO_FASECONCLUIDA -> {
                if (faseAtual != 5) {
                    graficos.setColor(Color.BLACK);
                    graficos.fillRect(0, 50, 1200, 160);

                    graficos.setFont(new Font("arial", Font.BOLD, 60));
                    graficos.setColor(Color.WHITE);
                    graficos.drawString("FASE " + faseAtual, 100, 110);
                    graficos.setFont(new Font("arial", Font.BOLD, 40));
                    graficos.drawString("CONCLUIDA", 100, 160);
                    graficos.setFont(new Font("arial", Font.BOLD, 80));
                    graficos.drawString(">>>", 550, 160);

                }
            }
            case ESTADO_GAMEOVER ->  {
                graficos.setColor(Color.BLACK);
                graficos.fillRect(0, 50, 1200, 160);

                graficos.setColor(Color.WHITE);

                graficos.setFont(new Font("arial", Font.BOLD, 80));
                graficos.drawString("GAME OVER", 100, 160);
            }
            case ESTADO_MENU ->  {
                graficos.setColor(Color.BLACK);
                graficos.fillRect(0, 50, 1200, 160);

                graficos.setColor(Color.WHITE);

                graficos.setFont(new Font("arial", Font.BOLD, 80));
                graficos.drawString("DS1A34", 200, 160);
                graficos.setFont(new Font("arial", Font.BOLD, 20));
                graficos.setColor(Color.WHITE);
                graficos.drawString("PRESSIONE ENTER PARA INICIAR", 440, 550);

            }

        }

        g.dispose();

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        if (inimigos.isEmpty()) {
            emJogo = ESTADO_FASECONCLUIDA;
        }

        List<Missel> misseis = spaceship.getMisseis();

        for (int i = 0; i < misseis.size(); i++) {

            Missel m = (Missel) misseis.get(i);

            if (m.isIsVisible()) {
                m.mexer();
            } else {
                misseis.remove(i);
            }
        }

        for (int i = 0; i < inimigos.size(); i++) {

            Inimigo in = inimigos.get(i);

            if (in.isIsVisible()) {
                in.mexer();
            } else {
                inimigos.remove(i);
            }
        }

        spaceship.mexer();
        if (faseAtual != 5) {
            checarColisoes();
        }

        moveFundo();
        repaint();
    }

    public void checarColisoes() {

        Rectangle formaNave = spaceship.getBounds();
        Rectangle formaInimigo;
        Rectangle formaMissel;

        for (int i = 0; i < inimigos.size(); i++) {

            Inimigo tempInimigo = inimigos.get(i);
            formaInimigo = tempInimigo.getBounds();

            if (formaNave.intersects(formaInimigo)) {

                spaceship.setIsVisible(false);
                tempInimigo.setIsVisible(false);

                emJogo = ESTADO_GAMEOVER;
            }
        }

        List<Missel> misseis = spaceship.getMisseis();

        for (int i = 0; i < misseis.size(); i++) {

            Missel tempMissel = misseis.get(i);
            formaMissel = tempMissel.getBounds();

            for (int j = 0; j < inimigos.size(); j++) {

                Inimigo tempInimigo = inimigos.get(j);
                formaInimigo = tempInimigo.getBounds();

                if (formaMissel.intersects(formaInimigo)) {
                    tempInimigo.setIsVisible(false);
                    tempMissel.setIsVisible(false);

                }
            }
        }

    }

    private class TecladoAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            if (e.getKeyCode() == KeyEvent.VK_1) {
                spaceship.setImagem(new ImageIcon("./assets/img/nave1.gif").getImage());
                naveAtual = 1;
            }

            if (e.getKeyCode() == KeyEvent.VK_2) {
                spaceship.setImagem(new ImageIcon("./assets/img/nave2.gif").getImage());
                naveAtual = 2;
            }

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                switch (emJogo) {
                    case ESTADO_GAMEOVER -> {
                        vidas -= 1;
                        if (vidas == 0) {
                            emJogo = ESTADO_MENU;
                        } else {
                            setFase(faseAtual);
                            emJogo = ESTADO_EMJOGO;
                        }
                    }
                    case ESTADO_FASECONCLUIDA ->  {
                        faseAtual += 1;
                        setFase(faseAtual);
                        emJogo = ESTADO_EMJOGO;
                    }
                    case ESTADO_MENU -> {
                        vidas = 3;
                        faseAtual = 1;
                        setFase(faseAtual);
                        emJogo = ESTADO_EMJOGO;
                    }
                }

            }
            spaceship.keyPressed(e);

            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                emJogo = ESTADO_MENU;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            spaceship.keyReleased(e);
        }
    }

}
