package anno;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class MyMethod implements ActionListener {
  
  private Method method;
  private ArrayList<ParameterPresenter> parameterPresenters;
  
  public MyMethod(Method m) {
    parameterPresenters = new ArrayList<ParameterPresenter>();
    method = m;
  }
  
  public void addComponents() {
    addParameterComponents();
    addMethodComponent();
  }
  
  public void addParameterComponents() {
    
    for (Class p : method.getParameterTypes()) {
      
      if (p.toString().equals("int") ){
        ParameterSpinner s = new ParameterSpinner();
        ParameterPresenter parameterPresenter = new IntegerParameterPresenter(s); 
        parameterPresenters.add(parameterPresenter);
        EnterValueGUI.box.add(s);
        
      } else if (p.toString().equals(String.class.toString())) {
        
        ParameterTextField t = new ParameterTextField();
        ParameterPresenter parameterPresenter = new StringParameterPresenter(t); 
        parameterPresenters.add(parameterPresenter);
        EnterValueGUI.box.add(t);
      }
    }
    
  }
  
  public void addMethodComponent() { 
    
    JButton b = new JButton(method.getName());
    b.addActionListener(this);
    EnterValueGUI.box.add(b);
  } 

  @Override
  public void actionPerformed(ActionEvent event) {
    // TODO Auto-generated method stub
    ArrayList<Object> parameters = new ArrayList<Object>(); 
    for (ParameterPresenter p : parameterPresenters) {
      parameters.add((p).getValue());
    }
    
    try {
      method.invoke(EnterValueGUI.theBean, parameters.toArray());
    } catch (Exception e) {
      JOptionPane.showMessageDialog(EnterValueGUI.frame, e.getCause());
      //e.printStackTrace();
    } 
  }
}
