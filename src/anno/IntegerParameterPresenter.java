package anno;

import javax.swing.JSpinner;

public class IntegerParameterPresenter extends ParameterPresenter {
  IntegerParameterPresenter (Object obj) {
    super(obj);
  }
  
  public Integer getValue() {
    return (Integer) ((JSpinner)parameter).getValue();
  }
  
  public Class getType() {
    return Integer.class;
  }
}

