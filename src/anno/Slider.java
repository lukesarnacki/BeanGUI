package anno;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Slider extends JSlider implements ChangeListener {

    MyField field = null;
    Slider(MyField field, int min, int max){
        super(min, max);
        try {
          setValue((Integer)field.getterMethod.invoke(EnterValueGUI.theBean));
        } catch (Exception e) {         
          e.printStackTrace();
        }
        this.field = field;
        setPaintLabels(true);
        setPaintTrack(true);
        setPaintTicks(true);
        setMinorTickSpacing((max - min)/10);
        setMajorTickSpacing((max - min)/5);
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
