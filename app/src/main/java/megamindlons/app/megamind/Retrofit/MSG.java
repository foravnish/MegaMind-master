package megamindlons.app.megamind.Retrofit;

public class MSG {
    private String status;
    private String message;

    public MSG() {
    }

    public MSG(String status, String message) {
        super();
        this.status = status;
        this.message = message;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
