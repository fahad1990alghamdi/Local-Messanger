/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LocalMessenger;

import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;


public class ChatWindow {
    
   private Stage primaryStage;
   private HBox chatWindow;
   private VBox imageArea;
   private VBox chatArea;
   private TextFlow msgHistory;
   private Rectangle2D screenBounds;
   private ChatWindow connectedUser;
   private Messenger messenger;
   private String imageName;
   private String userName;
   private final String defaultSize = "12"; 
   private String currentFontColor;
   private String currentFontSize;
   
   public ChatWindow(Stage stage, Rectangle2D bounds, Boolean parent, Messenger msgr) {
       primaryStage = stage;
       screenBounds = bounds;
       messenger = msgr;
       currentFontColor = "BLACK";
       currentFontSize = "12";
   }
   public void setConnectedUser(ChatWindow user) {
       connectedUser = user;
   }
   
   public void setPicture(String image) {
       imageName = image;
   }
   public void setName(String name) {
       userName = name;
   }
   public String getName() {
       return userName; 
   }
   public Color getColor() {
       
       Color color = Color.BLACK;
       
       switch (currentFontColor) {
            
            case "BLACK":
               color = Color.BLACK;
               break;
            case "SILVER":
               color = Color.SILVER;
               break;
            case "GRAY":
               color = Color.GRAY;
               break; 
            case "PINK":
               color = Color.PINK;
               break;
            case "GOLD":
               color = Color.GOLD;
               break;    
            case "BLUE":
               color = Color.BLUE;
               break;
       }
       return color;
   }
   public void displayMessage(ArrayList<Message> msgList) {
         msgHistory.getChildren().clear();
         for(Message m : msgList)
         {
             msgHistory.getChildren().add(m.getMessage());
         }
//       msgHistory.getChildren().add(message);
//       System.out.println(getName()+" == "+message.getText());
   }

   public void loadChatWindow (double x, double y) {
       initializeImageArea();
       initializeChatArea();
        
       chatWindow = new HBox();
       chatWindow.getChildren().add(imageArea);
       chatWindow.getChildren().add(chatArea);
        
       Scene scene = new Scene(chatWindow,getWidth()-20, getHeight(), Color.PURPLE);
       scene.getStylesheets().add(ChatWindow.class.getResource("style.css").toExternalForm());
       
       primaryStage.setScene(scene);
       
       x = (x==0) ? 0 : x-getWidth();
       
       primaryStage.setX(x);
       primaryStage.setY(y);
       primaryStage.show();
   }
   
    private void initializeImageArea() {
        imageArea = new VBox();
        imageArea.setId("imageArea");
        
        ImageView userImage = new ImageView();
        double size = getWidth()/4;
        System.out.println(getClass().getResource(imageName).toExternalForm()); 
        Image image = new Image(getClass().getResource(imageName).toExternalForm(), size, size, true, true);
        
        userImage.setImage(image);
        imageArea.getChildren().add(userImage);
        imageArea.setAlignment(Pos.CENTER);
//        imageArea.setStyle("-fx-background-color: red");
        
    }
    private void initializeChatArea() {
        
        chatArea = new VBox();
        chatArea.setId("chatArea");
        
        TextArea message = new TextArea();
         
        msgHistory = new TextFlow();
        msgHistory.setId("chatHistory");
        msgHistory.setMinWidth(435);
        msgHistory.setMinHeight(392);
        
        ScrollPane msgHistorySP = new ScrollPane();
        msgHistorySP.setContent(msgHistory);
        msgHistorySP.setMinWidth(200);
        msgHistorySP.setMinHeight(400);
        msgHistorySP.setMaxHeight(400);
        
        HBox setting = new HBox();
        ObservableList colorItems = FXCollections.observableArrayList("BLACK",
                "SILVER", "GRAY", "PINK", "WHITE", "GOLD", "BLUE");
        ComboBox bgColor = new ComboBox(colorItems);
        bgColor.setPromptText("Background");
        bgColor.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue observable, String oldValue, String newValue) {
                System.out.println("New Background selected : "+newValue);
                chatWindow.setStyle("-fx-background-color: "+newValue);
            }
        });
        
        
        ObservableList fontSizeItems = FXCollections.observableArrayList("1","2",
                "3","4","5","6");
        ComboBox fontSize = new ComboBox(fontSizeItems);
        fontSize.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue observable, String oldValue, String newValue) {
                System.out.println("New Background selected : "+newValue);
                
                double size  = Double.parseDouble(defaultSize)+Double.parseDouble(newValue);
                
                ChatWindow.this.currentFontSize = String.valueOf(size);
                message.setFont(new Font(size));
                System.out.println(currentFontSize);
            }
        });
        fontSize.setPromptText("Size");
        
        ObservableList fontColorItems = FXCollections.observableArrayList("BLACK",
                "SILVER", "GRAY", "PINK", "GOLD", "BLUE");
        ComboBox fontColor = new ComboBox(fontColorItems);
        fontColor.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue observable, String oldValue, String newValue) {
                System.out.println("New Background selected : "+newValue);
                currentFontColor = newValue;
                message.setStyle("-fx-text-fill: " + currentFontColor + ";");
            }
        });
        fontColor.setPromptText("Color");
        
        setting.getChildren().add(bgColor);
        setting.getChildren().add(fontSize);
        setting.getChildren().add(fontColor);
        
       
        
        HBox msgBtn = new HBox();
        Button send = new Button("Send");
        send.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(message.getText().isEmpty()==false)
                {
                    Text text = new Text(message.getText()+"\n\n");
                    messenger.sendMessage(text, ChatWindow.this.getName(), currentFontSize, getColor()); 
                    message.clear();
                }
            }
        });
        
        Button clear = new Button("Clear");
        clear.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                message.clear();
            }
        });
        msgBtn.getChildren().add(send);
        msgBtn.getChildren().add(clear);
        
        chatArea.getChildren().add(msgHistorySP);
        chatArea.getChildren().add(setting);
        chatArea.getChildren().add(message);
        chatArea.getChildren().add(msgBtn);
//        chatArea.setStyle("-fx-background-color: green");
        
    }
    
    private double getWidth() {
        return screenBounds.getWidth()/2;
    }
    
    private double getHeight() {
        return screenBounds.getHeight()-50;
    }
    
}
