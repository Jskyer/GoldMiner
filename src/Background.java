import java.awt.*;

public class Background {
    //玩家总得分
    static int totalGrades = 0;
    //关卡编号
    static int level = 1;
    //目标得分
    int goal = 10 * level;
    //关卡总数
    static int levelNum = 5;
    //关卡指定持续时间
    int duration = 20 + level * 8;
    //关卡开始时间
    static long startTime = 0;
    //关卡结束时间
    static long endTime = 0;

    private Image underground = Toolkit.getDefaultToolkit().getImage("./imgs/bg1.jpg");
    private Image sky = Toolkit.getDefaultToolkit().getImage("./imgs/bg2.jpg");
    private Image miner = Toolkit.getDefaultToolkit().getImage("./imgs/miner.png");
    private Image interfaceStart = Toolkit.getDefaultToolkit().getImage("./imgs/start.png");

    //绘制背景
    public void paintBg(Graphics graph){
        graph.drawImage(underground, 0, 100, null);
        graph.drawImage(sky, 0, 0, null);

        switch(GameWindow.gState){
            case 0:
                graph.drawImage(interfaceStart, 200, 275, null);
                break;
            case 1:
                graph.drawImage(miner, 277, 30, null);
                paintText(graph, Color.BLACK, "第 "+level+" 关", 20, 50, 50);
                paintText(graph, Color.BLACK, "目标得分："+goal, 20, 50, 70);
                paintText(graph, Color.BLACK, "当前得分："+totalGrades, 20, 50, 90);

                endTime = System.currentTimeMillis();
                long left = duration-(endTime - startTime)/1000;
                if(left <= 0)left = 0;
                paintText(graph, Color.BLACK, "倒计时："+left, 20, 450, 90);
                break;
            case 2:
                paintText(graph, Color.RED, "失败", 40, 270, 200);
                paintText(graph, Color.BLACK, "第 "+level+" 关", 20, 270, 240);
                paintText(graph, Color.BLACK, "目标得分："+goal, 20, 240, 260);
                paintText(graph, Color.BLACK, "当前得分："+totalGrades, 20, 240, 280);
                paintText(graph, Color.BLUE, "点击鼠标右键,返回开始页面", 20, 190, 300);
                break;
            case 3:
                paintText(graph, Color.RED, "成功", 40, 270, 200);
                paintText(graph, Color.BLACK, "第 "+level+" 关", 20, 270, 240);
                paintText(graph, Color.BLACK, "目标得分："+goal, 20, 240, 260);
                paintText(graph, Color.BLACK, "当前得分："+totalGrades, 20, 240, 280);
                paintText(graph, Color.BLUE, "点击鼠标右键,返回开始页面", 20, 190, 300);
                break;
            default:break;
        }

    }

    //重置游戏全局项
    public void resetAttr(){
        //玩家总得分
        totalGrades = 0;
        //关卡编号
        level = 1;
        //关卡开始时间
        startTime = System.currentTimeMillis();
        //关卡结束时间
        endTime = 0;

        goal = 10 * level;
        duration = 20 + level * 8;
    }

    //判断该关卡倒计时是否结束
    public boolean noTimeLeft(){
        long time = duration - (endTime - startTime)/1000;
        if(time <= 0)return true;
        return false;
    }

    //在窗口绘制文字
    public void paintText(Graphics g, Color color, String text, int size, int x, int y){
        g.setColor(color);
        g.setFont(new Font("宋体", Font.BOLD, size));
        g.drawString(text, x, y);
    }

}
