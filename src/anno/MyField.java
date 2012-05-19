package anno;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.swing.Box;


public class MyField {

  protected Field field;
  private Interface interfaceAnnotation;
  protected Range rangeAnnotation;
  protected Method getterMethod;
  private Method setterMethod;
  private static Class theBeanClass;
  protected TheBean theBean;

  MyField(Field field) {
    this.field = field;
    TheBean theBean = new TheBean();
    theBean.setDebug(true);
    theBeanClass = TheBean.class;
    rangeAnnotation = field.getAnnotation(Range.class);
    interfaceAnnotation = field.getAnnotation(Interface.class);
    
    try {
      getterMethod = theBeanClass.getDeclaredMethod(MyUtils.getterFrom(field.getName()));
      setterMethod = theBeanClass.getDeclaredMethod( MyUtils.setterFrom(field.getName()), field.getType() );
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public void addComponents() {
    if (isString()) {
      addStringComponent();
    } else if (isInteger()) {
      addIntegerComponents();
    }
  }
  
  public void addIntegerComponents() {
    if (isRange()) {
      addSliderComponent();
    } else {
      addSpinnerComponent();
    }
  }
  
  public void addSliderComponent() {
    Slider slide = null;
    try {
      slide = new Slider(
          this,
          rangeAnnotation.min(),
          rangeAnnotation.max()
      );
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    EnterValueGUI.box.add(slide);
    EnterValueGUI.box.add(Box.createVerticalStrut(20));
  }
  
  public void addSpinnerComponent() {
    Spinner spin = null;
    try {
      spin = new Spinner(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    EnterValueGUI.box.add(spin);
    EnterValueGUI.box.add(Box.createVerticalStrut(20));
  }
  
  public void addStringComponent() {
    EnterValueGUI.box.add(Box.createVerticalStrut(20));
    TField field = null;
    try {
      field = new TField(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
    EnterValueGUI.box.add(field);
    EnterValueGUI.box.add(Box.createVerticalStrut(20));
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

  public Interface getInterfaceAnnotation() {
    return interfaceAnnotation;
  }

  public Range getRangeAnnotation() {
    return rangeAnnotation;
  }

  public Method getGetterMethod() {
    return getterMethod;
  }

  public Method getSetterMethod() {
    return setterMethod;
  }
  
}
