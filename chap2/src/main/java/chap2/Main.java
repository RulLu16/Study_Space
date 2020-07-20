package chap2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AnnotationConfigApplicationContext ctx= //자바 설정에서 정보 읽어와 빈 객체 생성, 관리
				new AnnotationConfigApplicationContext(AppContext.class); //AppContext.class를 생성자 파라미터로 전달
		Greeter g=ctx.getBean("greeter",Greeter.class); //greeter 객체 검색, getBean(빈 객체 이름, 빈 객체 타입)
		String msg=g.greet("스프링");
		System.out.println(msg);
		ctx.close();
	}

}
