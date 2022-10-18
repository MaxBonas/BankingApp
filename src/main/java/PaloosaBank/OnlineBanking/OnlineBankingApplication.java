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


		AccountHolder accountHolder1 = new AccountHolder("May Lord", LocalDate.of(1989, 3, 22),
				new Address("Anselm Clave 7", "Corbera de Llobregat", "08757"),
				new Address("Carrer Caceres 26", "Barcelona", "08021"));
		AccountHolder accountHolder2 = new AccountHolder("Bill Adama", LocalDate.of(1943, 10, 30),
				new Address("Palm Springs st 89", "Miami", "64532"),
				null);
		AccountHolder accountHolder3 = new AccountHolder("Kara Thrace", LocalDate.of(1975, 8, 2),
				new Address("Turette ave. 1265", "L.A.", "867-563"),
				null);
		AccountHolder accountHolder4 = new AccountHolder("Thomson McThomson", LocalDate.of(1998, 9, 2),
				new Address("Carrer Viviera", "La Calma", "23157"),
				new Address("Carrer Victoria", "Barcelona", "08032"));
		AccountHolder accountHolder5 = new AccountHolder("Crystal Clair", LocalDate.of(1973, 11, 20),
				new Address("Buenaventura st 91", "Santa Carla", "68-654"),
				null);
		AccountHolder accountHolder6 = new AccountHolder("Kant BeRight", LocalDate.of(1935, 1, 24),
				new Address("Crisol ave. 365", "New York", "46266"),
				null);
		accountHolderRepository.saveAll(List.of(accountHolder1, accountHolder2, accountHolder3, accountHolder4, accountHolder5,
				accountHolder6));   // Another way to do it, with other strong points

		adminRepository.saveAll(List.of(
				new Admin("Emilio Botín"),
				new Admin("Tio Gilito"),
				new Admin("Luis Bàrcenas")));

		thirdPartyRepository.saveAll(List.of(
				new ThirdParty("Zara .ORG"),
				new ThirdParty("Aldi Inc."),
				new ThirdParty("Audi .s.a")));

		checkingRepository.saveAll(List.of(
				new Checking(new Money(BigDecimal.valueOf(1010.13)), accountHolder1, null),
				new Checking(new Money(BigDecimal.valueOf(23002.23)), accountHolder3, accountHolder2),
				new Checking(new Money(BigDecimal.valueOf(53)), accountHolder5, accountHolder4)));

		creditCardRepository.saveAll(List.of(
				new CreditCard(new Money(BigDecimal.valueOf(2310.98)), accountHolder4, null),
				new CreditCard(new Money(BigDecimal.valueOf(250.45)), accountHolder1, accountHolder2),
				new CreditCard(new Money(BigDecimal.valueOf(12000350)), accountHolder3, accountHolder1)));

		savingsRepository.saveAll(List.of(
				new Savings(new Money(BigDecimal.valueOf(46023.53)), accountHolder5, null),
				new Savings(new Money(BigDecimal.valueOf(342.30)), accountHolder3, accountHolder1),
				new Savings(new Money(BigDecimal.valueOf(2569.32)), accountHolder2, null)));

		studentsCheckingRepository.saveAll(List.of(
				new StudentsChecking(new Money(BigDecimal.valueOf(7801.21)), accountHolder1, accountHolder4),
				new StudentsChecking(new Money(BigDecimal.valueOf(1010.00)), accountHolder2, null),
				new StudentsChecking(new Money(BigDecimal.valueOf(27.90)), accountHolder5, accountHolder3)));


	}
}

// Todo: HashKey ThirdParty, how works?
//        todo requestheader haskey? --
// Todo: Revisar Validates
// Todo: Secretkey accounts, how works? is security?
// Todo: PatchBalanceAnyAccount (Account). Donde va? se repite?
// Todo: Transfer Method. General in Account or Account Holder... OR specific in each account type?
// Todo: MonthlySpended. confirm How works
// Todo: < 24 creation account method
// Todo: Credit Limit. Confirm how works
// Todo: Interested rate (CreditCard) confirm how works
// Todo: Penalty Fee confirm how works. Como resta?
// Todo: minimum balance. confirm how works
// Todo: GetAbstractById (Account) hace falta?
// Todo: Revisar Controllers
// Todo: Revisar Services



