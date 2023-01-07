import java.awt.*;
import java.util.List;

public class Line {
    //起点位置坐标
    private int x = 315;
    private int y = 90;

    //终点位置坐标
    private int nextx = 0;
    private int nexty = 0;

    //线长
    private int len = 60;
    //与水平面夹角
    private double theta = 0.05;
    //控制角度变化方向(顺、逆时针)
    private int dir = 1;
    //0表示处于默认摇摆状态，1表示抓取状态，2表示收缩状态,3表示抓取返回状态
    private int state = 0;
    //控制延长和收缩速度
    private int speed = 10;
    //线的关联窗口
    private GameWindow window;
    //被抓取的物体
    private Substance catchedObj = null;

    public Line(GameWindow w){
        window = w;
    }

    public int getNextx() {
        return nextx;
    }

    public int getNexty() {
        return nexty;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    //判断是否有物体成功抓取
    public boolean isCaptured(int nextx, int nexty){
        List<Substance> lst = window.objList;
        for(Substance obj : lst){
            if(nextx >= obj.x && nextx <= obj.x + obj.width
                    && nexty >= obj.y && nexty <= obj.y + obj.height){
                catchedObj = obj;
                return true;
            }
        }
        return false;
    }

    //绘制函数
    public void paintLine(Graphics g){
        nextx = (int)(x + len * Math.cos(theta * Math.PI));
        nexty = (int)(y + len * Math.sin(theta * Math.PI));

        switch (state){
            case 0:
                theta += (dir * 0.005);
                if(theta > 0.95){
                    dir = -1;
                }
                else if(theta < 0.05){
                    dir = 1;
                }
                break;
            case 1:
                if(isCaptured(nextx, nexty)){
                    //System.out.printf("catch!");
                    state = 3;
                    break;
                }

                len += speed;
                if(len >= 800){
                    state = 2;
                }
                break;
            case 2:
                len -= speed;
                if(len <= 60){
                    state = 0;
                }
                break;
            case 3:
                len -= speed;

                if(len <= 60){
                    state = 0;
                    Background.totalGrades += catchedObj.getGrade();
                    catchedObj.setX(-200);
                    catchedObj.setY(-200);
                    catchedObj = null;
                }
                else{
                    catchedObj.setX(nextx-catchedObj.getWidth()/2);
                    catchedObj.setY(nexty);
                    try{
                        Thread.sleep(catchedObj.getWeight());
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
                break;

            default: break;
        }

        g.setColor(Color.red);
        //多次画线实现加粗
        g.drawLine(x-1, y, nextx-1, nexty);
        g.drawLine(x, y, nextx, nexty);
        g.drawLine(x+1, y, nextx+1, nexty);
    }
}
