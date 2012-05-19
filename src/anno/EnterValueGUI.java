package anno;

import java.awt.Dimension;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class EnterValueGUI {

  public static Box box = null;
  private static Class theBeanClass = null;
  public static TheBean theBean = null;

  public static void main(String[] args) {

    theBeanClass = TheBean.class;
    try {
      theBean = (TheBean) theBeanClass.newInstance();
    } catch (Exception e) {
      e.printStackTrace();
    }

    theBean.setDebug(true);
    JFrame frame = new JFrame("TheBean GUI");

    box = new Box(BoxLayout.Y_AXIS);
    frame.add(box);

    try {
      addComponents();
    } catch (Exception e) {
      e.printStackTrace();
    }

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocation(700, 400);
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
      field.addComponents();
    }
  }
  
  private static void addMethodComponents() {
    for (Method m : theBeanClass.getDeclaredMethods()) {
      if (m.getAnnotation(Interface.class) == null)
        continue;
      
      System.out.println(m.getName());
      for (Class p : m.getParameterTypes()) {
        System.out.println(p);
      }

      MyMethod method = new MyMethod(m);
    }
  }
  
  
  public static final Dimension DIM = new Dimension(50, 30);
}
