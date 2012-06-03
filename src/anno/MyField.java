package anno;

import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javax.swing.Box;
import javax.swing.JFrame;


public class MyField {

  protected Field field;
  protected Range rangeAnnotation;
  protected Method getterMethod;
  protected Method setterMethod;
  private static Class<TheBean> theBeanClass;
  protected TheBean theBean;
  private Object component;
  private Box box;
  private JFrame frame;

  MyField(Field field, Box box, JFrame frame) {
    this.box = box;
    this.frame = frame;
    this.field = field;
    TheBean theBean = new TheBean();
    theBean.setDebug(true);
    theBeanClass = TheBean.class;
    rangeAnnotation = field.getAnnotation(Range.class);
    field.getAnnotation(Interface.class);
    
    try {
      getterMethod = theBeanClass.getDeclaredMethod(MyUtils.getterFrom(field.getName()));
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    try {
      setterMethod = theBeanClass.getDeclaredMethod( MyUtils.setterFrom(field.getName()), field.getType() );
    } catch (SecurityException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      setterMethod = null;
    }
  }
  
  public void addComponent() {
    if (isString()) {
      addStringComponent();
    } else if (isInteger()) {
      addIntegerComponent();
    }
    
    
    Method m = null;
    try {
      m = TheBean.class.getDeclaredMethod("addPropertyChangeListener", new Class[] {String.class, PropertyChangeListener.class});
    } catch (Exception e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    
    try {
      m.invoke(EnterValueGUI.theBean, field.getName().toUpperCase(), (PropertyChangeListener) component);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    try {
      m = TheBean.class.getDeclaredMethod("addVetoableChangeListener", new Class[] {String.class, VetoableChangeListener.class});
    } catch (Exception e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    
    try {
      m.invoke(EnterValueGUI.theBean, field.getName().toUpperCase(), (VetoableChangeListener) component);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  public void addIntegerComponent() {
    if (isRange()) {
      addSliderComponent();
    } else {
      addSpinnerComponent();
    }
  }
  
  public void addSliderComponent() {
    FieldSlider slide = null;
    try {
      slide = new FieldSlider(
          this,
          rangeAnnotation.min(),
          rangeAnnotation.max(),
          frame
      );
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (isEnabled()) {
      slide.setEnabled(false);
    }
    component = slide;
    box.add(slide);
    box.add(Box.createVerticalStrut(20));
  }
  
  public void addSpinnerComponent() {
    FieldSpinner spin = null;
    try {
      spin = new FieldSpinner(this, frame);
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (isEnabled()) {
      spin.setEnabled(false);
    }
    component = spin;
    box.add(spin);
    box.add(Box.createVerticalStrut(20));
  }
  
  public void addStringComponent() {
    box.add(Box.createVerticalStrut(20));
    FieldTextField tField = null;
    try {
      tField = new FieldTextField(this, frame);
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (isEnabled()) {
      tField.setEnabled(false);
    }
    component = tField;
    box.add(tField);
    box.add(Box.createVerticalStrut(20));
  }
  
  
  
  
  
  public Boolean isEnabled() {
    return setterMethod == null || Modifier.isPrivate(setterMethod.getModifiers());
  }
  
  public Boolean isString() {
    return field.getType().toString().equals(String.class.toString());
  }
  
  public Boolean isInteger() {
    return field.getType().toString().equals("int") ;
  }
  
  public Boolean isRange() {
     return field.isAnnotationPresent(Range.class);
  }
  
  public Boolean isInterface() {
    return field.isAnnotationPresent(Interface.class);
 }
  
  public Method getGetterMethod() {
    return getterMethod;
  }

  public Method getSetterMethod() {
    return setterMethod;
  }
  
}
