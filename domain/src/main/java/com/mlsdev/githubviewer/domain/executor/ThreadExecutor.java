package com.mlsdev.githubviewer.domain.executor;

/**
 * Created by roma on 20/05/15.
 */
public interface ThreadExecutor {
    void execute(final Runnable runnable);
}
