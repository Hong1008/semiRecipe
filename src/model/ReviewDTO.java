package model;

import java.util.Date;

public class ReviewDTO {
	private int review_num;
	
	private String review_subject;
	private String review_content;
	private String review_rate;
	private Date review_date;
	private int review_views;
	private String review_url;
	private String user_id;
	private int recipe_id;
	public ReviewDTO() {

	}
	public int getReview_num() {
		return review_num;
	}
	public void setReview_num(int review_num) {
		this.review_num = review_num;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	public String getReview_rate() {
		return review_rate;
	}
	public void setReview_rate(String review_rate) {
		this.review_rate = review_rate;
	}
	public Date getReview_date() {
		return review_date;
	}
	public void setReview_date(Date review_date) {
		this.review_date = review_date;
	}
	public int getReview_views() {
		return review_views;
	}
	public void setReview_views(int review_views) {
		this.review_views = review_views;
	}
	public String getReview_url() {
		return review_url;
	}
	public void setReview_url(String review_url) {
		this.review_url = review_url;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getRecipe_id() {
		return recipe_id;
	}
	public void setRecipe_id(int recipe_id) {
		this.recipe_id = recipe_id;
	}
	public String getReview_subject() {
		return review_subject;
	}
	public void setReview_subject(String review_subject) {
		this.review_subject = review_subject;
	}
	
}//end class
