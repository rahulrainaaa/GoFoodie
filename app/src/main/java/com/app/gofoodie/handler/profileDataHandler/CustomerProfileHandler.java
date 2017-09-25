package com.app.gofoodie.handler.profileDataHandler;

import android.content.Context;

import com.app.gofoodie.global.data.GlobalData;
import com.app.gofoodie.model.base.BaseModel;
import com.app.gofoodie.model.login.Login;
import com.app.gofoodie.utility.SessionUtils;

import java.util.Date;

/**
 * @class CustomerProfileHandler
 * @desc Handler class to handle the customer full profile based on the application login session.
 */
public class CustomerProfileHandler {

    /**
     * Class Private data members.
     */
    private Context mContext = null;

    /**
     * Class private static data member.
     */
    private static Date sPrevTime = null;   // Last data refreshed DateTime.

    /**
     * @param context
     * @constructor CustomerProfileHandler
     * @desc Constructor to init the class data member(s) [object].
     */
    public CustomerProfileHandler(Context context) {

        this.mContext = context;
    }

    /**
     * @return BaseModel object with reference to CustomerFullProfile.
     * @method getFullProfile
     * @desc Method to provide customer full profile detail OR get simply refresh in case if needed (refresh after 2 min).
     */
    public BaseModel getFullProfile() {

        return null;
    }

    /**
     * @return BaseModel reference to customerFullProfile response model.
     * @method refreshProfile
     * @desc Method to fetch customer profile from WEB API.
     */
    public BaseModel refreshProfile() {

        return null;
    }

    /**
     * @return
     * @method removeSession
     * @desc Method to remove {@link com.app.gofoodie.model.login.Login} from cache in cae of invalid session.
     */
    public boolean removeSession() {

        return true;
    }

    /**
     * @return Login reference to {@link Login} object fetched from cache. If not present, return null Login reference.
     * @method checkLoginSession
     * @desc Method to check for the session login from the cache/Preferences.
     */
    public Login checkLoginSession() {

        if (SessionUtils.getInstance().isSessionExist()) {

            return GlobalData.login;
        } else {

            return null;
        }
    }

}
