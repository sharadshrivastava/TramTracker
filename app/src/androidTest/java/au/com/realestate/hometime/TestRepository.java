package au.com.realestate.hometime;

import java.io.InputStream;

import au.com.realestate.hometime.service.TramsApi;
import au.com.realestate.hometime.service.TramsRepository;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class TestRepository {

    private static TestRepository sTestRepository;
    private static TramsRepository sTramsRepository;
    private static TramsApi sTramsApi;

    private MockWebServer mMockWebServer;

    private TestRepository(){
        if(sTramsApi==null){
            try{
                mMockWebServer = new MockWebServer();
                mMockWebServer.start();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(mMockWebServer.url("").toString())
                        .addConverterFactory(MoshiConverterFactory.create())
                        .build();
                sTramsApi = retrofit.create(TramsApi.class);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public synchronized static TestRepository getInstance() {
        if (sTestRepository == null) {
            sTestRepository = new TestRepository();
            sTramsRepository = new TramsRepository();
            sTramsRepository.setTramsApi(sTramsApi);
        }
        return sTestRepository;
    }

    public TramsRepository getTramsRepository(){
        return sTramsRepository;
    }

    public void shutDownServer() throws Exception{
        mMockWebServer.shutdown();
    }

    public void setResponse(String fileName) throws Exception{
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName);
        mMockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(new Buffer().readFrom(in)));
    }

    public void setErrorResponse() throws Exception{
        mMockWebServer.enqueue(new MockResponse().setResponseCode(400).setBody("{}"));
    }

}