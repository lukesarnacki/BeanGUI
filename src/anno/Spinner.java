package anno;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Spinner extends JSpinner implements ChangeListener {

    MyField field = null;
  
    Spinner(MyField field){
        this.field = field;
        setPreferredSize(EnterValueGUI.DIM);
        setMaximumSize(EnterValueGUI.DIM);
        try {
          setValue((Integer)field.getterMethod.invoke(EnterValueGUI.theBean));
        } catch (Exception e) {
          e.printStackTrace();
        }
        addChangeListener(this);
    }

    public void stateChanged(ChangeEvent event) {
        try {
          field.getSetterMethod().invoke(EnterValueGUI.theBean, getValue());
        } catch (Exception e) {          
          e.printStackTrace();
        }
    }
}
