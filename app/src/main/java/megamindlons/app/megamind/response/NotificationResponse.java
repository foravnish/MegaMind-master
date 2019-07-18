package megamindlons.app.megamind.response;

import java.io.Serializable;
import java.util.List;

public class NotificationResponse {

    /**
     * status : success
     * message : Like Comment Listing
     * data : [{"notification_id":"1","sign_up_id":"1","posting_id":"1","like_or_comment":"1","comment":"","created_date":"2019-02-02 01:30:43","from_user":[{"sign_up_id":"1","user_id":"pankaj_1990","email":"pankaj@gmail.com","profile_picture":"image1.jpg","created_date":"2019-02-02 01:23:16"}],"notification":"pankaj_1990 Liked Your Post","posting_detail":[{"postint_id":"1","sign_up_id":"1","posting_image":"http://littleletterslinked.com/api/assets/image/profile/image1.jpg","created_date":"2019-02-02 23:31:08","user_name":"cbuzc","likes":27,"comments":60}]},{"notification_id":"2","sign_up_id":"7","posting_id":"1","like_or_comment":"1","comment":"","created_date":"2019-02-02 01:31:08","from_user":[{"sign_up_id":"7","user_id":"avnish_458","email":"avnish@gmail.com","profile_picture":"image6.jpg","created_date":"2019-02-02 01:23:16"}],"notification":"avnish_458 Liked Your Post","posting_detail":[{"postint_id":"1","sign_up_id":"1","posting_image":"http://littleletterslinked.com/api/assets/image/profile/image1.jpg","created_date":"2019-02-02 23:31:08","user_name":"oar9j","likes":72,"comments":92}]},{"notification_id":"3","sign_up_id":"3","posting_id":"1","like_or_comment":"1","comment":"","created_date":"2019-02-02 01:31:18","from_user":[{"sign_up_id":"3","user_id":"raju_1990","email":"raju@gmail.com","profile_picture":"image2.jpg","created_date":"2019-02-02 01:23:16"}],"notification":"raju_1990 Liked Your Post","posting_detail":[{"postint_id":"1","sign_up_id":"1","posting_image":"http://littleletterslinked.com/api/assets/image/profile/image1.jpg","created_date":"2019-02-02 23:31:08","user_name":"hhmr8","likes":9,"comments":68}]},{"notification_id":"4","sign_up_id":"4","posting_id":"1","like_or_comment":"2","comment":"Hello World","created_date":"2019-02-02 01:31:30","from_user":[{"sign_up_id":"4","user_id":"monu_4587","email":"monu@gmail.com","profile_picture":"image3.jpg","created_date":"2019-02-02 01:23:16"}],"notification":"monu_4587 Commented On Your Post","posting_detail":[{"postint_id":"1","sign_up_id":"1","posting_image":"http://littleletterslinked.com/api/assets/image/profile/image1.jpg","created_date":"2019-02-02 23:31:08","user_name":"db9jo","likes":35,"comments":39}]},{"notification_id":"5","sign_up_id":"1","posting_id":"1","like_or_comment":"2","comment":"Nice","created_date":"2019-02-02 01:31:37","from_user":[{"sign_up_id":"1","user_id":"pankaj_1990","email":"pankaj@gmail.com","profile_picture":"image1.jpg","created_date":"2019-02-02 01:23:16"}],"notification":"pankaj_1990 Commented On Your Post","posting_detail":[{"postint_id":"1","sign_up_id":"1","posting_image":"http://littleletterslinked.com/api/assets/image/profile/image1.jpg","created_date":"2019-02-02 23:31:08","user_name":"i9hxk","likes":70,"comments":98}]},{"notification_id":"6","sign_up_id":"5","posting_id":"1","like_or_comment":"1","comment":"","created_date":"2019-02-02 01:31:48","from_user":[{"sign_up_id":"5","user_id":"ankur_6545","email":"ankur@gmail.com","profile_picture":"image4.jpg","created_date":"2019-02-02 01:23:16"}],"notification":"ankur_6545 Liked Your Post","posting_detail":[{"postint_id":"1","sign_up_id":"1","posting_image":"http://littleletterslinked.com/api/assets/image/profile/image1.jpg","created_date":"2019-02-02 23:31:08","user_name":"tbmya","likes":32,"comments":37}]},{"notification_id":"7","sign_up_id":"6","posting_id":"1","like_or_comment":"1","comment":"","created_date":"2019-02-02 01:32:03","from_user":[{"sign_up_id":"6","user_id":"vijay_458","email":"vijay584@gmail.com","profile_picture":"image5.jpg","created_date":"2019-02-02 01:23:16"}],"notification":"vijay_458 Liked Your Post","posting_detail":[{"postint_id":"1","sign_up_id":"1","posting_image":"http://littleletterslinked.com/api/assets/image/profile/image1.jpg","created_date":"2019-02-02 23:31:08","user_name":"ycdzu","likes":34,"comments":59}]}]
     */

