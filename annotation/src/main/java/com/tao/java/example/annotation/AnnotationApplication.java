package com.tao.java.example.annotation;

import com.tao.java.example.annotation.mybatis.annotation.Gene;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AnnotationApplication {

	@Gene("hi")
	void hi(){System.out.println("hi,my Anno");}
	public static void main(String[] args) {
		SpringApplication.run(AnnotationApplication.class, args);
	}

}
