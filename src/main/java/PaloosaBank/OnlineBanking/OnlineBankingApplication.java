package PaloosaBank.OnlineBanking;

import PaloosaBank.OnlineBanking.embedables.Address;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.Transfer;
import PaloosaBank.OnlineBanking.entities.accounts.Checking;
import PaloosaBank.OnlineBanking.entities.accounts.CreditCard;
import PaloosaBank.OnlineBanking.entities.accounts.Savings;
import PaloosaBank.OnlineBanking.entities.accounts.StudentsChecking;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.entities.users.Admin;
import PaloosaBank.OnlineBanking.entities.users.Role;
import PaloosaBank.OnlineBanking.entities.users.ThirdParty;
import PaloosaBank.OnlineBanking.repositories.TransferRepository;
import PaloosaBank.OnlineBanking.repositories.accounts.*;
import PaloosaBank.OnlineBanking.repositories.users.*;
import PaloosaBank.OnlineBanking.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@SpringBootApplication
public class OnlineBankingApplication implements CommandLineRunner {

	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	RoleRepository roleRepository;
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
	TransferRepository transferRepository;
	@Autowired
	TransferService transferService;

	public static void main(String[] args) {
		SpringApplication.run(OnlineBankingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		AccountHolder accountHolder1 = new AccountHolder("May Lord", "mylo@gmail.com",
				passwordEncoder.encode("C@r@1212"), LocalDate.of(1989, 3, 22),
				new Address("Anselm Clave 7", "Corbera de Llobregat", "08757"),
				new Address("Carrer Caceres 26", "Barcelona", "08021"));
		accountHolderRepository.save(accountHolder1);
		roleRepository.save(new Role("HOLDER", accountHolder1));
		AccountHolder accountHolder2 = new AccountHolder("Bill Adama", "galactica@gmail.com",
				passwordEncoder.encode("BillyBill"), LocalDate.of(1943, 10, 30),
				new Address("Palm Springs st 89", "Miami", "64532"),
				null);
		accountHolderRepository.save(accountHolder2);
		roleRepository.save(new Role("HOLDER", accountHolder2));
		AccountHolder accountHolder3 = new AccountHolder("Kara Thrace", "speddfire@gmail.com",
				passwordEncoder.encode("KyRaTo"), LocalDate.of(1975, 8, 2),
				new Address("Turette ave. 1265", "L.A.", "867-563"),
				null);
		accountHolderRepository.save(accountHolder3);
		roleRepository.save(new Role("HOLDER", accountHolder3));
		AccountHolder accountHolder4 = new AccountHolder("Thomson McThomson", "tommygun@gmail.com",
				passwordEncoder.encode("34443454G"), LocalDate.of(1998, 9, 2),
				new Address("Carrer Viviera", "La Calma", "23157"),
				new Address("Carrer Victoria", "Barcelona", "08032"));
		accountHolderRepository.save(accountHolder4);
		roleRepository.save(new Role("HOLDER", accountHolder4));
		AccountHolder accountHolder5 = new AccountHolder("Crystal Clair", "water@gmail.com",
				passwordEncoder.encode("ghulopata23"), LocalDate.of(1973, 11, 20),
				new Address("Buenaventura st 91", "Santa Carla", "68-654"),
				null);
		accountHolderRepository.save(accountHolder5);
		roleRepository.save(new Role("HOLDER", accountHolder5));
		AccountHolder accountHolder6 = new AccountHolder("Kant BeRight", "mrpunctual@gmail.com",
				passwordEncoder.encode("DEFEstado"), LocalDate.of(1935, 1, 24),
				new Address("Crisol ave. 365", "New York", "46266"),
				null);
		accountHolderRepository.save(accountHolder6);
		roleRepository.save(new Role("HOLDER", accountHolder6));
		accountHolderRepository.saveAll(List.of(accountHolder1, accountHolder2, accountHolder3, accountHolder4, accountHolder5,
				accountHolder6));   // Another way to do it, with other strong points

		Admin emilio = new Admin("Emilio Botín", "santender@gmail.com", passwordEncoder.encode("MiDiN3R0"));
		Admin barcenas = new Admin("Luis Bàrcenas", "ppsede@gmail.com", passwordEncoder.encode("SOBREtodo"));
		Admin titoGil = new Admin("Tio Gilito", "disney@gmail.com", passwordEncoder.encode("3patitos"));
		adminRepository.save(emilio);
		adminRepository.save(barcenas);
		adminRepository.save(titoGil);
		roleRepository.save(new Role("ADMIN", emilio));
		adminRepository.save(emilio);
		roleRepository.save(new Role("ADMIN", barcenas));
		adminRepository.save(barcenas);
		roleRepository.save(new Role("ADMIN", titoGil));
		adminRepository.save(titoGil);

		ThirdParty thirdParty1 = new ThirdParty("Zara .ORG", "ropita@gmail.com",
				passwordEncoder.encode("32234jhjfg"));
		thirdPartyRepository.save(thirdParty1);
		roleRepository.save(new Role("THIRD", thirdParty1));
		ThirdParty thirdParty2 = new ThirdParty("Aldi Inc.", "goodfood@gmail.com",
				passwordEncoder.encode("7687kjhgbk"));
		thirdPartyRepository.save(thirdParty2);
		roleRepository.save(new Role("THIRD", thirdParty2));
		ThirdParty thirdParty3 = new ThirdParty("Audi .s.a", "motorpeople@gmail.com",
				passwordEncoder.encode("jgfjugf76457"));
		thirdPartyRepository.save(thirdParty3);
		roleRepository.save(new Role("THIRD", thirdParty3));
		thirdPartyRepository.saveAll(List.of(thirdParty1, thirdParty2, thirdParty3));

		Checking checking1 = new Checking(new Money(BigDecimal.valueOf(1010.13)), accountHolder1, null);
		Checking checking2 = new Checking(new Money(BigDecimal.valueOf(23002.23)), accountHolder3, accountHolder2);
		Checking checking3 = new Checking(new Money(BigDecimal.valueOf(53346.34)), accountHolder5, accountHolder4);
		checkingRepository.saveAll(List.of(checking1, checking2, checking3));

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

		transferRepository.saveAll(List.of(
		new Transfer(checking1, "SushiGo.com", checking1.getPrimaryOwner(), BigDecimal.valueOf(134.23)),
		new Transfer(checking2, accountHolder4.getName(), checking2.getPrimaryOwner(), BigDecimal.valueOf(2650.26)),
		new Transfer(checking2, accountHolder3.getName(), checking2.getPrimaryOwner(), BigDecimal.valueOf(3230.00)),
				new Transfer(checking2, accountHolder6.getName(), checking2.getPrimaryOwner(), BigDecimal.valueOf(342.30))));
		Transfer transfer1 = new Transfer(checking3, accountHolder3.getName(), checking3.getPrimaryOwner(), BigDecimal.valueOf(3230.00));
		transfer1.setTransferDate(LocalDate.of(1973, 11, 20));
		transferRepository.save(transfer1);
		Transfer transfer2 = new Transfer(checking2, "Bar Tolo S.A.", checking2.getPrimaryOwner(), BigDecimal.valueOf(250.45));
		transfer2.setTransferDate(LocalDate.of(1973, 11, 20));
		transferRepository.save(transfer2);

		/* ---This next Transfer is created by the add method. Then checks if Fraud.
		Works, but I let it commented.----

		transferService.addTransfer(checking3, accountHolder3.getName(), checking3.getPrimaryOwner(), BigDecimal.valueOf(1200000.00));
		 */
	}
}


//TODO: SECURITY// cualquier user lo puede hacer todo

//TODO: tests, con la security?, me pedira password?





