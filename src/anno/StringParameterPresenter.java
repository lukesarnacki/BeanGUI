package anno;

import javax.swing.JTextField;

public class StringParameterPresenter extends ParameterPresenter {
  
  StringParameterPresenter (Object obj) {
    super(obj);
  }
  
  public String getValue() {
    return ((JTextField)parameter).getText();
  }
  
  public Class getType() {
    return String.class;
  }
}
