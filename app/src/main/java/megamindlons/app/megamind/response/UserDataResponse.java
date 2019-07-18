package megamindlons.app.megamind.response;

import java.io.Serializable;
import java.util.List;

public class UserDataResponse {

    /**
     * status : success
     * message : [{"id":"27","parent_agent_id":"0","branch_id":"0","ass_emp_id":"0","image":"b7facd45e58672a257d5d7a146f40623.jpg","gender":"0","fname":"acnish","username":"","decoded_password":"1234","password":"81dc9bdb52d04dc20036dbd8313ed055","email":"a@gmail.com","mobile":"1212121212","dob":"6-May-2016","state_id":"5","city_id":"137","occupation":"occ","pancard_no":"pap","aadharcard_no":"455","status":"1","comment":"","login_at":"2019-06-16 15:38:06","logout_at":"0000-00-00 00:00:00","created":"2019-06-14 06:45:14","pan_img":"PAN_5d2f489f7cd669a0f8c5b05b081a9bff.jpeg","aadhar_img":"http://megamindloans.com/crm/appImg/userImages/Aadhar_05e765378851bae6a6c31931353de4c8.jpg","voter_no":"vit","voter_img":"http://megamindloans.com/crm/appImg/userImages/VOTER_138d8492b464641ced58f8784b4770b0.jpg","image_thumb":"http://megamindloans.com/crm/appImg/userImages/thumb/b7facd45e58672a257d5d7a146f40623.jpg","pancard_img":"http://megamindloans.com/crm/appImg/userImages/PAN_5d2f489f7cd669a0f8c5b05b081a9bff.jpeg"}]
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

    public static class MessageBean implements Serializable {
        /**
         * id : 27
         * parent_agent_id : 0
         * branch_id : 0
         * ass_emp_id : 0
         * image : b7facd45e58672a257d5d7a146f40623.jpg
         * gender : 0
         * fname : acnish
         * username :
         * decoded_password : 1234
         * password : 81dc9bdb52d04dc20036dbd8313ed055
         * email : a@gmail.com
         * mobile : 1212121212
         * dob : 6-May-2016
         * state_id : 5
         * city_id : 137
         * occupation : occ
         * pancard_no : pap
         * aadharcard_no : 455
         * status : 1
         * comment :
         * login_at : 2019-06-16 15:38:06
         * logout_at : 0000-00-00 00:00:00
         * created : 2019-06-14 06:45:14
         * pan_img : PAN_5d2f489f7cd669a0f8c5b05b081a9bff.jpeg
         * aadhar_img : http://megamindloans.com/crm/appImg/userImages/Aadhar_05e765378851bae6a6c31931353de4c8.jpg
         * voter_no : vit
         * voter_img : http://megamindloans.com/crm/appImg/userImages/VOTER_138d8492b464641ced58f8784b4770b0.jpg
         * image_thumb : http://megamindloans.com/crm/appImg/userImages/thumb/b7facd45e58672a257d5d7a146f40623.jpg
         * pancard_img : http://megamindloans.com/crm/appImg/userImages/PAN_5d2f489f7cd669a0f8c5b05b081a9bff.jpeg
         */

        private String id;
        private String parent_agent_id;
        private String branch_id;
        private String ass_emp_id;
        private String image;
        private String gender;
        private String fname;
        private String username;
        private String decoded_password;
        private String password;
        private String email;
        private String mobile;
        private String dob;
        private String state_id;
        private String city_id;
        private String occupation;
        private String pancard_no;
        private String aadharcard_no;
        private String status;
        private String comment;
        private String login_at;
        private String logout_at;
        private String created;
        private String pan_img;
        private String aadhar_img;
        private String voter_no;
        private String voter_img;
        private String image_thumb;
        private String pancard_img;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getParent_agent_id() {
            return parent_agent_id;
        }

        public void setParent_agent_id(String parent_agent_id) {
            this.parent_agent_id = parent_agent_id;
        }

        public String getBranch_id() {
            return branch_id;
        }

        public void setBranch_id(String branch_id) {
            this.branch_id = branch_id;
        }

        public String getAss_emp_id() {
            return ass_emp_id;
        }

        public void setAss_emp_id(String ass_emp_id) {
            this.ass_emp_id = ass_emp_id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getDecoded_password() {
            return decoded_password;
        }

        public void setDecoded_password(String decoded_password) {
            this.decoded_password = decoded_password;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getState_id() {
            return state_id;
        }

        public void setState_id(String state_id) {
            this.state_id = state_id;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getOccupation() {
            return occupation;
        }

        public void setOccupation(String occupation) {
            this.occupation = occupation;
        }

        public String getPancard_no() {
            return pancard_no;
        }

        public void setPancard_no(String pancard_no) {
            this.pancard_no = pancard_no;
        }

        public String getAadharcard_no() {
            return aadharcard_no;
        }

        public void setAadharcard_no(String aadharcard_no) {
            this.aadharcard_no = aadharcard_no;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getLogin_at() {
            return login_at;
        }

        public void setLogin_at(String login_at) {
            this.login_at = login_at;
        }

        public String getLogout_at() {
            return logout_at;
        }

        public void setLogout_at(String logout_at) {
            this.logout_at = logout_at;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getPan_img() {
            return pan_img;
        }

        public void setPan_img(String pan_img) {
            this.pan_img = pan_img;
        }

        public String getAadhar_img() {
            return aadhar_img;
        }

        public void setAadhar_img(String aadhar_img) {
            this.aadhar_img = aadhar_img;
        }

        public String getVoter_no() {
            return voter_no;
        }

        public void setVoter_no(String voter_no) {
            this.voter_no = voter_no;
        }

        public String getVoter_img() {
            return voter_img;
        }

        public void setVoter_img(String voter_img) {
            this.voter_img = voter_img;
        }

        public String getImage_thumb() {
            return image_thumb;
        }

        public void setImage_thumb(String image_thumb) {
            this.image_thumb = image_thumb;
        }

        public String getPancard_img() {
            return pancard_img;
        }

        public void setPancard_img(String pancard_img) {
            this.pancard_img = pancard_img;
        }
    }
}
