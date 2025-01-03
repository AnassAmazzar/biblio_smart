package ma.emsi.salesmanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.time.LocalDate;

@SpringBootApplication
@EnableFeignClients
public class SalesManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesManagementServiceApplication.class, args);
		System.out.println(LocalDate.now());
	}

}
