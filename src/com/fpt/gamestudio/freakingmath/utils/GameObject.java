package com.fpt.gamestudio.freakingmath.utils;

/**
 * Created by ThaoHQSE60963 on 4/2/14.
 */
public class GameObject {

    public int first;
    public int second;
    public int res;
    public boolean isPlusOperate;        // 0 : plus. 1 : minus
    public boolean isTrue;

    public GameObject(int first, int second, int res, boolean isTrue, boolean isPlusOperate) {
        this.first = first;
        this.second = second;
        this.res = res;
        this.isTrue = isTrue;
        this.isPlusOperate = isPlusOperate;
    }
}
