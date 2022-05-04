package edu.it.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class AspectoTraceable {
	@Before("@annotation(edu.it.annotations.Traceable)")
	public void ejecutarAntesDeLlamarAlMetodo(JoinPoint joinPoint) {
		log.info("Me estoy anteponiendo a la llamada al metodo !!!");
		log.info(joinPoint.getKind());
		log.info(joinPoint.getTarget().toString());
		log.info(joinPoint.getSignature().getName());
		Object[] argumentos = joinPoint.getArgs();
		for (Object o : argumentos) {
			log.info(o.toString());
		}
		log.info("");
		
		/*
		 * Como ejercicio, estaria bueno loggear todo esto de manera
		 * estandar, ejemplo
		 * Aspecto Traceable: INFO: Metodo param1, param2, etc.
		 */
		
		try { Thread.sleep(8000); } catch (Exception ex) {} 
	}
}
