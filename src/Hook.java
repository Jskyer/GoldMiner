import java.awt.*;

public class Hook {
    Image hook = Toolkit.getDefaultToolkit().getImage("./imgs/hook.png");

    void paintHook(Graphics g, int x, int y){
        g.drawImage(hook, x, y, null);
    }
}
