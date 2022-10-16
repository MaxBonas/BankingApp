package PaloosaBank.OnlineBanking;

import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Checking;
import PaloosaBank.OnlineBanking.entities.accounts.CreditCard;
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
public class OnlineBankingApplication /*implements CommandLineRunner*/ {

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

//	@Override
//	public void run(String... args) throws Exception {
//
//		Checking checking1 = new Checking(new Money(BigDecimal.valueOf(1010)), null, null,
//				LocalDate.of(2012, 12, 12), null);
//		Checking checking2 = new Checking(new Money(BigDecimal.valueOf(23000)), null, null,
//				LocalDate.of(2038, 6, 8), null);
//		Checking checking3 = new Checking(new Money(BigDecimal.valueOf(53)), null, null,
//				LocalDate.of(1872, 2, 7), null);
//		CheckingRepository.saveAll(List.of(checking1, checking2, checking3));
//
//		CreditCardRepository.saveAll(List.of(
//				new CreditCard(new Money(BigDecimal.valueOf(2310)), null, null,
//						LocalDate.of(2012, 12, 12), null),
//				new CreditCard(new Money(BigDecimal.valueOf(1010)), null, null,
//						LocalDate.of(2012, 12, 12), null),
//				new CreditCard(new Money(BigDecimal.valueOf(1010)), null, null,
//						LocalDate.of(2012, 12, 12), null)));
//	}
}

