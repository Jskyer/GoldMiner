import java.awt.*;

public class Gold extends Substance{
   public Gold(int t){
       if(t == 1){
           x = (int)(40 + Math.random() * 560);
           y = (int)(160 + Math.random() * 500);
           width = 36;
           height = 36;
           weight = 50;
           grade = 10;
           img = Toolkit.getDefaultToolkit().getImage("./imgs/gold1.gif");
       }
       else if(t == 2){
           x = (int)(70 + Math.random() * 500);
           y = (int)(160 + Math.random() * 470);
           width = 72;
           height = 72;
           weight = 100;
           grade = 20;
           img = Toolkit.getDefaultToolkit().getImage("./imgs/gold2.gif");
       }
       else{
           x = (int)(140 + Math.random() * 360);
           y = (int)(160 + Math.random() * 400);
           width = 175;
           height = 175;
           weight = 180;
           grade = 40;
           img = Toolkit.getDefaultToolkit().getImage("./imgs/gold3.gif");
       }
   }
}
