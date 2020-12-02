package application;
/**
 * Class that extends exception which provides the error message
 * 
 * @author Evan Kilburn
 * @version 1.0
 */
public class IllegalPizza extends Exception{//start IllegalPizza
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @param error Is a String of the error message to be displayed
	 */
	public IllegalPizza(String error) {
		
		super(error);
	}
}//end IllegalPizza