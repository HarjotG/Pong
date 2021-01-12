import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Pong implements ActionListener, KeyListener {

    public static Pong pong;
    public final int WIDTH = 700, HEIGHT = 700;
    public Renderer renderer;
    public Paddle player1;
    public Paddle player2;
    public Ball ball;
    public boolean bot = false;
    public boolean w, s, up, down;
    public int gameStatus = 0; //0 = Stopped, 1 = Paused, 2 = Playing

    public Pong(){
        Timer timer = new Timer(20, this);
        JFrame jFrame = new JFrame("Pong");
        renderer = new Renderer();
        jFrame.setSize(WIDTH + 14, HEIGHT);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        jFrame.add(renderer);
        jFrame.addKeyListener(this);
        timer.start();
    }

    public void start(){
        gameStatus = 2;
        player1 = new Paddle(this,1);
        player2 = new Paddle(this,2);
        ball = new Ball(this );
    }

    public void update(){
        if(w){
            player1.move(true);
        }
        if(s){
            player1.move(false);
        }
        if(up){
            player2.move(true);
        }
        if(down){
            player2.move(false);
        }
        ball.update(player1, player2);
    }

    public static void main(String[] args){
        pong = new Pong();
    }

    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if(gameStatus == 0){
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", 1, 50));
            g.drawString("PONG", WIDTH / 2 - 70,  50);

            g.setFont(new Font("Arial", 1, 20));
            g.drawString("Press Space To Play", WIDTH / 2 - 90, HEIGHT / 2 -70);
            g.drawString("Press Shift To Play With Bot", WIDTH / 2 - 120, HEIGHT / 2 -50);
        }
        if(gameStatus == 1){
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", 1, 50));
            g.drawString("PAUSED", WIDTH / 2 - 105, HEIGHT / 2 - 25);
        }

        if(gameStatus == 2 || gameStatus == 1) {
            g.setColor(Color.WHITE);
            g.setStroke(new BasicStroke(5f));
            g.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);

            g.setFont(new Font("Arial", 1, 50));
            g.drawString(String.valueOf(player1.score), WIDTH / 2 - 70,  50);
            g.drawString(String.valueOf(player2.score), WIDTH / 2 + 41,  50);

            player1.render(g);
            player2.render(g);
            ball.render(g);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if(gameStatus == 2) {
            update();
        }
        renderer.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int id = e.getKeyCode();
        if(id == KeyEvent.VK_W){
            w = true;
        }
        if(id == KeyEvent.VK_S){
            s = true;
        }
        if(id == KeyEvent.VK_UP){
            up = true;
        }
        if(id == KeyEvent.VK_DOWN){
            down = true;
        }
        if(id == KeyEvent.VK_SHIFT && gameStatus == 0){
            bot = true;
            start();
        }
        if(id == KeyEvent.VK_SPACE){
            if(gameStatus == 0) {
                start();
                bot = false;
            }else if(gameStatus == 1){
                gameStatus = 2;
            }else if(gameStatus == 2){
                gameStatus = 1;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int id = e.getKeyCode();
        if(id == KeyEvent.VK_W){
            w = false;
        }
        if(id == KeyEvent.VK_S){
            s = false;
        }
        if(id == KeyEvent.VK_UP){
            up = false;
        }
        if(id == KeyEvent.VK_DOWN){
            down = false;
        }
    }
}
