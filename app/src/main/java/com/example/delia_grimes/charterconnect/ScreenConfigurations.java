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
    public ScreenConfigurations(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        resourceMainButtons = new Button[7];
    }


    //Putting buttons into the array and setting their width and height
    public void setButton0(Button button0){
        resourceMainButtons[0] = button0;
        fixButtons(0);  //Calls the fixButtons method which resets the width and height of this button
    }

    public void setButton1(Button button1){
        resourceMainButtons[1] = button1;
//        resourceMainButtons[1].setMinHeight(200);
        fixButtons(1);
    }

    public void setButton2(Button button2){
        resourceMainButtons[2] = button2;
        fixButtons(2);
    }

    public void setButton3(Button button3){
        resourceMainButtons[3] = button3;
        fixButtons(3);
    }

    public void setButton4(Button button4){
        resourceMainButtons[4] = button4;
        fixButtons(4);
    }

    public void setButton5(Button button5){
        resourceMainButtons[5] = button5;
        fixButtons(5);
    }

    public void setButton6(Button button6){
        resourceMainButtons[6] = button6;
        fixButtons(6);
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

