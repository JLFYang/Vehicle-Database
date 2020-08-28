package database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
public class VehicleUI extends Application
{
	VehicleList data = new VehicleList();
	Vehicle veh = new Vehicle();
	private Button	searchButton;
	private Button	addButton;
	private Button	removeButton;
	private TextField ownerName;
	private TextField carMake;
	private TextField carModel;
	private TextField carYear;
	private TextField carColor;
	private TextField carPlate;
	private ListView<String> vehicleList;
	
	public void start(Stage primaryStage) 
	{
		try {
	  		 BufferedReader in = new BufferedReader(new FileReader("Vehicle.txt"));
	  		 data = VehicleList.loadFrom(in);
	  		 in.close();
	  		}
		catch (FileNotFoundException e){}
		catch (IOException e){}
		
		GridPane aPane = new GridPane();
		aPane.setPadding(new Insets(5, 5, 5, 5));
	
	    Label label1 = new Label("Name"); //First Label (Name)
		ownerName = new TextField();
	    aPane.setMargin(label1, new Insets(0,0,0,10));
	    aPane.setMargin(ownerName, new Insets(0,0,0,10));
	    
		Label label2 = new Label("Make"); //Second Label (Make)
		carMake = new TextField();
		label2.setLayoutX(150);
		carMake.setLayoutX(150);
		aPane.setMargin(label2, new Insets(0,0,0,10));
		aPane.setMargin(carMake, new Insets(0,0,0,10));
		
			
		Label label3 = new Label("Model"); //Third Label (Model)
		carModel = new TextField();
		label3.setLayoutX(300);
		carModel.setLayoutX(300);
		aPane.setMargin(label3, new Insets(0,0,0,10));
		aPane.setMargin(carModel, new Insets(0,0,0,10));
		
		Label label4 = new Label("Year"); //Fourth Label (Year)
		carYear = new TextField();
		label4.setLayoutX(450);
		carYear.setLayoutX(450);
		aPane.setMargin(label4, new Insets(0,0,0,10));
		aPane.setMargin(carYear, new Insets(0, 0,0,10));
		 
		Label label5 = new Label("Color"); //Fifth Label (Color)
		carColor = new TextField();
		label5.setLayoutX(600);
		carColor.setLayoutX(600);
		aPane.setMargin(label5, new Insets(0,0,0,10));
		aPane.setMargin(carColor, new Insets(0, 0,0,10));
		 
		Label label6 = new Label("Plate"); //Sixth Label (Plate)
		carPlate = new TextField();
		label6.setLayoutX(750);
		carPlate.setLayoutX(750);
		aPane.setMargin(label6, new Insets(0,0,0,10));
		aPane.setMargin(carPlate, new Insets(0, 0,0,10));
		 
		searchButton = new Button("Search"); //Search Button
		searchButton.setLayoutY(-50);
		searchButton.setPrefSize(150,30);
		aPane.setMargin(searchButton, new Insets(0,0,10,10));
		searchButton.setOnAction(new EventHandler<ActionEvent>()
				{
					public void handle(ActionEvent event)
					{
						String text = ownerName.getText().trim();
						Vehicle veh = data.findCar(text);
						carMake.setText(veh.getMake());
						carModel.setText(veh.getModel());
						carYear.setText(String.valueOf(veh.getYear()));
						carColor.setText(veh.getColor());
						carPlate.setText(veh.getPlate());
					}
				});
		
		addButton = new Button("Add"); // Add Button
		addButton.setPrefSize(150, 30);
		aPane.setMargin(addButton, new Insets(0,0,10,10));
		addButton.setOnAction(new EventHandler<ActionEvent>()
				{
					public void handle(ActionEvent event)
					{
						String name = ownerName.getText().trim();
				        String make = carMake.getText().trim();
				        String model = carModel.getText().trim();
				        int year = Integer.parseInt(carYear.getText().trim());
				        String color = carColor.getText().trim();
				        String plate = carPlate.getText().trim();
				        Vehicle v = new Vehicle();
				        v.setOwner(name);
				        v.setMake(make);
				        v.setModel(model);
				        v.setYear(year);
				        v.setColor(color);
				        v.setPlate(plate);
				        data.addVehicle(v);
				        update();
					}
				});
		
		removeButton = new Button("Remove"); //Remove Button
		removeButton.setLayoutY(50);
		removeButton.setPrefSize(150,30);
		aPane.setMargin(removeButton, new Insets(0,0,10,10));
		removeButton.setOnAction(new EventHandler<ActionEvent>()
				{
					public void handle(ActionEvent event)
					{
						int index = vehicleList.getSelectionModel().getSelectedIndex();
				        if (index >= 0)
				        {
				        	data.remove(index);
				        	update();
				        }
					}
				});
				
		Label label = new Label("OwnerName"); //Header for my List View
	    aPane.add(label,0,2);
	    aPane.setMargin(label, new Insets(0,0,5,0));
		vehicleList = new ListView<String>(); //List View
		String[] owners = data.listOwners();
	    vehicleList.setItems(FXCollections.observableArrayList(owners));
	    vehicleList.setPrefSize(875, 700);
	    aPane.setMargin(vehicleList, new Insets(0,0,10,10));
	    vehicleList.setOnMouseClicked(new EventHandler<MouseEvent>()
	    {public void handle(MouseEvent mouseEvent)
	    	{
	    		int index = vehicleList.getSelectionModel().getSelectedIndex();
	    		Vehicle veh = data.find(index);
	    		ownerName.setText(veh.getOwner());
	    		carMake.setText(veh.getMake());
				carModel.setText(veh.getModel());
				carYear.setText(String.valueOf(veh.getYear()));
				carColor.setText(veh.getColor());
				carPlate.setText(veh.getPlate());
				update();
	    	}
	    });
	    
	    Group listView = new Group();
	    listView.getChildren().addAll(vehicleList);
	     
	    Group buttons = new Group();
	    buttons.getChildren().addAll(searchButton,addButton,removeButton);
	     
	    Group textField = new Group();
	    textField.getChildren().addAll(ownerName,carMake,carModel,carYear,carColor,carPlate);
	     
	    Group labels = new Group();
	    labels.getChildren().addAll(label1,label2,label3,label4,label5,label6);
	     
	    aPane.add(labels,0,0);
	    aPane.add(textField,0,1);
	    aPane.add(listView,0,3);
	    aPane.add(buttons,1,3);
	    
	    primaryStage.setTitle("Vehicle Database");
	    primaryStage.setResizable(false);
	    primaryStage.setScene(new Scene(aPane, 1100,800));
	    primaryStage.show();
	    update();
		}
		public void update()
		{
			try {
		   		PrintWriter out = new PrintWriter(new FileWriter("Vehicle.txt"));
		   		out.println();
		   		data.saveTo(out);
		   		out.close();
		   		}
			catch (FileNotFoundException e){}
			catch (IOException e){}
			String[] exactList = new String[data.getSize()];
			 for (int i=0; i<data.getSize(); i++)
			 {exactList[i] = data.listOwners()[i];}
			 int selectedIndex = vehicleList.getSelectionModel().getSelectedIndex();
			 vehicleList.setItems(FXCollections.observableArrayList(exactList));
		     vehicleList.getSelectionModel().select(selectedIndex);
		     removeButton.setDisable(vehicleList.getSelectionModel().getSelectedIndex() < 0);
		}
		public static void main(String[] args) { launch(args); }}

