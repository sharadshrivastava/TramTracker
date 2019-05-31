package au.com.realestate.hometime

import au.com.realestate.hometime.service.TramsApi
import au.com.realestate.hometime.service.TramsRepository
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class TestRepository {

    var tramsRepository: TramsRepository
    private var mockWebServer = MockWebServer()
    private var tramsApi: TramsApi

    init{
        mockWebServer.start()
        val retrofit = Retrofit.Builder()
                .baseUrl(mockWebServer.url("").toString())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        tramsApi = retrofit.create(TramsApi::class.java)
        tramsRepository = TramsRepository(tramsApi)
    }

    companion object {
        private var instance = TestRepository()
        fun get(): TestRepository = instance
    }

    fun setResponse(fileName: String) {
        val input = this.javaClass.classLoader?.getResourceAsStream(fileName)
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(Buffer().readFrom(input)))
    }

    fun setErrorResponse() {
        mockWebServer.enqueue(MockResponse().setResponseCode(400).setBody("{}"))
    }
}