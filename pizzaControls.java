//update every time after adding an event
/* Author: Evan Kilburn
 * Version: 1.0
 * Description: handles the controls for the GUI and events
 */
package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.text.DecimalFormat;

public class pizzaControls{
	private void quantityRangeAlert() {//alerts for an integer not in range 1-100
		Alert info = new Alert(AlertType.WARNING);
    	info.setTitle("Cannot Make Pizza");
    	info.setHeaderText("Quantity Issue");
    	info.setContentText("Quantity must be int the range 1 to 100 (inclusive).");
    	info.showAndWait();
	}
	
	private void quantityIntegerAlert() {//alerts for a non valid integer entry
		Alert info = new Alert(AlertType.WARNING);
    	info.setTitle("Cannot Make Pizza");
    	info.setHeaderText("Quantity Issue");
    	info.setContentText("Non integer quantity entered.");
    	info.showAndWait();
	}
	
	private void vegetarianAlert() {//alerts for ham on vegetarian pizza
		 Alert info = new Alert(AlertType.WARNING);
		 info.setTitle("Cannot Make Pizza");
		 info.setHeaderText("Vegetarian Issue");
		 info.setContentText("Cannot have a vegetarian pizza with ham.");
		 info.showAndWait();
	}
	

	 @FXML
	 private ResourceBundle resources;

	 @FXML
	 private URL location;

	 @FXML
	 private Button btnOrder;
	 
	 @FXML
	 private Label lblMainTitle;

	 @FXML
	 private ComboBox<String> cboSize;
	 
	 @FXML
	 private ComboBox<String> cboVegetarian;

	 @FXML
	 private ComboBox<String> cboCheese;

	 @FXML
	private ComboBox<String> cboGreenP;

	 @FXML
	 private ComboBox<String> cboPineapple;
	 
	 @FXML
	 private ComboBox<String> cboHam;

	 @FXML
	 private TextArea txtOrder;

	 @FXML
	 private TextField txtQuantity;

	 @FXML
	 private Label lblPizzaCost;
	 
	 @FXML
	 private Button btnCost;
	 
	 @FXML
	 private Label lblQuantityCost;

	 @FXML
	 void btnCostClick(ActionEvent event) throws IllegalPizza {
		 String s = cboSize.getSelectionModel().getSelectedItem().toString();
		 Pizza.Size si = Pizza.Size.valueOf(s);//changes string combo box value to corresponding enum value
		 String v = cboVegetarian.getSelectionModel().getSelectedItem().toString();
		 boolean ve = false;
		 if (v=="Yes") {
			 ve = true;
		 }
		 String c = cboCheese.getSelectionModel().getSelectedItem().toString();
		 Pizza.Cheese ch = Pizza.Cheese.valueOf(c);
		 String g = cboGreenP.getSelectionModel().getSelectedItem().toString();
		 Pizza.Topping gr = Pizza.Topping.valueOf(g);
		 String h = cboHam.getSelectionModel().getSelectedItem().toString();
		 Pizza.Topping ha = Pizza.Topping.valueOf(h);
		 String p = cboPineapple.getSelectionModel().getSelectedItem().toString();
		 Pizza.Topping pi = Pizza.Topping.valueOf(p);
		 try {  
			 int quantity = Integer.parseInt(txtQuantity.getText()); //get quantity
			 if (quantity > 0 && quantity <= 100) {//ham on vegetarian pizza
				 if (ve && ha == Pizza.Topping.Single) {
					 vegetarianAlert();
				 }
				 else {
					 DecimalFormat moneyFormat =new DecimalFormat("#.00");//money format
					 Pizza newP = new Pizza(si, ve, ch, pi, gr, ha);//make pizza
					 lblPizzaCost.setText("$"+(moneyFormat.format(newP.getCost())));//update cost
					 lblQuantityCost.setText("$"+(moneyFormat.format(newP.getCost()*quantity)));//update cost
				 }
			 }
			 else {//not valid range
				quantityRangeAlert();
    	    }
		 } catch(NumberFormatException e){ //can't convert quantity text to an integer
			quantityIntegerAlert();
    	} 
	 }
    
