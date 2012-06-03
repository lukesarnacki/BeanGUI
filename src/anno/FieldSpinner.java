package anno;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FieldSpinner extends JSpinner implements ChangeListener,
    PropertyChangeListener, VetoableChangeListener {
  
  private static final long serialVersionUID = 1L;
  MyField field = null;
  private JFrame frame;

  FieldSpinner(MyField field, JFrame frame) {
    this.field = field;
    this.frame = frame;
    setPreferredSize(EnterValueGUI.DIM);
    setMaximumSize(EnterValueGUI.DIM);
    try {
      setValue((Integer) field.getterMethod.invoke(EnterValueGUI.theBean));
    } catch (Exception e) {
      e.printStackTrace();
    }
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
    setValue(evt.getNewValue());
    addChangeListener(this);
  }
  
  public void vetoableChange(PropertyChangeEvent evt)
      throws PropertyVetoException {
      System.out.println(evt);
      System.out.println(evt.getNewValue());
  }
}
