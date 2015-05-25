package com.mlsdev.githubviewer;

import android.app.Application;
import android.os.Handler;

import com.mlsdev.githubviewer.data.network.ExtendedVolley;
import com.mlsdev.githubviewer.internal.di.components.ApplicationComponent;
import com.mlsdev.githubviewer.internal.di.components.DaggerApplicationComponent;
import com.mlsdev.githubviewer.internal.di.modules.ApplicationModule;
import com.mlsdev.githubviewer.ui.fragment.impl.ProjectsFragment;
import com.mlsdev.githubviewer.ui.fragment.impl.SearchUserFragment;


/**
 * Created by roma on 21.05.15.
 */
public class App extends Application {

    private ApplicationComponent applicationComponent;
    private Handler handler = new Handler();

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
        ExtendedVolley.init(getApplicationContext());
    }

    private void initDagger(){
        this.applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        this.applicationComponent.inject(this);
    }

    public Handler getHandler() {
        return handler;
    }

    public void inject(SearchUserFragment fragment){
        this.applicationComponent.inject(fragment);
    }

    public void inject(ProjectsFragment fragment){
        this.applicationComponent.inject(fragment);
    }
}
