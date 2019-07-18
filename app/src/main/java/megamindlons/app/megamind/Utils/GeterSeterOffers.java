package megamindlons.app.megamind.Utils;

public class GeterSeterOffers {

    //private variables
    String id;
    String banner;
    String url;
    String status;
    String date;



    // constructor
    public GeterSeterOffers(String id, String banner, String url, String status, String date){
        this.id = id;
        this.banner = banner;
        this.url = url;
        this.status = status;
        this.date = date;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getBanner() {
        return banner;
    }


    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
