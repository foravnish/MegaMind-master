package megamindlons.app.megamind.response;

import java.util.List;

public class VideosResponse{
	private List<MessageItem> message;
	private String status;

	public void setMessage(List<MessageItem> message){
		this.message = message;
	}

	public List<MessageItem> getMessage(){
		return message;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"VideosResponse{" + 
			"message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}


	public static class MessageItem{
		private String id;
		private String created_date;
		private String title;
		private String url_key;
		private String status;

		public void setId(String id){
			this.id = id;
		}

		public String getId(){
			return id;
		}



		public void setCreated_date(String created_date) {
			this.created_date = created_date;
		}

		public String getCreated_date() {
			return created_date;
		}

		public void setTitle(String title){
			this.title = title;
		}

		public String getTitle(){
			return title;
		}



		public void setUrl_key(String url_key) {
			this.url_key = url_key;
		}

		public String getUrl_key() {
			return url_key;
		}

		public void setStatus(String status){
			this.status = status;
		}

		public String getStatus(){
			return status;
		}

		@Override
		public String toString(){
			return
					"MessageItem{" +
							"id = '" + id + '\'' +
							",created_date = '" + created_date + '\'' +
							",title = '" + title + '\'' +
							",url_key = '" + url_key + '\'' +
							",status = '" + status + '\'' +
							"}";
		}
	}
}

