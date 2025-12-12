package com.coding.PracticeSpring;

import com.coding.PracticeSpring.PayInterface.PaymentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PracticeSpringApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PracticeSpringApplication.class, args);
	}


    final PaymentServices paymentServices;

    public PracticeSpringApplication(PaymentServices paymentServices) {
        this.paymentServices = paymentServices;
    }

    @Override
    public void run(String... args) throws Exception {
        paymentServices.send();
    }
}