    private String status;
    private String message;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * notification_id : 1
         * sign_up_id : 1
         * posting_id : 1
         * like_or_comment : 1
         * comment :
         * created_date : 2019-02-02 01:30:43
         * from_user : [{"sign_up_id":"1","user_id":"pankaj_1990","email":"pankaj@gmail.com","profile_picture":"image1.jpg","created_date":"2019-02-02 01:23:16"}]
         * notification : pankaj_1990 Liked Your Post
         * posting_detail : [{"postint_id":"1","sign_up_id":"1","posting_image":"http://littleletterslinked.com/api/assets/image/profile/image1.jpg","created_date":"2019-02-02 23:31:08","user_name":"cbuzc","likes":27,"comments":60}]
         */

        private String notification_id;
        private String sign_up_id;
        private String posting_id;
        private String like_or_comment;
        private String comment;
        private String created_date;
        private String notification;
        private List<FromUserBean> from_user;
        private List<PostingDetailBean> posting_detail;

        public String getNotification_id() {
            return notification_id;
        }

        public void setNotification_id(String notification_id) {
            this.notification_id = notification_id;
        }

        public String getSign_up_id() {
            return sign_up_id;
        }

        public void setSign_up_id(String sign_up_id) {
            this.sign_up_id = sign_up_id;
        }

        public String getPosting_id() {
            return posting_id;
        }

        public void setPosting_id(String posting_id) {
            this.posting_id = posting_id;
        }

        public String getLike_or_comment() {
            return like_or_comment;
        }

        public void setLike_or_comment(String like_or_comment) {
            this.like_or_comment = like_or_comment;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getCreated_date() {
            return created_date;
        }

        public void setCreated_date(String created_date) {
            this.created_date = created_date;
        }

        public String getNotification() {
            return notification;
        }

        public void setNotification(String notification) {
            this.notification = notification;
        }

        public List<FromUserBean> getFrom_user() {
            return from_user;
        }

        public void setFrom_user(List<FromUserBean> from_user) {
            this.from_user = from_user;
        }

        public List<PostingDetailBean> getPosting_detail() {
            return posting_detail;
        }

        public void setPosting_detail(List<PostingDetailBean> posting_detail) {
            this.posting_detail = posting_detail;
        }

        public static class FromUserBean  implements Serializable{
            /**
             * sign_up_id : 1
             * user_id : pankaj_1990
             * email : pankaj@gmail.com
             * profile_picture : image1.jpg
             * created_date : 2019-02-02 01:23:16
             */

            private String sign_up_id;
            private String user_id;
            private String email;
            private String profile_picture;
            private String created_date;

            public String getSign_up_id() {
                return sign_up_id;
            }

            public void setSign_up_id(String sign_up_id) {
                this.sign_up_id = sign_up_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getProfile_picture() {
                return profile_picture;
            }

            public void setProfile_picture(String profile_picture) {
                this.profile_picture = profile_picture;
            }

            public String getCreated_date() {
                return created_date;
            }

            public void setCreated_date(String created_date) {
                this.created_date = created_date;
            }
        }

        public static class PostingDetailBean implements Serializable{
            /**
             * postint_id : 1
             * sign_up_id : 1
             * posting_image : http://littleletterslinked.com/api/assets/image/profile/image1.jpg
             * created_date : 2019-02-02 23:31:08
             * user_name : cbuzc
             * likes : 27
             * comments : 60
             */

            private String postint_id;
            private String sign_up_id;
            private String posting_image;
            private String created_date;
            private String user_name;
            private int likes;
            private int comments;

            public String getPostint_id() {
                return postint_id;
            }

            public void setPostint_id(String postint_id) {
                this.postint_id = postint_id;
            }

            public String getSign_up_id() {
                return sign_up_id;
            }

            public void setSign_up_id(String sign_up_id) {
                this.sign_up_id = sign_up_id;
            }

            public String getPosting_image() {
                return posting_image;
            }

            public void setPosting_image(String posting_image) {
                this.posting_image = posting_image;
            }

            public String getCreated_date() {
                return created_date;
            }

            public void setCreated_date(String created_date) {
                this.created_date = created_date;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public int getLikes() {
                return likes;
            }

            public void setLikes(int likes) {
                this.likes = likes;
            }

            public int getComments() {
                return comments;
            }

            public void setComments(int comments) {
                this.comments = comments;
            }
        }
    }
}
