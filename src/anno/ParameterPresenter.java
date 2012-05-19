package anno;

public abstract class ParameterPresenter {
  
  protected Object parameter;
  public ParameterPresenter(Object obj) {
    parameter = obj; 
  }
  
  public abstract Class getType();
  public abstract Object getValue();

}
