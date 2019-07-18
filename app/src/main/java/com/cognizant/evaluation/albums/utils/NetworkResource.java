package com.cognizant.evaluation.albums.utils;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import java.util.Objects;

public abstract class NetworkResource<ResultType, RequestType> {
    private final AppExecutorsProviders appExecutorsProviders;
    private final MediatorLiveData<Resource<ResultType>> resourceMediatorLiveData = new MediatorLiveData<>();
    @NonNull
    @MainThread
    protected abstract LiveData<ResultType> loadFromCache();
    @NonNull
    @MainThread
    protected abstract LiveData<NetworkResponse<RequestType>> refreshFromCloud();
    public LiveData<Resource<ResultType>> converToLiveData() {
        return resourceMediatorLiveData;
    }
    @WorkerThread
    protected RequestType processResponse(NetworkResponse<RequestType> response) {
        return response.body;
    }
    @WorkerThread
    protected abstract void cacheToLocal(@NonNull RequestType item);
    @MainThread
    protected abstract boolean isDataAvailable(@Nullable ResultType data);
    protected abstract void onRefreshFailed() ;



    @MainThread
    public NetworkResource(AppExecutorsProviders appExecutorsProviders) {
        this.appExecutorsProviders = appExecutorsProviders;
        resourceMediatorLiveData.setValue(Resource.loading(null));
        LiveData<ResultType> dbSource = loadFromCache();
        resourceMediatorLiveData.addSource(dbSource, data -> {
            resourceMediatorLiveData.removeSource(dbSource);
            if (isDataAvailable(data)) {
                downloadFromCloud(dbSource);
            } else {
                resourceMediatorLiveData.addSource(dbSource, newData -> setValue(Resource.success(newData)));
            }
        });
    }

    private void downloadFromCloud(final LiveData<ResultType> dbSource) {
        LiveData<NetworkResponse<RequestType>> apiResponse = refreshFromCloud();
        resourceMediatorLiveData.addSource(dbSource, newData -> setValue(Resource.loading(newData)));
        resourceMediatorLiveData.addSource(apiResponse, response -> {
            resourceMediatorLiveData.removeSource(apiResponse);
            resourceMediatorLiveData.removeSource(dbSource);
            if (response.isSuccessful()) {
                appExecutorsProviders.diskIO().execute(() -> {
                    cacheToLocal(processResponse(response));
                    appExecutorsProviders.mainThread().execute(() ->
                            resourceMediatorLiveData.addSource(loadFromCache(),
                                    newData -> setValue(Resource.success(newData)))
                    );
                });
            } else {
                onRefreshFailed();
                resourceMediatorLiveData.addSource(dbSource,
                        newData -> setValue(Resource.error(response.errorMessage, newData)));
            }
        });
    }

    @MainThread
    private void setValue(Resource<ResultType> newValue) {
        if (!Objects.equals(resourceMediatorLiveData.getValue(), newValue)) {
            resourceMediatorLiveData.setValue(newValue);
        }
    }




}
