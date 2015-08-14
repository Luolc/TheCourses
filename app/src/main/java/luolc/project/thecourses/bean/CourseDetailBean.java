package luolc.project.thecourses.bean;

import java.util.List;

public class CourseDetailBean {
	private int code;
	private List<CourseTagBean> tags;
	private String characteristic;
	private List<CommentBean> comments;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public List<CourseTagBean> getTags() {
		return tags;
	}
	public void setTags(List<CourseTagBean> tags) {
		this.tags = tags;
	}
	public String getCharacteristic() {
		return characteristic;
	}
	public void setCharacteristic(String characteristic) {
		this.characteristic = characteristic;
	}
	public List<CommentBean> getComments() {
		return comments;
	}
	public void setComments(List<CommentBean> comments) {
		this.comments = comments;
	}
}
