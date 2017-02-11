/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LocalMessenger;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Message {
    
    private String msg;
    private String userName;
    private String msgTime;
    private Color fontColor;
    private double fontSize;

    public Message() {
        msg = userName = msgTime = "";
        fontSize = 12;
        fontColor = Color.BLACK;
    }

    public Color getFontColor() {
        return fontColor;
    }

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }

    public double getFontSize() {
        return fontSize;
    }

    public void setFontSize(double fontSize) {
        this.fontSize = fontSize;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }
    
    public Text getMessage () {
        Text message = new Text(msgTime+"\n"+userName+": "+msg);    
        message.setFill(fontColor);
        message.setFont(new Font(fontSize));
        
        return message;
    }
}
