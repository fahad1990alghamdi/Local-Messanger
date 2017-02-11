/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LocalMessenger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class Messenger {
    
    
    private Stage primaryStage;
    private ChatWindow user1;
    private ChatWindow user2;
    private ArrayList<Message> msgList;
    
    public Messenger(Stage primary) {
        primaryStage = primary;
        msgList = new ArrayList<Message>();
    }
    public void load() {
        
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        
        user1 = new ChatWindow(new Stage(), screenBounds, true, Messenger.this);
        user1.setPicture("user1.JPG");
        user1.setName("Tom");
        user1.loadChatWindow(screenBounds.getMinX(),screenBounds.getMinY());
        
        user2 = new ChatWindow(new Stage(),screenBounds, false, Messenger.this);
        user2.setPicture("user2.jpg");
        user2.setName("Jennifer");
        user2.loadChatWindow(screenBounds.getMaxX(),screenBounds.getMinY());
        
        user1.setConnectedUser(user2);
        user2.setConnectedUser(user1);
    }
    public void sendMessage(Text message, String userName, String fontSize, Color fontcolor) {
        
        Message msg = new Message();
        msg.setMsg(message.getText());
        msg.setMsgTime(getCurrentTime());
        msg.setUserName(userName);
        msg.setFontSize(Double.parseDouble(fontSize));
        msg.setFontColor(fontcolor);
        msgList.add(msg);
        
        System.out.println("Called by "+userName+" !!!!"+ message.getFont()+","+message.getFill());
        
        user1.displayMessage(msgList);
        user2.displayMessage(msgList);
    }
    public String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
        String time = formatTime.format(date);
        return time;        
   }
}