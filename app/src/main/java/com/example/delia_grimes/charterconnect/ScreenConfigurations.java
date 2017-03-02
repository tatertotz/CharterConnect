package com.example.delia_grimes.charterconnect;

import android.widget.Button;

/**
 * Created by Student on 2/16/17.
 */

public class ScreenConfigurations {

    public int screenWidth;
    public int screenHeight;
    public Button[] resourceMainButtons; //Initializes an array of buttons


    //A constructor to make a screen configuration with a given width and height.
    public ScreenConfigurations(int screenWidth, int screenHeight, int numberOfButtons) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        resourceMainButtons = new Button[numberOfButtons];
    }


    //Putting buttons into the array and setting their width and height
    public void setButton(Button button, int buttonNumber){
        resourceMainButtons[buttonNumber] = button;
        fixButtons(buttonNumber);  //Calls the fixButtons method which resets the width and height of this button
    }


    private void fixButtons(int button){
        int buttonHeight = (int)(screenHeight*0.25);    //Making a variable for the height of the buttons
        int buttonWidth = (int)(screenWidth*0.25); //Making a variable for the width of the buttons
        resourceMainButtons[button].setMinWidth(Math.min(buttonHeight, buttonWidth));
        resourceMainButtons[button].setMinHeight(Math.min(buttonHeight, buttonWidth));
    }


    public Button getButton(int whichButton){
        return resourceMainButtons[whichButton];
    }

//    public int getButton(int whichButton){
//        return resourceMainButtons[1].getMinHeight();
//    }

//    public int buttonX;
//    public int buttonY;
//    public int buttonHeight;
//    public int buttonWidth;


//    Object[] buttonArray = new Object[10];
//    buttonArray[0]


//    public class ResourceMainPage extends ScreenConfigurations {
//
//    }
}

