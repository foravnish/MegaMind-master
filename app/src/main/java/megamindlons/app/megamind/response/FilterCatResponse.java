package megamindlons.app.megamind.response;

import java.util.List;

public class FilterCatResponse {


    /**
     * status : success
     * message : [{"category":"Air Tickets"},{"category":"Innerwear & Swimwear"},{"category":"Apparel & Clothing"},{"category":" Click Here"},{"category":"Health"},{"category":"Car & Taxi Rental"},{"category":"Jewellery & Coins"},{"category":"Gifts & Flowers"},{"category":"Hotels"}]
     */

    private String status;
    private List<MessageBean> message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MessageBean> getMessage() {
        return message;
    }

    public void setMessage(List<MessageBean> message) {
        this.message = message;
    }

    public static class MessageBean {
        /**
         * category : Air Tickets
         */

        private String category;

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }
    }
}
