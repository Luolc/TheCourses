package luolc.project.thecourses.bean;

public class CommentBean {
	private String date;
	private int student_code;
	private String author;
	private String content;

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getStudent_code() {
		return student_code;
	}
	public void setStudent_code(int student_code) {
		this.student_code = student_code;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
