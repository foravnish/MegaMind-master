package megamindlons.app.megamind.response;

import java.util.List;

public class BankDataRersponse {


    /**
     * status : success
     * message : [{"id":"9","bank_name":"ZJSJSBBSBHEBBDBBS","ac_holder":"BSHSVEHEBE","ac_number":"9434946191911624","branch":"HSGSGSVSSY","ifsc":"HEHEVEsh","paytm_no":"4364949494649494","user_id":"7","user_type":"AGENT","status":"1","create_date":"2019-06-24 16:06:41"}]
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
         * id : 9
         * bank_name : ZJSJSBBSBHEBBDBBS
         * ac_holder : BSHSVEHEBE
         * ac_number : 9434946191911624
         * branch : HSGSGSVSSY
         * ifsc : HEHEVEsh
         * paytm_no : 4364949494649494
         * user_id : 7
         * user_type : AGENT
         * status : 1
         * create_date : 2019-06-24 16:06:41
         */

        private String id;
        private String bank_name;
        private String ac_holder;
        private String ac_number;
        private String branch;
        private String ifsc;
        private String paytm_no;
        private String user_id;
        private String user_type;
        private String status;
        private String create_date;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getAc_holder() {
            return ac_holder;
        }

        public void setAc_holder(String ac_holder) {
            this.ac_holder = ac_holder;
        }

        public String getAc_number() {
            return ac_number;
        }

        public void setAc_number(String ac_number) {
            this.ac_number = ac_number;
        }

        public String getBranch() {
            return branch;
        }

        public void setBranch(String branch) {
            this.branch = branch;
        }

        public String getIfsc() {
            return ifsc;
        }

        public void setIfsc(String ifsc) {
            this.ifsc = ifsc;
        }

        public String getPaytm_no() {
            return paytm_no;
        }

        public void setPaytm_no(String paytm_no) {
            this.paytm_no = paytm_no;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }
    }
}
