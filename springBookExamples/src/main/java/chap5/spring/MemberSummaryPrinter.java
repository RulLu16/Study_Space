package chap5.spring;

public class MemberSummaryPrinter extends MemberPrint {
	
	@Override
	public void print(Member member) {
		System.out.printf("information: name=%s\n", member.getName());
	}
	
}
