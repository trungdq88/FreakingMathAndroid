package com.utils;

import android.graphics.drawable.Drawable;
import com.config.BaseApplication;
import com.view.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Helper {

    private static Random r = new Random();
    private static int count = 0;

    public static int randomNumber(int num) {
        if (count++ > 5) {
            r = new Random();
        }
        return r.nextInt(num);
    }

    public static GameObject randomGame() {
        // random number and assign to field
        int firstNum = Helper.randomNumber(10);
        int secondNum = Helper.randomNumber(10);
        int res = firstNum + secondNum;
        boolean isTrue = Helper.randomNumber(2) == 0 ? true : false;
        int diff = Helper.randomNumber(3);
        if (diff == 0) diff++;
        boolean isPlus = Helper.randomNumber(2) - 1 > 0 ? true : false;
        res = isTrue ? res : (isPlus ? res + diff : res - diff);
        return new GameObject(firstNum, secondNum, res, isTrue);
    }

    public static Drawable getDrawableFromNumber(int num) {
        switch(num) {
            case 0:
                return BaseApplication.getAppContext().getResources().getDrawable(R.drawable.n0);
            case 1:
                return BaseApplication.getAppContext().getResources().getDrawable(R.drawable.n1);
            case 2:
                return BaseApplication.getAppContext().getResources().getDrawable(R.drawable.n2);
            case 3:
                return BaseApplication.getAppContext().getResources().getDrawable(R.drawable.n3);
            case 4:
                return BaseApplication.getAppContext().getResources().getDrawable(R.drawable.n4);
            case 5:
                return BaseApplication.getAppContext().getResources().getDrawable(R.drawable.n5);
            case 6:
                return BaseApplication.getAppContext().getResources().getDrawable(R.drawable.n6);
            case 7:
                return BaseApplication.getAppContext().getResources().getDrawable(R.drawable.n7);
            case 8:
                return BaseApplication.getAppContext().getResources().getDrawable(R.drawable.n8);
            case 9:
                return BaseApplication.getAppContext().getResources().getDrawable(R.drawable.n9);
            default:
                return null;
        }
    }

    public static String getRandomNiceColor() {
        String[] colors = "#000033,#000066,#000099,#0000CC,#0000FF,#003300,#003333,#003366,#003399,#0033CC,#0033FF,#006600,#006633,#006666,#006699,#0066CC,#0066FF,#009900,#009933,#009966,#009999,#0099CC,#0099FF,#00CC00,#00CC33,#00CC66,#00CC99,#00CCCC,#00CCFF,#00FF00,#00FF33,#00FF66,#00FF99,#00FFCC,#00FFFF,#330000,#330033,#330066,#330099,#3300CC,#3300FF,#333300,#333333,#333366,#333399,#3333CC,#3333FF,#336600,#336633,#336666,#336699,#3366CC,#3366FF,#339900,#339933,#339966,#339999,#3399CC,#3399FF,#33CC00,#33CC33,#33CC66,#33CC99,#33CCCC,#33CCFF,#33FF00,#33FF33,#33FF66,#33FF99,#33FFCC,#33FFFF,#660000,#660033,#660066,#660099,#6600CC,#6600FF,#663300,#663333,#663366,#663399,#6633CC,#6633FF,#666600,#666633,#666666,#666699,#6666CC,#6666FF,#669900,#669933,#669966,#669999,#6699CC,#6699FF,#66CC00,#66CC33,#66CC66,#66CC99,#66CCCC,#66CCFF,#66FF00,#66FF33,#66FF66,#66FF99,#66FFCC,#66FFFF,#990000,#990033,#990066,#990099,#9900CC,#9900FF,#993300,#993333,#993366,#993399,#9933CC,#9933FF,#996600,#996633,#996666,#996699,#9966CC,#9966FF,#999900,#999933,#999966,#999999,#9999CC,#9999FF,#99CC00,#99CC33,#99CC66,#99CC99,#99CCCC,#99CCFF,#99FF00,#99FF33,#99FF66,#99FF99,#99FFCC,#99FFFF,#CC0000,#CC0033,#CC0066,#CC0099,#CC00CC,#CC00FF,#CC3300,#CC3333,#CC3366,#CC3399,#CC33CC,#CC33FF,#CC6600,#CC6633,#CC6666,#CC6699,#CC66CC,#CC66FF,#CC9900,#CC9933,#CC9966,#CC9999,#CC99CC,#CC99FF,#CCCC00,#CCCC33,#CCCC66,#CCCC99,#CCCCCC,#CCCCFF,#CCFF00,#CCFF33,#CCFF66,#CCFF99,#CCFFCC,#CCFFFF,#FF0000,#FF0033,#FF0066,#FF0099,#FF00CC,#FF00FF,#FF3300,#FF3333,#FF3366,#FF3399,#FF33CC,#FF33FF,#FF6600,#FF6633,#FF6666,#FF6699,#FF66CC,#FF66FF,#FF9900,#FF9933,#FF9966,#FF9999,#FF99CC,#FF99FF,#FFCC00,#FFCC33,#FFCC66,#FFCC99,#FFCCCC,#FFCCFF,#FFFF00,#FFFF33,#FFFF66,#FFFF99,#FFFFCC,#FFFFFF,#000000,#000033,#000066,#000099,#0000CC,#0000FF,#003300,#003333,#003366,#003399,#0033CC,#0033FF,#006600,#006633,#006666,#006699,#0066CC,#0066FF,#009900,#009933,#009966,#009999,#0099CC,#0099FF,#00CC00,#00CC33,#00CC66,#00CC99,#00CCCC,#00CCFF,#00FF00,#00FF33,#00FF66,#00FF99,#00FFCC,#00FFFF,#000000,#000033,#000066,#000099,#0000CC,#0000FF,#330000,#330033,#330066,#330099,#3300CC,#3300FF,#660000,#660033,#660066,#660099,#6600CC,#6600FF,#990000,#990033,#990066,#990099,#9900CC,#9900FF,#CC0000,#CC0033,#CC0066,#CC0099,#CC00CC,#CC00FF,#FF0000,#FF0033,#FF0066,#FF0099,#FF00CC,#FF00FF,#000000,#003300,#006600,#009900,#00CC00,#00FF00,#330000,#333300,#336600,#339900,#33CC00,#33FF00,#660000,#663300,#666600,#669900,#66CC00,#66FF00,#990000,#993300,#996600,#999900,#99CC00,#99FF00,#CC0000,#CC3300,#CC6600,#CC9900,#CCCC00,#CCFF00,#FF0000,#FF3300,#FF6600,#FF9900,#FFCC00,#FFFF00,#000000,#111111,#222222,#333333,#444444,#555555,#666666,#777777,#888888,#999999,#AAAAAA,#BBBBBB,#CCCCCC,#DDDDDD,#EEEEEE,#FFFFFF,#333333,#333366,#333399,#3333CC,#336633,#336666,#336699,#3366CC,#339933,#339966,#339999,#3399CC,#33CC33,#33CC66,#33CC99,#33CCCC,#663333,#663366,#663399,#6633CC,#666633,#666666,#666699,#6666CC,#669933,#669966,#669999,#6699CC,#66CC33,#66CC66,#66CC99,#66CCCC,#993333,#993366,#993399,#9933CC,#996633,#996666,#996699,#9966CC,#999933,#999966,#999999,#9999CC,#99CC33,#99CC66,#99CC99,#99CCCC,#CC3333,#CC3366,#CC3399,#CC33CC,#CC6633,#CC6666,#CC6699,#CC66CC,#CC9933,#CC9966,#CC9999,#CC99CC,#CCCC33,#CCCC66,#CCCC99,#CCCCCC,#666666,#666699,#6666CC,#6666FF,#669966,#669999,#6699CC,#6699FF,#66CC66,#66CC99,#66CCCC,#66CCFF,#66FF66,#66FF99,#66FFCC,#66FFFF,#996666,#996699,#9966CC,#9966FF,#999966,#999999,#9999CC,#9999FF,#99CC66,#99CC99,#99CCCC,#99CCFF,#99FF66,#99FF99,#99FFCC,#99FFFF,#CC6666,#CC6699,#CC66CC,#CC66FF,#CC9966,#CC9999,#CC99CC,#CC99FF,#CCCC66,#CCCC99,#CCCCCC,#CCCCFF,#CCFF66,#CCFF99,#CCFFCC,#CCFFFF,#FF6666,#FF6699,#FF66CC,#FF66FF,#FF9966,#FF9999,#FF99CC,#FF99FF,#FFCC66,#FFCC99,#FFCCCC,#FFCCFF,#FFFF66,#FFFF99,#FFFFCC".split(",");
        return colors[randInt(0, colors.length - 1)];
    }

    /**
     * Returns a pseudo-random number between min and max, inclusive.
     * The difference between min and max can be at most
     * <code>Integer.MAX_VALUE - 1</code>.
     *
     * @param min Minimum value
     * @param max Maximum value.  Must be greater than min.
     * @return Integer between min and max, inclusive.
     * @see java.util.Random#nextInt(int)
     */
    public static int randInt(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
