package chap3.spring;

public class MemberPrint {
	public void print(Member member) {
		System.out.printf("member info: id=%d, email=%s, name=%s, date=%tF\n",
				member.getId(), member.getEmail(), member.getName(), member.getRegisterDateTime());
	}

}
