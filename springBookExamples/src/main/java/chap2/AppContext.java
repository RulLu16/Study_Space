package chap2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //해당 클래스를 스프링 설정 클래스로 지정
public class AppContext {
	
	@Bean //해당 매소드가 생성한 객체를 스프링이 관리하는 빈 객체로 등록
	public Greeter greeter() {
		Greeter g=new Greeter();
		g.setFormat("%s, Hello!");
		return g;
	} //객체를 생성하고 초기화하는 설정
}
