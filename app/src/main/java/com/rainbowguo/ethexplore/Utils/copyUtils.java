package com.rainbowguo.ethexplore.Utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

public class copyUtils {
     public static void copy(String data, Context context){
         ClipboardManager copy = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
         copy.setPrimaryClip(ClipData.newPlainText("copy",data));
     }
}
