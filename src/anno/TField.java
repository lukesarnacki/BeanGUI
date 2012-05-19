package anno;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class TField extends JTextField implements ActionListener {

    private MyField field = null;
    public TField(MyField field) {
        super();
        this.field = field;
        try {
          setText((String)field.getterMethod.invoke(EnterValueGUI.theBean));
        } catch (Exception e) {
          e.printStackTrace();
        }
        setMaximumSize(new Dimension(300, 30));
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
