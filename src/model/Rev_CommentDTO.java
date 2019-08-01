package model;

public class Rev_CommentDTO {
	private int rcom_num;
	private String rcom_content;
	private String user_id;
	private int review_num;
	public Rev_CommentDTO() {

	}
	public int getRcom_num() {
		return rcom_num;
	}
	public void setRcom_num(int rcom_num) {
		this.rcom_num = rcom_num;
	}
	public String getRcom_content() {
		return rcom_content;
	}
	public void setRcom_content(String rcom_content) {
		this.rcom_content = rcom_content;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getReview_num() {
		return review_num;
	}
	public void setReview_num(int review_num) {
		this.review_num = review_num;
	}
	
}
