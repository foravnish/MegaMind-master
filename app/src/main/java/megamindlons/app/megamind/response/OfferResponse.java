package megamindlons.app.megamind.response;

import java.io.Serializable;
import java.util.List;

public class OfferResponse {


    /**
     * status : success
     * message : [{"id":"1940","promo_id":"P98433","offer_id":"1362","offer_name":"OYOrooms.com CPS - India","type":"Coupon","code":"VCOYO55","title":"Flat 55% off on all properties","description":"TnC: Valid on bookings created till 30th June, Valid on check-ins created till 30th June, Valid For ","category":"Hotels","offer_page":"http://tracking.vcommission.com/aff_c?offer_id=1362&aff_id=92992","preview_url":"","date_added":"Apr 24, 2019","expiry":"Jun 30, 2019","exclusive":"0","featured":"0","created":"2019-06-10 02:36:02"},{"id":"1941","promo_id":"P98434","offer_id":"1362","offer_name":"OYOrooms.com CPS - India","type":"Coupon","code":"VCOYO60NEW","title":"Flat 60% off on all properties","description":"TnC: Valid on bookings created till 30th June, Valid on check-ins created till 30th June, Valid For ","category":"Hotels","offer_page":"http://tracking.vcommission.com/aff_c?offer_id=1362&aff_id=92992","preview_url":"","date_added":"Apr 24, 2019","expiry":"Jun 30, 2019","exclusive":"0","featured":"0","created":"2019-06-10 02:36:02"},{"id":"1942","promo_id":"P98436","offer_id":"1362","offer_name":"OYOrooms.com CPS - India","type":"Coupon","code":"VCOYO55","title":"Flat 60% off on all properties","description":"TnC: Valid on bookings created till 30th June, Valid on check-ins created till 30th June, Valid For ","category":"Hotels","offer_page":"http://tracking.vcommission.com/aff_c?offer_id=1362&aff_id=92992","preview_url":"","date_added":"Apr 25, 2019","expiry":"Jun 30, 2019","exclusive":"0","featured":"0","created":"2019-06-10 02:36:02"},{"id":"1953","promo_id":"P98900","offer_id":"2417","offer_name":"FabHotels.com CPS - India","type":"Coupon","code":"FH1000","title":"Exclusive Offer! Flat Rs.1000 Off","description":"","category":"Hotels","offer_page":"http://tracking.vcommission.com/aff_c?offer_id=2417&aff_id=92992&url=https%3A%2F%2Fwww.fabhotels.com%2Fdeals%2Ffab-exclusive-offer-1000%3Futm_source%3Dalliance%26utm_medium%3Dvcommission_cps%26utm_campaign%3D%7Baffiliate_id%7D%26utm_content%3D1","preview_url":"https://www.fabhotels.com/deals/fab-exclusive-offer-1000","date_added":"May 30, 2019","expiry":"Jun 30, 2019","exclusive":"0","featured":"0","created":"2019-06-10 02:36:02"},{"id":"2023","promo_id":"P98949","offer_id":"2860","offer_name":"TreeboHotels.com CPS - India","type":"Coupon","code":"TRB50","title":"Flat 50% off on all treebo hotels","description":"","category":"Hotels","offer_page":"http://tracking.vcommission.com/aff_c?offer_id=2860&aff_id=92992&url=https%3A%2F%2Fwww.treebo.com%3Futm_source%3DAffiliate%26utm_medium%3DVcom%26utm_campaign%3D%7Baffiliate_id%7D","preview_url":"https://www.treebo.com","date_added":"Jun 06, 2019","expiry":"Jun 30, 2019","exclusive":"0","featured":"0","created":"2019-06-10 02:36:02"},{"id":"2024","promo_id":"P98950","offer_id":"2860","offer_name":"TreeboHotels.com CPS - India","type":"Coupon","code":"TRB55","title":"Flat 55% off on all treebo hotels","description":"","category":"Hotels","offer_page":"http://tracking.vcommission.com/aff_c?offer_id=2860&aff_id=92992&url=https%3A%2F%2Fwww.treebo.com%3Futm_source%3DAffiliate%26utm_medium%3DVcom%26utm_campaign%3D%7Baffiliate_id%7D","preview_url":"https://www.treebo.com","date_added":"Jun 06, 2019","expiry":"Jun 30, 2019","exclusive":"0","featured":"0","created":"2019-06-10 02:36:02"},{"id":"2025","promo_id":"P98951","offer_id":"2860","offer_name":"TreeboHotels.com CPS - India","type":"Coupon","code":"TRBCB200","title":"Flat 50% off   200 Cashback","description":"","category":"Hotels","offer_page":"http://tracking.vcommission.com/aff_c?offer_id=2860&aff_id=92992&url=https%3A%2F%2Fwww.treebo.com%3Futm_source%3DAffiliate%26utm_medium%3DVcom%26utm_campaign%3D%7Baffiliate_id%7D","preview_url":"https://www.treebo.com","date_added":"Jun 06, 2019","expiry":"Jun 30, 2019","exclusive":"0","featured":"0","created":"2019-06-10 02:36:02"}]
     * total_page : 1
     */

