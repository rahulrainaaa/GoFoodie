package com.app.gofoodie.utility;

import android.content.Context;
import android.os.Vibrator;

/**
 * Utility static class for vibrations handling.
 */
public class VibrationUtil {

    private static final VibrationUtil ourInstance = new VibrationUtil();

    private VibrationUtil() {
    }

    public static VibrationUtil getInstance() {
        return ourInstance;
    }

    public void vibrate(Context context) {

        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (v != null) v.vibrate(50);
    }
}
