package com.utils;

import android.graphics.drawable.Drawable;
import com.config.BaseApplication;
import com.view.R;

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
        int firstNum = Helper.randomNumber(9);
        int secondNum = Helper.randomNumber(9);
        int res = firstNum + secondNum;
        boolean isTrue = Helper.randomNumber(2) == 0 ? true : false;
        int diff = Helper.randomNumber(5);
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
}
