import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GameWindow extends JFrame {
    static int gState = 0;//游戏状态，0表示未运行，1表示运行中，2表示游戏失败，3表示游戏成功

    static int rdExp = 1; //用于控制金块和石块数目

    private Background backGround = new Background();
    private Line line = new Line(this);
    private Hook hook = new Hook();
    private Image icon = Toolkit.getDefaultToolkit().getImage("./imgs/icon.jpg");
    List<Substance> objList = new ArrayList<Substance>();
    Image canvas; //所有图片绘制在画布上，一并呈现

    public GameWindow(){
        //初始化objList
        int goldNum = (int)(1 + rdExp * 1.2);
        for(int i = 0; i < goldNum; i++){
            double x = Math.random();
            Gold g;
            if(x < 0.3)g = new Gold(1);
            else if(x < 0.8)g = new Gold(2);
            else g = new Gold(3);

            if(!isIntersected(g))objList.add(g);
            else i--;
        }

        int rockNum = (int)(1 + rdExp * 1.5);
        for(int i = 0; i < rockNum; i++){
            double x = Math.random();
            Rock r;
            if(x < 0.3)r = new Rock(1);
            else if(x < 0.8)r = new Rock(2);
            else r = new Rock(3);

            if(!isIntersected(r))objList.add(r);
            else i--;
        }

        rdExp++;
    }

    //判断物体是否重叠
    public boolean isIntersected(Substance obj){
        for(Substance item : objList){
            if(obj.getRect().intersects(item.getRect()))return true;
        }
        return false;
    }

    //启动窗口，并运行游戏
    public void init(int width, int height){
        this.setTitle("Gold Miner");
        this.setIconImage(icon);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        addMouseListener(
            new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    switch(gState){
                        case 0:
                            if(e.getButton() == 1){
                                int mouseX = e.getX();
                                int mouseY = e.getY();
                                if(mouseX >= 200 && mouseX <= 456
                                  && mouseY >= 275 && mouseY <= 350){
                                    gState = 1;
                                    backGround.resetAttr();
                                }
                            }
                            break;
                        case 1:
                            if(e.getButton() == 1){
                                if(line.getState() == 0){
                                    line.setState(1);
                                }
                            }
                            break;
                        case 2:
                        case 3:
                            if(e.getButton() == 3){
                                gState = 0;
                            }
                            break;
                        default:break;
                    }
                }
            }
        );

        while(true){
            repaint();

            if(backGround.noTimeLeft() && gState == 1){
                if(Background.totalGrades >= backGround.goal){
                    if(Background.level >= Background.levelNum){
                        gState = 3;
                        rdExp = 1;
                    }
                    else{
                        Background.level++;
                        Background.startTime = System.currentTimeMillis();
                        Background.totalGrades = 0;
                    }
                }
                else{
                    gState = 2;
                    rdExp = 1;
                }

                dispose();
                GameWindow gw = new GameWindow();
                gw.init(640, 700);
            }

            try{
                Thread.sleep(50);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //绘制游戏组件
    public void paint(Graphics graph){
        canvas = this.createImage(640, 700);
        Graphics cvs= canvas.getGraphics();

        backGround.paintBg(cvs);
        if(gState == 1){
            line.paintLine(cvs);
            for(Substance obj : objList) {
                obj.paint(cvs);
            }
            hook.paintHook(cvs, line.getNextx()-18, line.getNexty()-2);
        }

        graph.drawImage(canvas, 0, 0, null);
    }

    public static void main(String[] args) {
        GameWindow gw = new GameWindow();
        gw.init(640,700);
    }

}
