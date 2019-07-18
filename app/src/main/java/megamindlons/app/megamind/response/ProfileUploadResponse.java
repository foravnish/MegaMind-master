package megamindlons.app.megamind.response;

public class ProfileUploadResponse {


    /**
     * status : success
     * message : Profile pic has been reset successfully!
     */

    private String status;
    private String message;
    private  String image_url;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getImage_url() {
        return image_url;
    }
}
