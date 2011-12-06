package main.model;

/**
 * The interface of the Model.
 */
public abstract class Model {	
	
	/**
	 * Returns the model as the current implementation of the model.
	 * @return An instance of the model.
	 */
	public static Model getInstance() {
		return MySQLModel.getInstance();
	}
	
	
	
}
