package com.app.gofoodie.utility;

        import android.app.Activity;
        import android.content.Context;

        import com.app.gofoodie.global.data.GlobalData;
        import com.app.gofoodie.model.customer.Customer;
        import com.app.gofoodie.handler.modelHandler.ModelParser;
        import com.app.gofoodie.model.login.Login;

        import org.json.JSONObject;

/**
 * @class SessionUtils
 * @desc Singleton Class to handle the session, get session, save session, update session, load session amd remove session.
 */
public class SessionUtils {

    public static final String TAG = "SessionUtils";

    private static final SessionUtils ourInstance = new SessionUtils();

    public static SessionUtils getInstance() {
        return ourInstance;
    }

    private SessionUtils() {

    }

    /**
     * @return boolean true = session exist and false = session not exist.
     * @method isSessionExist
     * @desc Method to check if there is any existing session or not.
     */
    public boolean isSessionExist() {

        return GlobalData.isSession;
    }

    /**
     * @param activity
     * @method removeSession
     * @desc Method to remover the existing session (Login and user profile details).
     */
    public void removeSession(Activity activity) {

        CacheUtils.getInstance().getPref(activity, CacheUtils.PREF_NAME.PREF_CUSTOMER_PROFILE).edit().remove(CacheUtils.PREF_KEY).commit();
        CacheUtils.getInstance().getPref(activity, CacheUtils.PREF_NAME.PREF_LOGIN).edit().remove(CacheUtils.PREF_KEY).commit();
        GlobalData.login = null;
        GlobalData.customer = null;
        GlobalData.isSession = false;
    }

    /**
     * @param context
     * @param login
     * @param profile
     * @return int 0 = failed to save, 1 = session saved successfully, 2 = session saved and override existing session.
     * @method saveSession
     * @desc Method to save given parameters as the session for the application.
     */
    public int saveSession(Context context, JSONObject login, JSONObject profile) {

        CacheUtils.getInstance().getPref(context, CacheUtils.PREF_NAME.PREF_CUSTOMER_PROFILE).edit().putString(CacheUtils.PREF_KEY, profile.toString()).commit();
        CacheUtils.getInstance().getPref(context, CacheUtils.PREF_NAME.PREF_LOGIN).edit().putString(CacheUtils.PREF_KEY, login.toString()).commit();
        return 1;
    }

    /**
     * @param activity
     * @method loadSession
     * @desc Method to load the session from Preference to the globally accessible model class.
     */
    public void loadSession(Activity activity) {

        String strProfile = CacheUtils.getInstance().getPref(activity, CacheUtils.PREF_NAME.PREF_CUSTOMER_PROFILE).getString(CacheUtils.PREF_KEY, "");
        String strLogin = CacheUtils.getInstance().getPref(activity, CacheUtils.PREF_NAME.PREF_LOGIN).getString(CacheUtils.PREF_KEY, "");

        if (strLogin.isEmpty() || strProfile.isEmpty()) {
            GlobalData.login = null;
            GlobalData.customer = null;
            GlobalData.isSession = false;
            return;
        }

        ModelParser modelParser = new ModelParser();
        GlobalData.customer = (Customer) modelParser.getModel(strProfile, Customer.class, null);
        GlobalData.login = (Login) modelParser.getModel(strLogin, Login.class, null);
        GlobalData.isSession = true;

    }

}
