package com.app.gofoodie.utility;


public class DateUtils {
    private static final DateUtils ourInstance = new DateUtils();

    public static DateUtils getInstance() {
        return ourInstance;
    }

    private DateUtils() {
    }

    public String weekName(int i) {

        switch (i) {

            case 1:

                return "sun";

            case 2:

                return "mon";

            case 3:

                return "tue";

            case 4:

                return "wed";

            case 5:

                return "thu";

            case 6:

                return "fri";

            case 7:

                return "sat";

            default:

                return "";
        }
    }

}
