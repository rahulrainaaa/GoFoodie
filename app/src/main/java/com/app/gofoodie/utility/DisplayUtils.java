package com.app.gofoodie.utility;


import android.app.Activity;
import android.graphics.Point;
import android.view.Display;

public class DisplayUtils {
    private static final DisplayUtils ourInstance = new DisplayUtils();

    public static DisplayUtils getInstance() {
        return ourInstance;
    }

    private DisplayUtils() {
    }

    public Point getResolution(Activity activity) {

        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }
}
