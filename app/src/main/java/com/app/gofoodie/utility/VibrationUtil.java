package com.app.gofoodie.utility;

import android.content.Context;
import android.os.Vibrator;

public class VibrationUtil {
    private static final VibrationUtil ourInstance = new VibrationUtil();

    public static VibrationUtil getInstance() {
        return ourInstance;
    }

    private VibrationUtil() {
    }

    public void vibrate(Context context) {

        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(50);
    }
}
