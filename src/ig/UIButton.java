package ig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UIButton extends JButton  {

    private Dimension screen;

    public UIButton (String texte) {
        super(texte);
        //this.setFont(new Font("Arial",Font.PLAIN,30));
        this.setPreferredSize(new Dimension(150,60));
        this.setBackground(new Color(0xB7461D));
        this.setForeground(new Color(234, 216, 13));
        //this.setLocation(this.screen.width/2,this.screen.height/2);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }




}
