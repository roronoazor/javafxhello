package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

public class Main extends Application {


    Label message = new Label("Enter your name below");

    @Override
    public void start(Stage primaryStage) throws Exception{
        // start is where we write the code
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));


        // declare the text fields
        TextField first_name = new TextField();
        TextField last_name = new TextField();

        //both fields should be wide enough to display column count
        first_name.setPrefColumnCount(15);
        last_name.setPrefColumnCount(15);

        HBox buttonbox = new HBox();

        Button savebtn = new Button("_Save");
        savebtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("this is a save button");
            }
        });
        savebtn.setDefaultButton(true);


        Button nextbtn = new Button("_Next");
        nextbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("this is the next button");
            }
        });

        buttonbox.getChildren().add(savebtn);
        buttonbox.getChildren().add(nextbtn);
        buttonbox.setSpacing(10);


        first_name.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("You have changed the first name");
            }
        });

        last_name.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("You have changed the last name");
            }
        });


        // create the labels
        Label first_name_label = new Label("First Name: ");
        Label last_name_label = new Label("_Last Name: ");

        //bind the label to the according fields
        first_name_label.setLabelFor(first_name);
        last_name_label.setLabelFor(last_name);

        // set mnemonic parsing for a label
        first_name_label.setMnemonicParsing(true);
        last_name_label.setMnemonicParsing(true);


        // select car label
        Label car_label = new Label("Select your car label? ");

        MenuItem benz = new MenuItem("Benz");
        benz.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("You selected the benz, the benz, the benz");
            }
        });

        MenuItem honda = new MenuItem("Honda");
        honda.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("You selected a japanese Honda");
            }
        });

        MenuItem toyota = new MenuItem("Toyota");
        toyota.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("You selected toyota");
            }
        });


        MenuButton car_items = new MenuButton("select car");
        car_items.getItems().addAll(benz, toyota, honda);

        CheckBox fordCbx = new CheckBox("ford");
        CheckBox audiCbx = new CheckBox("audi");
        audiCbx.setAllowIndeterminate(true);

        fordCbx.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (t1 != null && t1){
                    System.out.println("your selection ford");
                    System.out.println(t1);
                }
            }
        });

        audiCbx.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (t1 != null && t1){
                    System.out.println(observableValue.getValue());
                    System.out.println("your selection audi");
                    System.out.println(t1);
                }
            }
        });

        // Create four ToggleButtons
        ToggleButton fordBtn = new ToggleButton("Ford");
        ToggleButton audiBtn = new ToggleButton("Audi");
        ToggleButton ferrariBtn = new ToggleButton("Ferrari");
        ToggleButton porscheBtn = new ToggleButton("Porsche");
        // Create a ToggleGroup
        final ToggleGroup group = new ToggleGroup();
        // Add all ToggleButtons to a ToggleGroup
        group.getToggles().addAll(fordBtn, audiBtn, ferrariBtn, porscheBtn);
        // Create a ChangeListener for the ToggleGroup
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov,
                                final Toggle toggle, final Toggle new_toggle)
            {
                String toggleBtn = ((ToggleButton)new_toggle).getText();
                System.out.println("your selection: " + toggleBtn);
            }});


        // Create a ChoiceBox for cars
        ChoiceBox<String> cars = new ChoiceBox<>();
        // Add the items to the ChoiceBox
        cars.getItems().addAll("Ford", "Audi", "Ferrari", "Porsche");
        // Create the Selection Message Label
        Label selectionMsgLbl = new Label("Your selection:");
        // Create the Selection Value Label
        Label selectedValueLbl = new Label();
        // Bind the value property to the text property of the Label
        selectedValueLbl.textProperty().bind(cars.valueProperty());

        Label carLbl = new Label("Car:");
        // Create a ComboBox for cars
        ComboBox<String> cars_combo = new ComboBox<>();
        // Add the items to the ComboBox
        cars_combo.getItems().addAll("Ford", "Audi", "Ferrari", "Porsche");
        // Select the first car from the list
        cars_combo.getSelectionModel().selectFirst();
        // Add a ChangeListener to the ComboBox
        cars_combo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, final String oldValue, final String newValue)
            {
                System.out.println("Your selection: " + newValue);
            }
        });

        ListView<String> cars_list = new ListView<String>();
        // Add Items to the ListView
        cars_list.getItems().addAll("Ford", "Audi", "Ferrari", "Porsche");
        // Select the first car from the list
        cars_list.getSelectionModel().selectFirst();
        // Add ChangeListener to the ListView
        HashSet<String> selected_car_list = new HashSet<>();

        cars_list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, final String oldValue, final String newValue)
            {
                selected_car_list.add(newValue);
                System.out.println("Your selection: " + selected_car_list);
            }
        });

        // allergies around this time

        // create menubar
        MenuBar menubar = new MenuBar();

        // create menu
        Menu filemenu = new Menu("File");
        Menu editmenu = new Menu("edit");

        // create menu item
        MenuItem openitem = new MenuItem("Open");
        MenuItem newitem = new MenuItem("New");

        openitem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("menu opened");
            }
        });

        newitem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("menu newed");
            }
        });

        filemenu.getItems().addAll(openitem, newitem);

        //add the menus to the menu bar
        // note you can put the menu bar in a HBOX layout and add it to your root grid layout
        menubar.getMenus().addAll(filemenu, editmenu);







        HBox togglebox = new HBox();
        togglebox.getChildren().addAll(fordBtn, ferrariBtn, audiBtn, porscheBtn);
        togglebox.setSpacing(10);

        GridPane root = new GridPane();
        root.addRow(0, this.message);
        root.addRow(1, first_name_label, first_name);
        root.addRow(2, last_name_label, last_name);
        root.addRow(3, buttonbox);
        root.addRow(4, car_label, car_items);
        root.addRow(5, audiCbx, fordCbx);
        root.addRow(6, togglebox);
        root.addRow(7, new Label("Car: "), cars);
        root.addRow(8, selectionMsgLbl, selectedValueLbl);
        root.addRow(9, new Label("Cars Combo: "), cars_combo);
        root.addRow(10, new Label("Cars List: "), cars_list);
        root.addRow(11, new Label("menu bar: "), menubar);



        root.setHgap(10);
        root.setVgap(10);

        //set the size of the grid pane
        root.setMinSize(350, 400);

        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");


        Scene scene = new Scene(root, 400, 400);

        primaryStage.setScene(scene);
        primaryStage.setTitle("An FX example");
        primaryStage.show();



        /*
        Text mytext = new Text("Hello JavaFx");
        VBox rt = new VBox();
        rt.getChildren().add(mytext);



        primaryStage.setTitle("My First Java Fx Application");
        primaryStage.setScene(new Scene(rt, 300, 275));
        primaryStage.show();
        */
    }


    public static void main(String[] args) {
        launch(args);
    }
}