    private String status;
    private int total_page;
    private List<MessageBean> message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public List<MessageBean> getMessage() {
        return message;
    }

    public void setMessage(List<MessageBean> message) {
        this.message = message;
    }

    public static class MessageBean  implements Serializable {
        /**
         * id : 1940
         * promo_id : P98433
         * offer_id : 1362
         * offer_name : OYOrooms.com CPS - India
         * type : Coupon
         * code : VCOYO55
         * title : Flat 55% off on all properties
         * description : TnC: Valid on bookings created till 30th June, Valid on check-ins created till 30th June, Valid For
         * category : Hotels
         * offer_page : http://tracking.vcommission.com/aff_c?offer_id=1362&aff_id=92992
         * preview_url :
         * date_added : Apr 24, 2019
         * expiry : Jun 30, 2019
         * exclusive : 0
         * featured : 0
         * created : 2019-06-10 02:36:02
         */

        private String id;
        private String promo_id;
        private String offer_id;
        private String offer_name;
        private String type;
        private String code;
        private String title;
        private String description;
        private String category;
        private String offer_page;
        private String preview_url;
        private String date_added;
        private String expiry;
        private String exclusive;
        private String featured;
        private String created;
        private String cat_image;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPromo_id() {
            return promo_id;
        }

        public void setPromo_id(String promo_id) {
            this.promo_id = promo_id;
        }

        public String getOffer_id() {
            return offer_id;
        }

        public void setOffer_id(String offer_id) {
            this.offer_id = offer_id;
        }

        public String getOffer_name() {
            return offer_name;
        }

        public void setOffer_name(String offer_name) {
            this.offer_name = offer_name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
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

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getOffer_page() {
            return offer_page;
        }

        public void setOffer_page(String offer_page) {
            this.offer_page = offer_page;
        }

        public String getPreview_url() {
            return preview_url;
        }

        public void setPreview_url(String preview_url) {
            this.preview_url = preview_url;
        }

        public String getDate_added() {
            return date_added;
        }

        public void setDate_added(String date_added) {
            this.date_added = date_added;
        }

        public String getExpiry() {
            return expiry;
        }

        public void setExpiry(String expiry) {
            this.expiry = expiry;
        }

        public String getExclusive() {
            return exclusive;
        }

        public void setExclusive(String exclusive) {
            this.exclusive = exclusive;
        }

        public String getFeatured() {
            return featured;
        }

        public void setFeatured(String featured) {
            this.featured = featured;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getCat_image() {
            return cat_image;
        }

        public void setCat_image(String cat_image) {
            this.cat_image = cat_image;
        }
    }
}
