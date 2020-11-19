package chap4.spring;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberPrint {
	private DateTimeFormatter dateTimeFormatter;
	
	public MemberPrint() {
		// 생성자에서 초기화를 해줄때 자동주입을 어떤 방식으로 했냐에 따라 넣는 초기화값이 달라진다.
		// required=false로 했다면 만약 주입할 빈이 없을 시, null조차 넣지 않는다.
		// @Nullable로 했다면 주입할 빈이 없을 시, null을 넣는다.
		// Optional을 사용했다면 주입할 빈이 없을 시, 값이 없는 Optional을 할당한다.
		dateTimeFormatter = DateTimeFormatter.ofPattern("hello");
	}
	
	public void print(Member member) {
		System.out.printf("member info: id=%d, email=%s, name=%s, date=%tF\n",
				member.getId(), member.getEmail(), member.getName(), member.getRegisterDateTime());
	}
	
	@Autowired(required = false) 
	// 자동 주입할 대상이 필수적인게 아니면, 즉 빈이 없어 주입을 못해도 상관 없다면 다음처럼 속성을 false로 지정한다.
	// 자바 8버전 부터는 의존 주입 대상에 Optional을 사용해도 된다. (Optional<DateTimeFormatter>)
	// @Nullable을 주입할 파라미터 앞에 붙이는 방법도 있다.
	public void setDateFormatter(DateTimeFormatter dateTimeFormatter) {
		this.dateTimeFormatter = dateTimeFormatter;
	}

}
