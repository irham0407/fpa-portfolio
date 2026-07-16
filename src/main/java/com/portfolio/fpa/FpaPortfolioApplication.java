package com.portfolio.fpa;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class FpaPortfolioApplication {

	@PostConstruct
	public void init(){
		// Mengunci zona waktu sistem aplikasi ke Asia/Jakarta (UTC+7)
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Jakarta"));
		System.out.println("Zona waktu aplikasi berhasil diatur ke: " + TimeZone.getDefault().getID());
	}

	public static void main(String[] args) {
		SpringApplication.run(FpaPortfolioApplication.class, args);
	}

}
