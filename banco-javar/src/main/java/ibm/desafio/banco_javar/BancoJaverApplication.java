package ibm.desafio.banco_javar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@FeignClient
public class BancoJaverApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancoJaverApplication.class, args);
	}

}
