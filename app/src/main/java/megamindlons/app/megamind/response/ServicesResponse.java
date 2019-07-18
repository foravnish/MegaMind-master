package megamindlons.app.megamind.response;

import java.util.List;

public class ServicesResponse {


    /**
     * status : success
     * message : [{"id":"1","image":"https://megamindloans.com/crm/appImg/serviceImages/thumb/4cc6167c27b4e3db1d26c80358b091d3.jpg","title":"Home Loan","description":"<p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.<\/p>","status":"1","created":"2018-07-03 07:10:18"},{"id":"2","image":"https://megamindloans.com/crm/appImg/serviceImages/thumb/d60d9d8c15ba106cbc4abc9b09580b98.jpg","title":"Loan Against Property","description":"<p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.<\/p>\r\n","status":"1","created":"2018-07-03 07:10:18"},{"id":"3","image":"https://megamindloans.com/crm/appImg/serviceImages/thumb/83d1ecbc0d04a25d054f0383c2463a5c.jpg","title":"Personal Loan","description":"<p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.<\/p>\r\n","status":"1","created":"2018-07-03 07:10:18"},{"id":"4","image":"https://megamindloans.com/crm/appImg/serviceImages/thumb/5ba9f328d5c36565ad7acdcb694ae02d.jpg","title":"Business Loan","description":"<p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.<\/p>\r\n","status":"1","created":"2018-07-03 07:10:18"},{"id":"5","image":"https://megamindloans.com/crm/appImg/serviceImages/thumb/52ae67f006a28bf10f4f4c80d88c067e.jpg","title":"Credit Card","description":"<p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.<\/p>\r\n","status":"1","created":"2018-07-03 07:10:18"},{"id":"15","image":"https://megamindloans.com/crm/appImg/serviceImages/thumb/a81b517d35cefb59324d78b2a75791ae.jpg","title":"Industrial Loan","description":"<p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.<\/p>\r\n","status":"1","created":"2018-07-03 07:10:18"},{"id":"16","image":"https://megamindloans.com/crm/appImg/serviceImages/thumb/c6316ed27e406780cbb059887a93a9de.jpg","title":"Machinery Loan","description":"<p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.<\/p>\r\n","status":"1","created":"2018-07-03 07:10:18"}]
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
         * id : 1
         * image : https://megamindloans.com/crm/appImg/serviceImages/thumb/4cc6167c27b4e3db1d26c80358b091d3.jpg
         * title : Home Loan
         * description : <p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.</p>
         * status : 1
         * created : 2018-07-03 07:10:18
         */

        private String id;
        private String image;
        private String title;
        private String description;
        private String status;
        private String created;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }
    }
}
