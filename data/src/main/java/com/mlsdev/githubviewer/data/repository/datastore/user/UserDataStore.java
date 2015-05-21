package com.mlsdev.githubviewer.data.repository.datastore.user;

import com.android.volley.VolleyError;
import com.mlsdev.githubviewer.data.entity.UserEntity;

/**
 * Created by roma on 20.05.15.
 */
public interface UserDataStore {

    interface UserCallback{
        void onSuccessUser(UserEntity user);
        void onFailUser(VolleyError error);
    }

    void userGet(String username, UserCallback userCallback);

}
