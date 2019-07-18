package megamindlons.app.megamind.Retrofit;

import megamindlons.app.megamind.response.BankDataRersponse;
import megamindlons.app.megamind.response.FilterCatResponse;
import megamindlons.app.megamind.response.OTPResponse;
import megamindlons.app.megamind.response.OfferResponse;
import megamindlons.app.megamind.response.ProfileUploadResponse;
import megamindlons.app.megamind.response.ServicesResponse;
import megamindlons.app.megamind.response.UserDataResponse;
import megamindlons.app.megamind.response.VideosResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface APIService {


    @FormUrlEncoded
    @POST("checkMobileExist")
    Call<MSG> checkMobileExist(@Field("mobile") String mobile);

    @FormUrlEncoded
    @POST("login")
    Call<JSONResponse> login(@Field("mobile") String mobile,
                             @Field("password") String password);

    @FormUrlEncoded
    @POST("registration")
    Call<MSG> registration(@Field("fname") String fname, @Field("mobile") String mobile, @Field("email") String email, @Field("occupation") String occupation,
                           @Field("pancard_no") String pancard_no, @Field("aadharcard_no") String aadharcard_no, @Field("dob") String dob,
                           @Field("state_id") String state_id, @Field("city_id") String city_id, @Field("gender") String gender, @Field("password") String password,
                           @Field("parent_agent_id") String parent_agent_id);

    @FormUrlEncoded
    @POST("postNewUserLead")
    Call<MSG> postNewUserLead(@Field("user_id") String user_id, @Field("gender") String gender, @Field("state_id") String state_id, @Field("city_id") String city_id, @Field("service_id") String service_id,
                              @Field("name") String name, @Field("age") String age, @Field("user_comment") String user_comment, @Field("email") String email, @Field("cust_occupation") String cust_occupation,
                              @Field("loan_amount") String loan_amount, @Field("mobile") String mobile);

    @GET("userAllLeads")
    Call<JSONResponse> userAllLeads(@Query("user_id") String user_id);


    @Multipart
    @POST("editUserProfilePic")
    Call<ProfileUploadResponse> postImage(@Part MultipartBody.Part image, @Part("user_id") RequestBody user_id);


    @Multipart
    @POST("editUserDocs")
    Call<OfferResponse> uploadDoc(@Part MultipartBody.Part pan_img, @Part("pan_no") RequestBody pan_no,
                                  @Part MultipartBody.Part aadhar_img, @Part("aadhar_no") RequestBody aadhar_no,
                                  @Part MultipartBody.Part voter_img, @Part("voter_no") RequestBody voter_no,
                                  @Part("user_id") RequestBody user_id);


    @GET("activeYouTubeVideos")
    Call<VideosResponse> callVideos();

    @GET("allOffers")
    Call<OfferResponse> callOffers(@Query("page") String page,@Query("category") String category);


    @GET("allFilters")
    Call<FilterCatResponse> callOffersCat();

    @GET("activeServices")
    Call<ServicesResponse> callServices();

    @GET("userProfileDetails")
    Call<UserDataResponse> callUserData(@Query("user_id") String user_id);

    @GET("sendOTP_Forgot")
    Call<OTPResponse> callOTP(@Query("mobile") String mobile);

    @GET("sendOTP_register")
    Call<OTPResponse> callOTPFOrRegister(@Query("mobile") String mobile);


    @FormUrlEncoded
    @POST("resetPassword")
    Call<OTPResponse> callresetPassword(@Field("user_id") String user_id,@Field("new_password") String new_password);

    @FormUrlEncoded
    @POST("add_bank")
    Call<MSG> postBankData(@Field("id") String id, @Field("bank_name") String bank_name,
                           @Field("ac_holder") String ac_holder, @Field("ac_number") String ac_number,
                           @Field("branch") String branch, @Field("ifsc") String ifsc,
                           @Field("paytm_no") String paytm_no, @Field("user_id") String user_id,
                           @Field("user_type") String user_type);


    @GET("userBankDetails")
    Call<BankDataRersponse> callGetBankData(@Query("user_id") String user_id);


}
