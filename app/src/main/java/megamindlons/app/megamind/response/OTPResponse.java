package megamindlons.app.megamind.response;

public class OTPResponse {

    /**
     * status : success
     * mobile : 8791193678
     * id : 7
     * OTP : 5627
     */

    private String status;
    private String mobile;
    private String id;
    private int OTP;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOTP() {
        return OTP;
    }

    public void setOTP(int OTP) {
        this.OTP = OTP;
    }
}
