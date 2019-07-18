package megamindlons.app.megamind.Retrofit;

import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import megamindlons.app.megamind.Utils.Lg;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AppRetrofit {
    private static AppRetrofit mAppRetrofit;
    //60 seconds timeout
    private static final long API_TIME_OUT = 60000;
    OkHttpClient client = null;
    static APIService apiServices;
    static APIService apiServicesOffer;

    public AppRetrofit() {
        //for logging
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(Lg.ISDEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();


        client = okBuilder.connectTimeout(API_TIME_OUT, TimeUnit.MILLISECONDS)
                .readTimeout(API_TIME_OUT, TimeUnit.MILLISECONDS)
                .writeTimeout(API_TIME_OUT, TimeUnit.MILLISECONDS).addInterceptor(interceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {

                        Request.Builder builder = chain.request().newBuilder();
                        builder.header(ApiConstants.AUTHENTICATION_KEY, ApiConstants.AUTHENTICATION_VALUE);
                        return chain.proceed(builder.build());
                    }
                }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .client(client)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                        .create()))
                .build();
        apiServices = retrofit.create(APIService.class);

        Retrofit retrofitOffer = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .client(client)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                        .create()))
                .build();
        apiServicesOffer = retrofitOffer.create(APIService.class);

    }

    /**
     * Null handler in retrofit instance
     */
    private static class NullOnEmptyConverterFactory extends Converter.Factory {

        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            final Converter<ResponseBody, ?> delegate = retrofit.nextResponseBodyConverter(this, type, annotations);
            return  new Converter<ResponseBody, Object>() {
                @Override
                public Object convert(ResponseBody value) throws IOException {
                    if (value.contentLength() == 0) return null;
                    return delegate.convert(value);
                }
            };

//            return (Converter<ResponseBody, Object>) body -> {
//                if (body.contentLength() == 0) return null;
//                return delegate.convert(body);
//            };
        }
    }

    public static APIService getApiServices() {
        if (mAppRetrofit == null)
            mAppRetrofit = new AppRetrofit();
        return apiServices;
    }

    public static APIService getApiServicesOffers() {
        if (mAppRetrofit == null)
            mAppRetrofit = new AppRetrofit();
        return apiServicesOffer;
    }

}
