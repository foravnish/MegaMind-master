package megamindlons.app.megamind.Retrofit;

public class JSONResponse {

    private UserData[] message;
    private String status;

    public UserData[] getData() {
        return message;
    }

    JSONResponse(String status){
        this.status=status;


    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setMessage(UserData[] message) {
        this.message = message;
    }

    public UserData[] getMessage() {
        return message;
    }
}
