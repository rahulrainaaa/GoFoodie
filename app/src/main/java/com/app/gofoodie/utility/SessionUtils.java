package com.app.gofoodie.utility;

/**
 * @class SessionUtils
 * @desc Singleton Class to handle the session, get session, save session, update session, load session amd remove session.
 */
class SessionUtils {
    private static final SessionUtils ourInstance = new SessionUtils();

    static SessionUtils getInstance() {
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

        return false;
    }

    /**
     * @method removeSession
     * @desc Method to remover the existing session (Login and user profile details).
     */
    public void removeSession() {


    }

    /**
     * @return int 0 = failed to save, 1 = session saved successfully, 2 = session saved and override existing session.
     * @method saveSession
     * @desc Method to save given parameters as the session for the application.
     */
    public int saveSession() {

        return 0;
    }

    /**
     * @method loadSession
     * @desc Method to load the session from Preference to the globally accessible model class.
     */
    public void loadSession() {

    }


}
