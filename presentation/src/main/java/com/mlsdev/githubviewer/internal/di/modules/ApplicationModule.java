package com.mlsdev.githubviewer.internal.di.modules;

import android.content.Context;
import android.os.Handler;

import com.mlsdev.githubviewer.App;
import com.mlsdev.githubviewer.data.executor.JobExecutor;
import com.mlsdev.githubviewer.domain.executor.PostExecutionThread;
import com.mlsdev.githubviewer.domain.executor.ThreadExecutor;
import com.mlsdev.githubviewer.internal.di.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by roma on 22.05.15.
 */
@Module
public class ApplicationModule {

    private final App application;

    public ApplicationModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context providesApplicationContext() {
        return this.application.getApplicationContext();
    }

    @Provides
    @Singleton
    Handler providesApplicationHandler() {
        return this.application.getHandler();
    }

    @Provides
    @Singleton
    ThreadExecutor providesThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providesPostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

}
