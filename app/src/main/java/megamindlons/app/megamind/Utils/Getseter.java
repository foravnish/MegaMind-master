package megamindlons.app.megamind.Utils;

import android.app.Dialog;
import android.content.SharedPreferences;

import megamindlons.app.megamind.R;


/**
 * Created by rosen on 11-04-2017.
 */

public class Getseter {


    //private variables
    String id;
    String banner;
    String url;
    String status;




    // constructor
    public Getseter(String id, String banner, String url, String status){
        this.id = id;
        this.banner = banner;
        this.url = url;
        this.status = status;
    }






    public Getseter() {

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
