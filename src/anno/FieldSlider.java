package anno;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FieldSlider extends JSlider implements ChangeListener,
    PropertyChangeListener, VetoableChangeListener {

  private static final long serialVersionUID = 1L;
  MyField field = null;
  private JFrame frame;

  FieldSlider(MyField field, int min, int max, JFrame frame) {
    super(min, max);
    try {
      setValue((Integer) field.getterMethod.invoke(EnterValueGUI.theBean));
    } catch (Exception e) {
      e.printStackTrace();
    }
    this.frame = frame;
    this.field = field;
    setPaintLabels(true);
    setPaintTrack(true);
    setPaintTicks(true);
    setMinorTickSpacing((max - min) / 10);
    setMajorTickSpacing((max - min) / 5);
    addChangeListener(this);
  }

  public void stateChanged(ChangeEvent event) {
    try {
      field.getSetterMethod().invoke(EnterValueGUI.theBean, getValue());
    } catch (InvocationTargetException e) {
      if (e.getCause() instanceof PropertyVetoException)
        JOptionPane.showMessageDialog(frame, e.getCause(), "Veto", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    // TODO Auto-generated method stub
    removeChangeListener(this);
    setValue((Integer) evt.getNewValue());
    addChangeListener(this);
  }

  @Override
  public void vetoableChange(PropertyChangeEvent evt)
      throws PropertyVetoException {
      System.out.println(evt);
      System.out.println(evt.getNewValue());

  }

}
