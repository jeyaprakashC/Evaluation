package com.cognizant.evaluation.albums;

import android.util.Log;

import androidx.annotation.UiThread;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;

import com.cognizant.evaluation.albums.di.LocalCacheModule;
import com.cognizant.evaluation.albums.di.MainViewModelFactory;
import com.cognizant.evaluation.albums.di.NetworkModule;
import com.cognizant.evaluation.albums.home.MainViewModel;
import com.cognizant.evaluation.albums.models.Albums;
import com.cognizant.evaluation.albums.repository.AppLocalCache;
import com.cognizant.evaluation.albums.repository.LocalCacheDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class DataBaseTest extends BaseTest {

//    @Inject
//    MainViewModelFactory viewModeFactory;

    TestAppComponent testAppComponent;

    private AppLocalCache mDatabase;
    private LocalCacheDao dao;


    //private MainViewModel viewModel;

    private FragmentActivity activity;


    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();


    @Before
    public void setup() {
        this.activity = Robolectric.setupActivity(FragmentActivity.class);
        mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                AppLocalCache.class)
                .allowMainThreadQueries()
                .build();
        //configureDagger();

        dao = mDatabase.getDao();

       // this.viewModel = ViewModelProviders.of(this.activity, viewModeFactory).get(MainViewModel.class);

    }

    @After
    public void tearDown() {
        this.stopMockServer();
    }


//    void configureDagger() {
//
//        this.testAppComponent =
//                DaggerTestAppComponent.builder().networkModule(new NetworkModule()).localCacheModule(new LocalCacheModule(activity.getApplication())).build();
//        this.testAppComponent.inject(this);
//    }


    @Test
    public void isDatabaseEmpty() throws InterruptedException {
        List<Albums> albums = LiveDataTestUtil.getValue(dao.getAlbumsasLiveData());
        assertTrue(albums.isEmpty());
    }


    @Test
    public void insertAlbums() throws InterruptedException {
        dao.addAlbums(getTestAlbums());
        assertEquals(5, LiveDataTestUtil.getValue(dao.getAlbumsasLiveData()).size());
    }



    private List<Albums> getTestAlbums() {
        List<Albums> albumss = new ArrayList<Albums>();
        for(int i = 1; i <= 5; i++ ) {
            Albums album = new Albums(i,"title "+i,i);
            albumss.add(album);
        }
        return albumss;

    }



    @Test
    public void checklist()  {

    }



}
