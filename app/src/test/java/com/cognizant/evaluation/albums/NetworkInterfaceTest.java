package com.cognizant.evaluation.albums;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.cognizant.evaluation.albums.di.NetworkInterface;
import com.cognizant.evaluation.albums.models.Albums;
import com.cognizant.evaluation.albums.utils.LiveDataCallAdapterFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.List;

import okhttp3.mockwebserver.RecordedRequest;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.cognizant.evaluation.albums.LiveDataTestUtil.getValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class NetworkInterfaceTest extends BaseTest {


    public NetworkInterfaceTest() {
        super();
    }

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();
    private NetworkInterface cloudService;


    @Before
    public void createdService() throws IOException {
        cloudService = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .client(getHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(NetworkInterface.class);
    }


    @Test
    public void testAlbums() throws IOException, InterruptedException {
        enqueueResponse("response.json");
        List<Albums>  result = getValue(cloudService.getAlbums()).body;
        RecordedRequest request = mockWebServer.takeRequest();
        assertThat(request.getPath(), is("/albums"));
        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertTrue(result.get(0).getTitle().equals("quidem molestiae enim"));

    }

    @After
    public void stopService() throws IOException {
        mockWebServer.shutdown();
    }


}
