package kr.ac.sogang.java;

public class TodoDto {
	Long id;
	String name;
	String regDate;
	int sequence;
	String title;
	String type;
	
	public TodoDto() {
		// TODO Auto-generated constructor stub
	}
	public void setId(long id) {
		this.id=id;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void setRegDate(String date) {
		this.regDate=date;
	}
	public void setSequence(int se) {
		this.sequence=se;
	}
	public void setTitle(String title) {
		this.title=title;
	}
	public void setType(String type) {
		this.type=type;
	}
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getRegDate() {
		return regDate;
	}
	public int getSequence() {
		return sequence;
	}
	public String getTitle() {
		return title;
	}
	public String getType() {
		return type;
	}
}
