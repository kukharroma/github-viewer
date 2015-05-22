package com.mlsdev.githubviewer.data.repository;

import android.content.Context;

import com.mlsdev.githubviewer.data.cache.provider.user.ProjectCacheImpl;
import com.mlsdev.githubviewer.data.entity.ProjectEntity;
import com.mlsdev.githubviewer.data.entity.mapper.ProjectMapper;
import com.mlsdev.githubviewer.data.repository.datastore.project.ProjectDataStore;
import com.mlsdev.githubviewer.data.repository.datastore.project.ProjectDataStoreFactory;
import com.mlsdev.githubviewer.domain.model.Project;
import com.mlsdev.githubviewer.domain.repository.ProjectRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by roma on 20.05.15.
 */
public class ProjectRepositoryImpl implements ProjectRepository {

    private final Context context;
    private final ProjectDataStoreFactory projectDataStoreFactory;
    private final ProjectMapper projectMapper;
    private final ProjectCacheImpl projectCache;

    @Inject
    public ProjectRepositoryImpl(Context context, ProjectDataStoreFactory projectDataStoreFactory, ProjectCacheImpl projectCache, ProjectMapper projectMapper) {
        this.context = context;
        this.projectDataStoreFactory = projectDataStoreFactory;
        this.projectCache = projectCache;
        this.projectMapper = projectMapper;
    }

    @Override
    public void repositoriesGet(String username, final ProjectCallback callback) {
        ProjectDataStore projectDataStore = this.projectDataStoreFactory.createApiDataStore();
        ProjectDataStore.ProjectsCallback projectsCallback = new ProjectDataStore.ProjectsCallback() {
            @Override
            public void onSuccessProjects(List<ProjectEntity> projectsEntities) {
                ProjectRepositoryImpl.this.projectCache.put(projectsEntities);
                List<Project> projects = projectMapper.transform(projectsEntities);
                callback.onSuccessProjects(projects);
            }

            @Override
            public void onFailProjects(String message) {
                callback.onFailProjects(message);
            }
        };
        projectDataStore.repositoriesGet(username, projectsCallback);
    }

    @Override
    public List<Project> repositoriesGet() {
        return projectMapper.transform(projectCache.get());
    }

    @Override
    public void clear() {
        projectCache.clear();
    }
}
