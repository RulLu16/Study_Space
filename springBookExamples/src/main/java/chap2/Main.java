package chap2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AnnotationConfigApplicationContext ctx= //자바 설정에서 정보 읽어와 빈 객체 생성, 관리
				new AnnotationConfigApplicationContext(AppContext.class); //AppContext.class를 생성자 파라미터로 전달
		Greeter g1=ctx.getBean("greeter",Greeter.class); //greeter 객체 검색, getBean(빈 객체 이름, 빈 객체 타입)
		Greeter g2=ctx.getBean("greeter",Greeter.class);
		String msg=g1.greet("스프링");
		System.out.println(msg);
		System.out.println("(g1==g2): "+(g1==g2)); // 싱글톤 객체. 스프링은 별도 설정 없을 시 한개의 빈 객체만 생성.
		ctx.close();
	}

}
