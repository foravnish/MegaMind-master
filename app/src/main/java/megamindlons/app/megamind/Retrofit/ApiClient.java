package megamindlons.app.megamind.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import megamindlons.app.megamind.Utils.ApiConstants;
import megamindlons.app.megamind.Utils.Lg;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

//    public static final String BASE_URL = "http://fccollc.com/megamind-1/Api/index.php/main/";
    public static String BASE_URL="https://megamindloans.com/crm/Api/index.php/main/";
//    public static String BASE_URL_OFFER="https://megamindloans.com/crm/api/index.php/main/";
    private static ApiClient mAppRetrofit;
    private static Retrofit retrofit = null;
    private static final long API_TIME_OUT = 60000;
    static APIService apiServices;
    public static Retrofit getClient() {
        if (retrofit==null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();


            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(Lg.ISDEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

            OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();

            OkHttpClient client = okBuilder.connectTimeout(API_TIME_OUT, TimeUnit.MILLISECONDS)
                    .readTimeout(API_TIME_OUT, TimeUnit.MILLISECONDS)
                    .writeTimeout(API_TIME_OUT,TimeUnit.MILLISECONDS).addInterceptor(interceptor).addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request.Builder builder = chain.request().newBuilder();
                            builder.header(ApiConstants.AUTHENTICATION_KEY, ApiConstants.AUTHENTICATION_VALUE);
                            return chain.proceed(builder.build());
                        }
                    }).build();


            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            apiServices = retrofit.create(APIService.class);
        }

        return retrofit;
    }

    public static APIService getApiServices() {
        if (mAppRetrofit == null)
            mAppRetrofit = new ApiClient();
        return apiServices;
    }
}
