package com.hh.person.animation.inter;

/**
 * Created by hjz on 2017/2/28.
 */

public interface IOnAddDelListener {
    enum FailType {
        COUNT_MAX, COUNT_MIN
    }

//    void onAddSuccess(int count);

    void onAddSuccess(int count,float rawX,float rawY);

    void onAddFailed(int count, FailType failType);

    void onDelSuccess(int count);

    void onDelFaild(int count, FailType failType);
}
