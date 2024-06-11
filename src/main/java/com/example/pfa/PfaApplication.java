package com.example.pfa;

import com.example.pfa.Dto.RegisterRequest;
import com.example.pfa.Entities.Etablissement;
import com.example.pfa.Repository.EtablissementRepository;
import com.example.pfa.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import static com.example.pfa.Entities.Role.Admin;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class PfaApplication {
	private final EtablissementRepository etablissementRepository;

	public PfaApplication(EtablissementRepository etablissementRepository) {
		this.etablissementRepository = etablissementRepository;
		if (this.etablissementRepository == null) {
			System.out.println("Repository is null");
		} else {
			System.out.println("Repository is not null");
		}
	}
	public static void main(String[] args) {
		SpringApplication.run(PfaApplication.class, args);
	}
	/* @Bean
	public CommandLineRunner commandLineRunner(AuthenticationService authenticationService){
		return args -> {
			// Ensure the Etablissement exists
			Etablissement etablissement = etablissementRepository.findById(1L)
					.orElseGet(() -> {
						Etablissement newEtablissement = new Etablissement();
						newEtablissement.setNom("Default Etablissement");
						newEtablissement.setAdresse("Default Address");
						newEtablissement.setEmail("default@example.com");
						newEtablissement.setStatus(true); // Assuming status is a boolean
						return etablissementRepository.save(newEtablissement);
					});

			// Create users
			var admin = RegisterRequest.builder()
					.Nom("admin")
					.Prenom("yassmin")
					.email("boukhchanay@gmail.com")
					.password("admin")
					.role(Admin)
					.telephone(99506512)
					.adresse("Gabes")
					.EtablissementId(etablissement.getId())
					.build();
			System.out.println("Admin token: " + authenticationService.register(admin).getToken());
		};

	}
*/
}
