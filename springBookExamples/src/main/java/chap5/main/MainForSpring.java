package chap5.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import chap4.config.AppCtx;
import chap4.spring.ChangePasswordService;
import chap4.spring.DuplicateMemberException;
import chap4.spring.MemberInfoPrinter;
import chap4.spring.MemberNotFoundException;
import chap4.spring.MemberRegisterService;
import chap4.spring.RegisterRequest;
import chap4.spring.WrongIdPasswordException;

import chap3.spring.MemberListPrinter;

public class MainForSpring {
	
	private static ApplicationContext ctx = null;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		ctx = new AnnotationConfigApplicationContext(AppCtx.class); 
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			System.out.println("Input command: ");
			String command = reader.readLine();
			if(command.equalsIgnoreCase("exit")) {
				System.out.println("terminate program.");
				break;
			}
			
			if(command.startsWith("new ")) {
				processNewCommand(command.split(" "));
				continue;
			} else if(command.startsWith("change ")) {
				processChangeCommand(command.split(" "));
				continue;
			} else if(command.equals("list")) {
				processListCommand();
				continue;
			} else if(command.startsWith("info ")) {
				processInfoCommand(command.split(" "));
				continue;
			}
			
			printHelp();
		}
	}
	
	private static void processNewCommand(String[] arg) {
		if(arg.length != 5) {
			printHelp();
			return;
		}
		
		MemberRegisterService regSvc = ctx.getBean(MemberRegisterService.class);
		RegisterRequest req = new RegisterRequest();
		req.setEmail(arg[1]);
		req.setName(arg[2]);
		req.setPassword(arg[3]);
		req.setConfirmPassword(arg[4]);
		
		if(!req.isPasswordEqualToConfirmPassword()) {
			System.out.println("Wrong password!");
			return;
		}
		try {
			regSvc.regist(req);
			System.out.println("Assign complete");
		} catch(DuplicateMemberException e) {
			System.out.println("Already exist");
		}
	}
	
	private static void processChangeCommand(String[] arg) {
		if(arg.length != 4) {
			printHelp();
			return;
		}
		
		// component scan을 했으므로 타입만으로 구할 수 있게된다.
		// 해당 클래스에 대한 bean이 하나만 만들어지니까 없어도 되는 것 아닐까?
		ChangePasswordService changePwdSvc = ctx.getBean(ChangePasswordService.class);
		
		try {
			changePwdSvc.changePassword(arg[1], arg[2], arg[3]);
			System.out.println("password changed");
		} catch(MemberNotFoundException e) {
			System.out.println("not exist");
		} catch(WrongIdPasswordException e) {
			System.out.println("wrong password");
		}
	}
	
	private static void printHelp() {
		System.out.println();
		System.out.println("Wrong command");
		System.out.println("Please use this command:");
		System.out.println("new / change ");
	}
	
	private static void processListCommand() {
		MemberListPrinter listPrinter = 
				ctx.getBean("listPrinter", MemberListPrinter.class);
		listPrinter.printAll();
	}
	
	private static void processInfoCommand(String[] arg) {
		if(arg.length != 2) {
			printHelp();
			return;
		}
		MemberInfoPrinter infoPrinter = 
				ctx.getBean("infoPrinter", MemberInfoPrinter.class);
		infoPrinter.printMemberInfo(arg[1]);
	}

}
