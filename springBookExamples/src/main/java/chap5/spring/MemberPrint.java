package chap5.spring;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberPrint {
	private DateTimeFormatter dateTimeFormatter;
	
	public MemberPrint() {
		dateTimeFormatter = DateTimeFormatter.ofPattern("hello");
	}
	
	public void print(Member member) {
		System.out.printf("member info: id=%d, email=%s, name=%s, date=%tF\n",
				member.getId(), member.getEmail(), member.getName(), member.getRegisterDateTime());
	}
	
	@Autowired(required = false) 
	public void setDateFormatter(DateTimeFormatter dateTimeFormatter) {
		this.dateTimeFormatter = dateTimeFormatter;
	}

}
