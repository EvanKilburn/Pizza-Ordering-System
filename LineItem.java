package application;
/**
 * LineItem describes a single line from pizza order
 * <p>
 * The number of pizzas and pizza object are stored inside  
 * <p>
 * This version uses constructors, acessors, mutators, get cos, to string and a compare to method
 * 
 * @author Evan Kilburn
 * @version 1.0
 */
import java.text.DecimalFormat;

public class LineItem implements java.io.Serializable, Comparable<LineItem>{//start line item
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int numberOfPizzas;
	private Pizza pizza; 
	
	/**
	 * 2 paramater constructor for line item 
	 * 
	 * method sets the values of numberOfPizzas and pizza if there is an error it throws IllegalPizza
	 * @param numPizza Which is the integer value of number of pizzas
	 * @param p Is the pizza object passed in
	 * @throws IllegalPizza
	 */
	public LineItem(int numPizza, Pizza p) throws IllegalPizza {
		if(p == null) {//throws if null = pizza
			throw new IllegalPizza("Null pizza detected");
		}
		else if (numPizza < 1 || numPizza > 100) {//throws if not a valid number of pizzas
			throw new IllegalPizza("Invalid number of pizzas, must be between 1 and 100 (inclusive)");
		}
		this.numberOfPizzas = numPizza;
		this.pizza = p;
	}
	
	/**
	 * constructor for line item if no number of pizzas entered
	 * 
	 * calls the first constructor
	 * @param p Which is a pizza object
	 * @throws IllegalPizza
	 */
	public LineItem(Pizza p) throws IllegalPizza {
		this(1, p);//calls first constructor
	}
	
	/**
	 * mutator that sets number of pizzas
	 * 
	 * sets integer number of pizzas
	 * @param num Is the number of pizzas for the passed in pizza type
	 * @throws IllegalPizza
	 */
	public void setNumber(int num) throws IllegalPizza {
		if (num<1 || num > 100) {//throws IllegalPizza if not a valid number
			throw new IllegalPizza("The number of pizzas you are trying to enter is not valid");
		}
		this.numberOfPizzas = num;
	}
	
	/**
	 * accessor for number of pizzas
	 * 
	 * just returns value for int numberOfPizzas
	 * @return The integer value of numberOfPizzas
	 */
	public int getNumber() {
		return numberOfPizzas;
	}
	
	/**
	 * acessor for pizza
	 * 
	 * pizza is and object
	 * @return Object pizza
	 */
	public Object getPizza() {
		return pizza;
	}
	
	/**
	 * finds the cost of the line item
	 * 
	 * finds cost of one pizza then multiplies it by the quantity of pizzas and then applies discounts with formatted money output
	 * @return Float representing the cost of the pizza
	 */
	public float getCost() {
		DecimalFormat moneyFormat =new DecimalFormat("#.00");//decimal format so cost is to two decimal places
		float cost = this.numberOfPizzas*this.pizza.getCost();
		double discount = 0;
		if (this.numberOfPizzas >= 10 && this.numberOfPizzas <= 20) {//bulk discount
			discount = 0.1*cost;
		}
		else if (this.numberOfPizzas>20) {//bigger bulk discount
			discount = 0.15*cost;
		}
		cost = (float) (cost-discount);
		String money = moneyFormat.format(cost);//formats cost to two decimal places
		return(Float.valueOf(money));
	}
	
	/**
	 * converts line item into a complete string
	 * 
	 * @return String representing line items
	 */
	public String toString() {
		if (this.numberOfPizzas<10) {
			return(" "+this.numberOfPizzas+" "+this.pizza.toString());
		}
		else {
			return(""+this.numberOfPizzas+" "+this.pizza.toString());
		}
	}

	/**
	 * finds the cost difference between the two pizzas
	 * 
	 * @param otherPizza Is of type LineItem representing the other pizza being passed in
	 * @return difference Is of type Integer that represents the difference in cost of the two
	 */
	public int compareTo(LineItem otherPizza) {
		int difference = (int)(otherPizza.getCost() - this.getCost());
		return difference;
	}
	
}//end line item