	 @FXML
	 void btnOrderClick(ActionEvent event) throws IllegalPizza {
		 String s = cboSize.getSelectionModel().getSelectedItem().toString();
		 Pizza.Size si = Pizza.Size.valueOf(s);//changes string combo box calue to corresponding enum value
		 String v = cboVegetarian.getSelectionModel().getSelectedItem().toString();
		 boolean ve = false;
		 if (v=="Yes") {
			 ve = true;
		 }
		 String c = cboCheese.getSelectionModel().getSelectedItem().toString();
		 Pizza.Cheese ch = Pizza.Cheese.valueOf(c);
		 String g = cboGreenP.getSelectionModel().getSelectedItem().toString();
		 Pizza.Topping gr = Pizza.Topping.valueOf(g);
		 String h = cboHam.getSelectionModel().getSelectedItem().toString();
		 Pizza.Topping ha = Pizza.Topping.valueOf(h);
		 String p = cboPineapple.getSelectionModel().getSelectedItem().toString();
		 Pizza.Topping pi = Pizza.Topping.valueOf(p);
		 try {  
			 int quantity = Integer.parseInt(txtQuantity.getText()); 
			 if (quantity > 0 && quantity <= 100) {
				 if (ve && ha == Pizza.Topping.Single) {//ham on vegetarian pizza
					vegetarianAlert();
				 }
				 else {
					 Pizza newP = new Pizza(si, ve, ch, pi, gr, ha);//create pizza
					 LineItem line = new LineItem(quantity,newP);//create pizza description
					 String currentText = txtOrder.getText();
					 float currentTotal = 0; 
					 try {//fails on first pizza from empty string
						 String lastLine = currentText.substring(currentText.lastIndexOf("\n"));
						 lastLine = lastLine.substring(9);
						 currentTotal = Float.valueOf(lastLine);
						 currentText = currentText.substring(0,currentText.lastIndexOf('\n'));
					 }
					 catch (Exception e) {
						 currentText="";
					 }
					 finally {
						 currentTotal = currentTotal +(newP.getCost()*quantity);
						 currentText = currentText+"\n"+line+"\nTotal: $"+Float.toString(currentTotal)+"0";//updated order summary
						 txtOrder.setText(currentText);//update order
					 }
				 }
			 }
			 else {//range error
    	    	quantityRangeAlert();
    	    }
		} catch(NumberFormatException e){  //non integer quantity
    		quantityIntegerAlert();
    	}
	 }
    
	 //following are string array lists for the combo box options
	 private ObservableList<String> sizeList = FXCollections.observableArrayList("Small", "Medium", "Large");
	 private ObservableList<String> toppingList = FXCollections.observableArrayList("None","Single");
	 private ObservableList<String> cheeseList = FXCollections.observableArrayList("Single","Double","Triple");
	 private ObservableList<String> vegetarianList = FXCollections.observableArrayList("No","Yes");

	 @FXML
	 void initialize() {
		 assert btnOrder != null : "fx:id=\"btnOrder\" was not injected: check your FXML file 'orderSystem.fxml'.";
		 assert cboSize != null : "fx:id=\"cboSize\" was not injected: check your FXML file 'orderSystem.fxml'.";
		 assert cboVegetarian != null : "fx:id=\"cboVegetarian\" was not injected: check your FXML file 'orderSystem.fxml'.";
		 assert cboCheese != null : "fx:id=\"cboCheese\" was not injected: check your FXML file 'orderSystem.fxml'.";
		 assert cboGreenP != null : "fx:id=\"cboGreenP\" was not injected: check your FXML file 'orderSystem.fxml'.";
		 assert cboPineapple != null : "fx:id=\"cboPineapple\" was not injected: check your FXML file 'orderSystem.fxml'.";
		 assert cboHam != null : "fx:id=\"cboHam\" was not injected: check your FXML file 'orderSystem.fxml'.";
		 assert txtOrder != null : "fx:id=\"txtOrder\" was not injected: check your FXML file 'orderSystem.fxml'.";
		 assert txtQuantity != null : "fx:id=\"txtQuantity\" was not injected: check your FXML file 'orderSystem.fxml'.";
		 assert lblPizzaCost != null : "fx:id=\"lblPizzaCost\" was not injected: check your FXML file 'orderSystem.fxml'.";
		 assert btnCost != null : "fx:id=\"btnCost\" was not injected: check your FXML file 'orderSystem.fxml'.";
        
        cboSize.setItems(sizeList);//set combo box options to array list
    	cboSize.setValue("Small");//set default value
    	
    	cboVegetarian.setItems(vegetarianList);
    	cboVegetarian.setValue("No");
    	
    	cboCheese.setItems(cheeseList);
    	cboCheese.setValue("Single");
    	
    	cboGreenP.setItems(toppingList);
    	cboGreenP.setValue("None");
    	
    	cboHam.setItems(toppingList);
    	cboHam.setValue("None");
    	
    	cboPineapple.setItems(toppingList);
    	cboPineapple.setValue("None");
    	
    	txtOrder.setEditable(false);//user can't edit the order text box
    }
}
