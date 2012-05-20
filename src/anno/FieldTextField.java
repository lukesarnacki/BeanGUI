package anno;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FieldTextField extends JTextField implements ActionListener,
    PropertyChangeListener, VetoableChangeListener {

  private static final long serialVersionUID = 1L;
  private MyField field = null;

  public FieldTextField(MyField field) {
    super();
    this.field = field;
    try {
      setText((String) field.getterMethod.invoke(EnterValueGUI.theBean));
    } catch (Exception e) {
      e.printStackTrace();
    }
    setMaximumSize(new Dimension(300, 30));
    addActionListener(this);
  }

  public void actionPerformed(ActionEvent event) {
    try {
      field.getSetterMethod().invoke(EnterValueGUI.theBean, getText());
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
    removeActionListener(this);
    setText((String) evt.getNewValue());
    addActionListener(this);
  }
  public void vetoableChange(PropertyChangeEvent evt)
      throws PropertyVetoException {

  }
}
