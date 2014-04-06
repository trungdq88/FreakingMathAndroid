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
    private static int rangeTailInc = 4;
    private static int rangeHeadInc = 1;
    private static int maximumCurrentHeadRange = 0;
    private static int maximumCurrentTailRange = 3;
    private static int levelInc = 3;
    private static int maximumHeadRange = 100;
    private static int maximumTailRange = 20;

    public static void resetSetting() {
        count = 0;
        turn = 0;
        rangeTailInc = 4;
        rangeHeadInc = 1;
        maximumCurrentHeadRange = 0;
        maximumCurrentTailRange = 3;
        levelInc = 3;
        maximumHeadRange = 100;
        maximumTailRange = 20;
    }

    public static int randomNumber(int num) {
        if (count++ > 8) {
            r = new Random();
        }
        return r.nextInt(num);
    }

    public static GameObject randomGame() {
        turn++;
        if (turn % levelInc == 0) {
            maximumCurrentTailRange += rangeTailInc;
            maximumCurrentHeadRange += rangeHeadInc;
            if (maximumCurrentTailRange > maximumTailRange) maximumCurrentTailRange = maximumTailRange;
            if (maximumCurrentHeadRange > maximumHeadRange) maximumCurrentHeadRange = maximumHeadRange;
        }

//        if (turn == firstLevel) {
//            maximumCurrentTailRange = Math.min(maximumCurrentTailRange + rangeTailInc, maximumHeadRange);
//        } else if (turn - levelInc == firstLevel) {
//            maximumCurrentTailRange = Math.min(maximumCurrentTailRange + rangeTailInc, maximumHeadRange);
//            turn = firstLevel;
//        }

        // random number and assign to field
        int firstNum =  + Helper.randomNumber(maximumCurrentTailRange) + 1;
        int secondNum = Helper.randomNumber(maximumCurrentTailRange) + 1;

        // random head
        if (maximumCurrentTailRange > 15) {
            if (firstNum != 1) firstNum += Helper.randomNumber(maximumCurrentHeadRange);
            if (secondNum != 1) secondNum += Helper.randomNumber(maximumCurrentHeadRange);
        }

        // real result
        int res = firstNum + secondNum;
        // should generate true answer or wrong answer
        boolean isTrue = randomBoolean();
        // should add or minus difference
        boolean isPlus = randomBoolean();
        if (!isTrue) {
            // prevent usr just guess unit
            boolean isGenerateSameUnit = Helper.randomBoolean();
            if (isGenerateSameUnit && res > 10) {
                res = isPlus ? res + 10 : res - 10;
            } else {
                // difference when generate wrong
                int diff = Helper.randomNumber(5);
                // prevent change nothing
                if (diff == 0) diff++;
                if (res - diff <= 0) res = res + diff;
                else {
                    res = isPlus ? res + diff : res - diff;
                }
            }
        }

        // last random. random operand
        boolean isPlusOperate = randomBoolean();
        if (!isPlusOperate) {
            int tmp = firstNum;
            firstNum = res;
            res = tmp;
        }
        return new GameObject(firstNum, secondNum, res, isTrue, isPlusOperate);
    }

    public static boolean randomBoolean() {
        return Helper.randomNumber(2) == 0;
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
