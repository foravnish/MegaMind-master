package megamindlons.app.megamind.Retrofit;

import com.google.gson.annotations.SerializedName;

public class UserData {

    @SerializedName("id")
    private String id;
    @SerializedName("image")
    private  String image;
    @SerializedName("fname")
    private String name;
    @SerializedName("mobile")
    private String mobile;
    @SerializedName("email")
    private String email;
    @SerializedName("dob")
    private String dob;
    @SerializedName("referer_code")
    private String referer_code;
    @SerializedName("image_thumb")
    private String image_thumb;


    UserData(String id, String image,String name,String mobile,String email,String referer_code,String dob, String image_thumb){

        this.id=id;
        this.image=image;
        this.name=name;
        this.mobile=mobile;
        this.email=email;
        this.referer_code=referer_code;
        this.dob=dob;
        this.image_thumb=image_thumb;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public String getImage_thumb() {
        return image_thumb;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setReferer_code(String referer_code) {
        this.referer_code = referer_code;
    }

    public String getReferer_code() {
        return referer_code;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDob() {
        return dob;
    }
}
