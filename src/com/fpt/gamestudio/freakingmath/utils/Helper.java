package com.fpt.gamestudio.freakingmath.utils;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;
import com.fpt.gamestudio.freakingmath.config.BaseApplication;

import java.util.Random;

public class Helper {

    private static Random r = new Random();
    private static int count = 0;
    private static int turn = 0;
    private static int rangeInc = 4;
    private static int maximumCurrentRange = 7;
    private static int firstLevel = 8;
    private static int levelInc = 4;
    private static int maximumRange = 100;

    public static void resetSetting() {
        count = 0;
        turn = 0;
        rangeInc = 4;
        maximumCurrentRange = 7;
        firstLevel = 8;
        levelInc = 4;
        maximumRange = 100;
    }

    public static int randomNumber(int num) {
        if (count++ > 8) {
            r = new Random();
        }
        return r.nextInt(num);
    }

    public static GameObject randomGame() {
        turn++;
        if (turn == firstLevel) {
            maximumCurrentRange = Math.min(maximumCurrentRange + rangeInc, maximumRange);
        } else if (turn - levelInc == firstLevel) {
            maximumCurrentRange = Math.min(maximumCurrentRange + rangeInc, maximumRange);
            turn = firstLevel;
        }

        // random number and assign to field
        int firstNum = Helper.randomNumber(maximumCurrentRange) + 1;
        int secondNum = Helper.randomNumber(maximumCurrentRange) + 1;
        int res = firstNum + secondNum;
        boolean isTrue = Helper.randomNumber(2) == 0;
        int diff = Helper.randomNumber(4);
        if (diff == 0) diff++;
        boolean isPlus = Helper.randomNumber(2) - 1 > 0;
        if (!isTrue) {
            if (res - diff <= 0) res = res + diff;
            else {
                res = isPlus ? res + diff : res - diff;
            }
        }
        return new GameObject(firstNum, secondNum, res, isTrue);
    }


    public static Drawable getDrawableFromResourceId(int id) {
        return BaseApplication.getAppContext().getResources().getDrawable(id);
    }

    public static String getRandomNiceColor() {
        String[] colors = "00CC66,00CC99,00CCCC,00CCFF,00FFCC,33CC66,33CC99,33CCCC,33CCFF,3399CC,339999,663399,666699,6666CC,669999,669966,6699CC,6699FF,66CC99,66CCCC,66CCFF,996699,996666,9999FF,9999CC,99CCCC,99CCFF,99CC99,CC9966,CC9999,CC99CC,CC99FF,CCCC99,CCCCFF".split(",");
        return "#" + colors[randInt(0, colors.length - 1)];
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

    public static int getWidth(){
        Context mContext = BaseApplication.getAppContext();
        int width=0;
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.HONEYCOMB){
            Point size = new Point();
            display.getSize(size);
            width = size.x;
        }
        else{
            width = display.getWidth();  // deprecated
        }
        return width;
    }

    public static int getHeight(){
        Context mContext = BaseApplication.getAppContext();
        int height=0;
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.HONEYCOMB){
            Point size = new Point();
            display.getSize(size);
            height = size.y;
        }else{
            height = display.getHeight();  // deprecated
        }
        return height;
    }

}
