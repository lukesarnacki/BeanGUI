package anno;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class TField extends JTextField implements ActionListener {

    private MyField field = null;
    public TField(MyField field) {
        super("" + 0, 1 + (int)Math.log10(100));
        this.field = field;
        setMaximumSize(EnterValueGUI.DIM);
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) {
      try {
        field.getSetterMethod().invoke(EnterValueGUI.theBean, getText());
      } catch (Exception e) {          
        e.printStackTrace();
      }
    }

}
