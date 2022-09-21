package com.board.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAspect {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	//execution(BoardDTO select*(..))=> 리턴 타입이 BoardDTO 클래스이고, 메소드의 이름이 select로 시작하며,
	//파라미터가 0개 이상인 모든 메서드가 호출될 때 
	// 실행할 위치 
	@Around("execution(* com.board..controller.*Controller.*(..)) or execution(* com.board..service.*Impl.*(..)) ")
	public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {

		String type = "";
		String name = joinPoint.getSignature().getDeclaringTypeName();
		System.out.println(name);
		if (name.contains("Controller") == true) {
			type = "Controller ===> ";

		} else if (name.contains("Service") == true) {
			type = "ServiceImpl ===> ";

		} else if (name.contains("Mapper") == true) {
			type = "Mapper ===> ";
		}

		logger.debug(type + name + "." + joinPoint.getSignature().getName() + "()");
		return joinPoint.proceed();
	}

}