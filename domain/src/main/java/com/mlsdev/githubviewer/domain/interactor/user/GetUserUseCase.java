package com.mlsdev.githubviewer.domain.interactor.user;

import com.mlsdev.githubviewer.domain.interactor.Cancelable;
import com.mlsdev.githubviewer.domain.interactor.Interactor;
import com.mlsdev.githubviewer.domain.model.User;

/**
 * Created by roma on 21.05.15.
 */
public interface GetUserUseCase extends Interactor, Cancelable {

    interface Callback {
        void onSuccessUser(User user);

        void onFailUser(String message);
    }

    void execute(String username, Callback callback);

    User execute();

}
