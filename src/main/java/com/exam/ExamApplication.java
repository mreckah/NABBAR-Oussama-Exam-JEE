package com.exam;

import com.exam.entity.Client;
import com.exam.entity.CreditImmobilier;
import com.exam.entity.CreditPersonnel;
import com.exam.entity.CreditProfessionnel;
import com.exam.entity.Remboursement;
import com.exam.entity.StatutCredit;
import com.exam.entity.TypeRemboursement;
import com.exam.repository.ClientRepository;
import com.exam.repository.CreditRepository;
import com.exam.repository.RemboursementRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class ExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(ClientRepository clientRepository,
			CreditRepository creditRepository,
			RemboursementRepository remboursementRepository) {
		return args -> {
			// Create clients
			Client client1 = new Client();
			client1.setNom("Oussama");
			client1.setEmail("Oussama@email.com");
			clientRepository.save(client1);

			Client client2 = new Client();
			client2.setNom("NABBAR");
			client2.setEmail("NABBAR@email.com");
			clientRepository.save(client2);

			// Create credits
			// Credit Personnel
			CreditPersonnel creditPersonnel = new CreditPersonnel();
			creditPersonnel.setClient(client1);
			creditPersonnel.setDateDemande(LocalDate.now().minusMonths(1));
			creditPersonnel.setStatut(StatutCredit.ACCEPTE);
			creditPersonnel.setDateAcception(LocalDate.now().minusMonths(1).plusDays(5));
			creditPersonnel.setMontant(5000.0);
			creditPersonnel.setDureeRemboursement(12);
			creditPersonnel.setTauxInteret(4.5);
			creditPersonnel.setMotif("Achat voiture");
			creditRepository.save(creditPersonnel);

			// Credit Immobilier
			CreditImmobilier creditImmobilier = new CreditImmobilier();
			creditImmobilier.setClient(client2);
			creditImmobilier.setDateDemande(LocalDate.now().minusMonths(2));
			creditImmobilier.setStatut(StatutCredit.ACCEPTE);
			creditImmobilier.setDateAcception(LocalDate.now().minusMonths(2).plusDays(10));
			creditImmobilier.setMontant(200000.0);
			creditImmobilier.setDureeRemboursement(240);
			creditImmobilier.setTauxInteret(3.2);
			creditImmobilier.setTypeBien("Appartement");
			creditRepository.save(creditImmobilier);

			// Credit Professionnel
			CreditProfessionnel creditProfessionnel = new CreditProfessionnel();
			creditProfessionnel.setClient(client1);
			creditProfessionnel.setDateDemande(LocalDate.now().minusMonths(3));
			creditProfessionnel.setStatut(StatutCredit.EN_ATTENTE);
			creditProfessionnel.setMontant(50000.0);
			creditProfessionnel.setDureeRemboursement(60);
			creditProfessionnel.setTauxInteret(5.0);
			creditProfessionnel.setMotif("Développement entreprise");
			creditProfessionnel.setRaisonSociale("Dupont SARL");
			creditRepository.save(creditProfessionnel);

			// Create remboursements for the personal credit
			Remboursement remboursement1 = new Remboursement();
			remboursement1.setCredit(creditPersonnel);
			remboursement1.setDate(LocalDate.now().minusDays(15));
			remboursement1.setMontant(450.0);
			remboursement1.setType(TypeRemboursement.MENSUEL);
			remboursementRepository.save(remboursement1);

			Remboursement remboursement2 = new Remboursement();
			remboursement2.setCredit(creditPersonnel);
			remboursement2.setDate(LocalDate.now());
			remboursement2.setMontant(450.0);
			remboursement2.setType(TypeRemboursement.MENSUEL);
			remboursementRepository.save(remboursement2);

			// Create remboursements for the real estate credit
			Remboursement remboursement3 = new Remboursement();
			remboursement3.setCredit(creditImmobilier);
			remboursement3.setDate(LocalDate.now().minusDays(10));
			remboursement3.setMontant(1200.0);
			remboursement3.setType(TypeRemboursement.MENSUEL);
			remboursementRepository.save(remboursement3);

			System.out.println("Sample data initialized successfully!");
		};
	}
}
