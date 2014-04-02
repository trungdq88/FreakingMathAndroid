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
        int firstNum = Helper.randomNumber(9);
        int secondNum = Helper.randomNumber(9);
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
}
