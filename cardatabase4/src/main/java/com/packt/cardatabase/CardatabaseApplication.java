package com.packt.cardatabase;

import com.packt.cardatabase.domain.*;
import com.packt.cardatabase.web.AppUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.packt.cardatabase.domain.CarRepository;

import java.util.Arrays;

@SpringBootApplication
public class CardatabaseApplication implements CommandLineRunner {
	private static final Logger logger= LoggerFactory.getLogger(CardatabaseApplication.class);


	private final CarRepository repository;
	private final OwnerRepository oRepository;
	private final AppUserRepository uRepository;

	public CardatabaseApplication(CarRepository repository, OwnerRepository oRepository, AppUserRepository urepository) {
		this.repository = repository;
		this.oRepository = oRepository;
        this.uRepository = urepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(CardatabaseApplication.class, args);
		logger.info("애플리케이션 실행");
	}

	@Override
	public void run(String... args) throws Exception {

		Owner owner1 = new Owner("John", "johnson");
		Owner owner2 = new Owner("Mary", "Robinson");
		Owner owner3 = new Owner("근수", "안");
		oRepository.saveAll(Arrays.asList(owner1, owner2, owner3));

		repository.save(new Car("Ford", "Mustang", "Red", "ADF-11121", 2023, 59000, owner1));
		repository.save(new Car("Nissan", "Leaf", "White", "SSJ-3002", 2020, 29000, owner1));
		repository.save(new Car("Toyota", "Prius", "Sliver", "KKO-0212", 2022, 39000, owner2));
		repository.save(new Car("Kia", "Seltos", "Chacoal", "360수5690", 2020, 28000, owner3));


		for (Car car : repository.findAll()) {
			logger.info("브랜드: {}, 모델명: {}", car.getBrand(), car.getModel());
		}

		// 사용자명 : user, 비밀번호: user
		uRepository.save(new AppUser("user", "$2y$04$Y9/VuzC52OVWNl4udjPjZOh0JYWbR8SojpWTcqa0zDVb1/FE2YAje","User"));
		// 사용자명 : admin, 비밀번호: admin
		uRepository.save(new AppUser("admin", "$2y$04$.DZ0p6zh1phfEFOFpY000emjEj1qJpH1HjSE4wnP6pYVbcwtMKLXu","admin"));


	}
}
