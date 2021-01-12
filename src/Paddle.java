import java.awt.*;

public class Paddle {

    public int paddleNumber;
    public int score;
    public int x, y, width = 50, height = 200;

    public Paddle(Pong pong, int paddleNumber){
        this.paddleNumber = paddleNumber;

        if(paddleNumber == 1){
            this.x = 0;
        }
        if(paddleNumber == 2) {
            this.x = pong.WIDTH - width;
        }
        this.y = pong.HEIGHT/2 - this.height/2;
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);

    }

    public void move(boolean up) {
        int speed = 10;
        if(up){
            if(y - speed > 0){
                y-= speed;
            }else{
                y = 0;
            }
        }
        else{
            if(y + height + speed + 38 < Pong.pong.HEIGHT){
                y+= speed;
            }else{
                y = Pong.pong.HEIGHT - height - 38;
            }
        }
    }
}
