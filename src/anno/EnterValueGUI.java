package anno;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class EnterValueGUI {

  public static Box box = null;
  public static JFrame frame = null;
  private static Class<TheBean> theBeanClass = null;
  public static TheBean theBean = null;

  public static void main(String[] args) {

    theBeanClass = TheBean.class;
    try {
      theBean = (TheBean) theBeanClass.newInstance();
    } catch (Exception e) {
      e.printStackTrace();
    }

    theBean.setDebug(true);
    frame = new JFrame("TheBean GUI");
    
    box = new Box(BoxLayout.Y_AXIS);
    frame.add(box);
    
    try {
      addComponents();
    } catch (Exception e) {
      e.printStackTrace();
    }

    frame.setLayout(new FlowLayout());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocation(300, 100);
    frame.pack();
    frame.setVisible(true);

  }

  private static void addComponents() {
    addFieldComponents();
    addMethodComponents();
  }

  private static void addFieldComponents() {
    for (Field f : theBeanClass.getDeclaredFields()) {
      if (f.getAnnotation(Interface.class) == null)
        continue;
      MyField field = new MyField(f);
      field.addComponent();
    }
  }
  
  private static void addMethodComponents() {
    for (Method m : theBeanClass.getDeclaredMethods()) {
      if (m.getAnnotation(Interface.class) == null)
        continue;
      
      MyMethod method = new MyMethod(m);
      method.addComponents();
    }
  }
  
  
  public static final Dimension DIM = new Dimension(50, 30);
}
