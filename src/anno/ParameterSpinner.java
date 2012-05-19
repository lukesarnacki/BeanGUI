package anno;

import javax.swing.JSpinner;

public class ParameterSpinner extends JSpinner {

  //MyMethod method = null;

  ParameterSpinner() {
    //method = m;
    setPreferredSize(EnterValueGUI.DIM);
    setMaximumSize(EnterValueGUI.DIM);
    setValue(0);
  }

}
