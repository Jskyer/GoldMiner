import java.awt.*;

public class Substance {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Image img;
    protected int weight; //影响线的抓取速度
    protected int grade; //抓取得分

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getWeight() {
        return weight;
    }

    public int getGrade() {
        return grade;
    }

    public Rectangle getRect(){
        return new Rectangle(x, y, width, height);
    }

    public void paint(Graphics g){
        g.drawImage(img, x, y, null);
    }

}
