package PaloosaBank.OnlineBanking;

import PaloosaBank.OnlineBanking.embedables.Address;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Checking;
import PaloosaBank.OnlineBanking.entities.accounts.CreditCard;
import PaloosaBank.OnlineBanking.entities.accounts.Savings;
import PaloosaBank.OnlineBanking.entities.accounts.StudentsChecking;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.entities.users.Admin;
import PaloosaBank.OnlineBanking.entities.users.ThirdParty;
import PaloosaBank.OnlineBanking.repositories.accounts.*;
import PaloosaBank.OnlineBanking.repositories.users.AccountHolderRepository;
import PaloosaBank.OnlineBanking.repositories.users.AdminRepository;
import PaloosaBank.OnlineBanking.repositories.users.ThirdPartyRepository;
import PaloosaBank.OnlineBanking.repositories.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@SpringBootApplication
public class OnlineBankingApplication implements CommandLineRunner {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	CheckingRepository checkingRepository;

	@Autowired
	CreditCardRepository creditCardRepository;

	@Autowired
	SavingsRepository savingsRepository;

	@Autowired
	StudentsCheckingRepository studentsCheckingRepository;

	@Autowired
	AccountHolderRepository accountHolderRepository;

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	ThirdPartyRepository thirdPartyRepository;

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(OnlineBankingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		Checking checking1 = new Checking(new Money(BigDecimal.valueOf(1010.13)), null, null,
				LocalDate.of(2012, 12, 12), null);
		Checking checking2 = new Checking(new Money(BigDecimal.valueOf(23002.23)), null, null,
				LocalDate.of(2038, 6, 8), null);
		Checking checking3 = new Checking(new Money(BigDecimal.valueOf(53)), null, null,
				LocalDate.of(1872, 2, 7), null);
		checkingRepository.saveAll(List.of(checking1, checking2, checking3)); // Another way to do it, with other strong points

		creditCardRepository.saveAll(List.of(
				new CreditCard(new Money(BigDecimal.valueOf(2310.98)), null, null,
						LocalDate.of(2008, 1, 2), null),
				new CreditCard(new Money(BigDecimal.valueOf(250.45)), null, null,
						LocalDate.of(2021, 5, 15), null),
				new CreditCard(new Money(BigDecimal.valueOf(12000350)), null, null,
						LocalDate.of(2003, 7, 4), null)));

		savingsRepository.saveAll(List.of(
				new Savings(new Money(BigDecimal.valueOf(46023.53)), null, null,
						LocalDate.of(1923, 4, 21), null),
				new Savings(new Money(BigDecimal.valueOf(342.30)), null, null,
						LocalDate.of(1999, 1, 11), null),
				new Savings(new Money(BigDecimal.valueOf(2569.32)), null, null,
						LocalDate.of(2001, 9, 8), null)));

		studentsCheckingRepository.saveAll(List.of(
				new StudentsChecking(new Money(BigDecimal.valueOf(7801.21)), null, null,
						LocalDate.of(2022, 11, 16), null),
				new StudentsChecking(new Money(BigDecimal.valueOf(1010.00)), null, null,
						LocalDate.of(2005, 8, 9), null),
				new StudentsChecking(new Money(BigDecimal.valueOf(27.90)), null, null,
						LocalDate.of(1993, 2, 28), null)));

		adminRepository.saveAll(List.of(
				new Admin("Emilio Botín"),
				new Admin("Tio Gilito"),
				new Admin("Luis Bàrcenas")));

		accountHolderRepository.saveAll(List.of(
				new AccountHolder("Max Bonas", LocalDate.of(1989, 3, 22),
						new Address("Anselm Clave 7", "Corbera de Llobregat", 8757),
						new Address("Anselm Clave 7", "Barcelona", 8757)),
				//TODO porque no me deja poner un codigo postal con 0 delante
				new AccountHolder("Bill Adama", LocalDate.of(1943, 10, 30),
						new Address("Palm Springs st 89", "Miami", 64532), null),
				new AccountHolder("Kara Thrace", LocalDate.of(1975, 8, 2),
						new Address("Turette ave. 1265", "L.A.", 86756), null)));

		thirdPartyRepository.saveAll(List.of(
				new ThirdParty("Zara .ORG", "UJYGFjhgfvjghFV764576FG"),
				new ThirdParty("Aldi Inc.", "jhvfjgjg563465DYHhfv"),
				new ThirdParty("Audi .s.a", "JHGFJHUG7654FVghjhg")));

	}
}

