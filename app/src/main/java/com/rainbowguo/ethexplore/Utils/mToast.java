package com.rainbowguo.ethexplore.Utils;

import android.content.Context;
import android.widget.Toast;

public class mToast {
    private static Toast toast;
    public static void create(Context context){
        toast =  new Toast(context);
    }
    public static void showToastRequestFail(){
        if (toast != null){
            toast.setText("Request failed,please try again.");
            toast.show();
        }
    }

    public static void showCopy(){
        if (toast != null){
            toast.setText("Content copied");
            toast.show();
        }
    }
    public static void clear(){
        toast = null;
    }
}
