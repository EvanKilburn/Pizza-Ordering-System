package application;
/**
 * A class to make a pizza object
 * <p>
 * The pizza size, cheese, and topings are all stored inside  
 * <p>
 * This version uses constructors,clone, equals and a cost acessors
 * 
 * @author Evan Kilburn
 * @version 1.0
 */
import java.text.DecimalFormat;

public class Pizza implements java.io.Serializable {//beginning of Pizza class 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	enum Size {Small, Medium, Large};
	enum Cheese {Single, Double, Triple};
	enum Topping {None, Single};
	private Size SIZE;
	private Topping PINEAPPLE;
	private Topping GREENPEPPER;
	private Topping HAM;
	private Cheese CHEESE;
	private boolean VEGETARIAN;
	
	/**
	 * five paramater Pizza constructor
	 * 
	 * @param s Is the enum value of Size
	 * @param v Is the boolean value of vegetarian
	 * @param c Is the enum value of Cheese
	 * @param p Is the enum value of Topping for pineapple
	 * @param g Is the enum value of Topping for green pepper
	 * @param h Is the enum value of Topping for ham
	 * @throws IllegalPizza if illegal values
	 */
	public Pizza(Size s, boolean v, Cheese c, Topping p, Topping g, Topping h) throws IllegalPizza {
		if(v && h == Topping.Single) {//vegetarian and ham
			throw new IllegalPizza("A vegetarian pizza cannot have ham");
		}
		else if (s==null  || c==null || p==null || g==null || h==null) {//null values
			throw new IllegalPizza("Error, null value occured");
		}
		this.SIZE = s;//sets following values
		this.VEGETARIAN = v;
		this.CHEESE = c;
		this.PINEAPPLE = p;
		this.GREENPEPPER = g;
		this.HAM = h;
		
	}
	
	/**
	 * second constructor of Pizza with 4 paramaters
	 * 
	 * no value given for vegatarian so assume false, calls 5 parameter constructor to set values
	 * @param s Is the enum value of Size
	 * @param c Is the enum value of Cheese
	 * @param p Is the enum value of Topping for pineapple
	 * @param g Is the enum value of Topping for green pepper
	 * @param h Is the enum value of Topping for ham
	 * @throws IllegalPizza if illegal values
	 */
	public Pizza(Size s, Cheese c, Topping p, Topping g, Topping h) throws IllegalPizza {
		this(s, false, c, p, g, h);//calls first constructor
	}
	
	/**
	 * third constructor for Pizza with no paramaters
	 * 
	 * assume pizza to be made is a small single cheese pizza
	 * @throws IllegalPizza if illegal values
	 */
	public Pizza() throws IllegalPizza {
		this(Size.Small, false, Cheese.Single, Topping.None, Topping.None, Topping.Single);//calls first constructor
	}
	
	/**
	 * finds cost of the pizza
	 * 
	 * @return cost Which is a float representation of how much the pizza costs
	 */
	public float getCost() {
		float cost = 0;
		if (this.SIZE.equals(Size.Small)) {
			cost += 7;
		}
		else if (this.SIZE.equals(Size.Medium)) {
			cost += 9;
		}
		else if (this.SIZE.equals(Size.Large)) {
			cost += 11;
		}
		if (this.CHEESE.equals(Cheese.Double)) {
			cost += 1.50;
		}
		else if (this.CHEESE.equals(Cheese.Triple)) {
			cost += 3;
		}
		if (this.PINEAPPLE.equals(Topping.Single)) {
			cost += 1.50;
		}
		if (this.GREENPEPPER.equals(Topping.Single)) {
			cost += 1.50;
		}
		if (this.HAM.equals(Topping.Single)) {
			cost += 1.50;
		}
		return(cost);
	}
	
	/**
	 * converts the pizza into a String description
	 * 
	 * @return line Which is a String that describes the pizza's attributes
	 */
	@Override
	public String toString() {
		String line = "";
		DecimalFormat moneyFormat =new DecimalFormat("#.00");//decimal format so cost is to two decimal places
		if (VEGETARIAN == true) {
			line = ""+this.SIZE.toString()+" vegetarian pizza, "+this.CHEESE.toString()+" cheese";
		}
		else {
			line = ""+this.SIZE.toString()+" pizza, "+this.CHEESE.toString()+" cheese";
		}
		if (this.PINEAPPLE.equals(Topping.Single)) {
			line = line+", pineapple";
		}
		if (this.GREENPEPPER.equals(Topping.Single)) {
			line = line+", green pepper";
		}
		if (this.HAM.equals(Topping.Single)) {
			line = line+", ham";
		}
		String money = moneyFormat.format(this.getCost());//formats cost to two decimal places
		line = line+". Cost: $"+money+" each.";
		return(line);
	}
	
	/**
	 * checks if two pizzas are identical
	 * 
	 * @return true or false, returns true if two pizzas are equal
	 */
	@Override
	public boolean equals(Object otherPizza) {
		if (otherPizza instanceof Pizza) {
			Pizza p = (Pizza)otherPizza;
			if (this.SIZE == p.SIZE && this.CHEESE == p.CHEESE && this.PINEAPPLE == p.PINEAPPLE && this.GREENPEPPER == p.GREENPEPPER && this.HAM == p.HAM && this.VEGETARIAN == p.VEGETARIAN) {
				return true;
			}
		}
		return false;
	}
	
	//clones the pizza object
	//return: if successful will return clone of type pizza, if fails will return null
	/**
	 * clones the pizza object
	 * 
	 * sets all attributes equal to the current pizza and clones the object with no aliasing
	 * @return if successful will return the clones pizza object, otherwise if it fails it will return null
	 */
	@Override
	public Pizza clone() {
		try {
			Pizza p = new Pizza(SIZE, VEGETARIAN, CHEESE, PINEAPPLE, GREENPEPPER, HAM);
			return(p);
		} catch (IllegalPizza e) {
			e.printStackTrace();
		}
		return(null);
		} 
}//end of Pizza class
