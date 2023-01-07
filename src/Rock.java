import java.awt.*;

public class Rock extends Substance{
    public Rock(int t){
        if(t == 1){
            x = (int)(40 + Math.random() * 560);
            y = (int)(160 + Math.random() * 500);
            width = 71;
            height = 71;
            weight = 50;
            grade = 2;
            img = Toolkit.getDefaultToolkit().getImage("./imgs/stone1.png");
        }
        else if(t == 2){
            x = (int)(70 + Math.random() * 500);
            y = (int)(160 + Math.random() * 470);
            width = 105;
            height = 105;
            weight = 120;
            grade = 4;
            img = Toolkit.getDefaultToolkit().getImage("./imgs/stone2.png");
        }
        else{
            x = (int)(90 + Math.random() * 450);
            y = (int)(160 + Math.random() * 435);
            width = 175;
            height = 175;
            weight = 140;
            grade = 6;
            img = Toolkit.getDefaultToolkit().getImage("./imgs/stone3.png");
        }
    }
}
