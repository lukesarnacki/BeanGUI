package anno;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FieldSpinner extends JSpinner implements ChangeListener,
    PropertyChangeListener {
  
  private static final long serialVersionUID = 1L;
  MyField field = null;

  FieldSpinner(MyField field) {
    this.field = field;
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
        JOptionPane.showMessageDialog(EnterValueGUI.frame, e.getCause(), "Veto", JOptionPane.ERROR_MESSAGE);
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
}
