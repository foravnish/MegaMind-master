package megamindlons.app.megamind.Retrofit;

import com.google.gson.annotations.SerializedName;

public class ListingData {
    @SerializedName("id")
    private String id;
    @SerializedName("agent_id")
    private String agent_id;
    @SerializedName("gender")
    private String gender;
    @SerializedName("state_id")
    private String state_id;


    ListingData(String id, String agent_id,String gender,String state_id){

        this.id=id;
        this.agent_id=agent_id;
        this.gender=gender;
        this.state_id=state_id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setAgent_id(String agent_id) {
        this.agent_id = agent_id;
    }

    public String getAgent_id() {
        return agent_id;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public String getState_id() {
        return state_id;
    }
}
