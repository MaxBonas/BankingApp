package PaloosaBank.OnlineBanking.services.accounts;

import PaloosaBank.OnlineBanking.DTOs.accounts.*;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.*;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.entities.users.Admin;
import PaloosaBank.OnlineBanking.enums.Status;
import PaloosaBank.OnlineBanking.enums.TypeAccount;
import PaloosaBank.OnlineBanking.repositories.TransferRepository;
import PaloosaBank.OnlineBanking.repositories.accounts.*;
import PaloosaBank.OnlineBanking.repositories.users.AccountHolderRepository;
import PaloosaBank.OnlineBanking.repositories.users.ThirdPartyRepository;
import PaloosaBank.OnlineBanking.services.TransferService;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.AccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class AccountService implements AccountServiceInterface {

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
    CheckingService checkingService;
    @Autowired
    CreditCardService creditCardService;
    @Autowired
    SavingsService savingsService;
    @Autowired
    StudentsCheckingService studentsCheckingService;
    @Autowired
    ThirdPartyRepository thirdPartyRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    TransferRepository transferRepository;
    @Autowired
    TransferService transferService;

    @Override
    public void addAccountByHolder(TypeAccount typeAccount, AccountPostDTO account) {
        AccountHolder accountHolder = accountHolderRepository.findById(account.getPrimaryOwnerId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "An Account Holder with the given id doesn't exist"));
        AccountHolder accountHolder2 = null;
        if (account.getSecondaryOwnerId() != null) {
            accountHolder2 = accountHolderRepository.findById(account.getSecondaryOwnerId()).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "An Account Holder with the given id doesn't exist"));
        }
        Money balance = new Money(BigDecimal.valueOf(account.getBalance()));
        if (typeAccount == TypeAccount.CHECKING) {
            Money balance1 = new Money(BigDecimal.valueOf(account.getBalance()));
            LocalDate birth1 = accountHolder.getDateOfBirth();
            Period period = Period.between(birth1, LocalDate.now());
            if (period.getYears() < 24) {
                StudentsChecking studentsChecking = new StudentsChecking(balance1, accountHolder, accountHolder2);
                studentsChecking.setStatus(Status.INACTIVE);
                studentsCheckingRepository.save(studentsChecking);
            }

            Checking checking1 = new Checking(balance, accountHolder, accountHolder2);
            checking1.setStatus(Status.INACTIVE);
            checking1.setMinimumBalance(new Money(BigDecimal.valueOf(account.getMinimumBalance())));
            checking1.setMonthlyMaintenanceFee(new Money(BigDecimal.valueOf(account.getMonthlyFee())));
            checkingRepository.save(checking1);
        }
        if (typeAccount == TypeAccount.CREDITCARD) {
            Money balance2 = new Money(BigDecimal.valueOf(account.getBalance()));
            CreditCard creditCard1 = new CreditCard(balance2, accountHolder, accountHolder2);
            creditCard1.setStatus(Status.INACTIVE);
            creditCard1.setCreditLimit(new Money(BigDecimal.valueOf(account.getCreditLimit())));
            creditCard1.setInterestRate(account.getInterestRate());
            creditCardRepository.save(creditCard1);
        }
        if (typeAccount == TypeAccount.SAVINGS) {
            Money balance3 = new Money(BigDecimal.valueOf(account.getBalance()));
            Savings savings1 = new Savings(balance3, accountHolder, accountHolder2);
            savings1.setStatus(Status.INACTIVE);
            savings1.setMinimumBalance(new Money(BigDecimal.valueOf(account.getMinimumBalance())));
            savings1.setInterestRate(account.getInterestRate());
            savingsRepository.save(savings1);
        }
        if (typeAccount == TypeAccount.STUDENT_CHECKING) {
            Money balance4 = new Money(BigDecimal.valueOf(account.getBalance()));
            StudentsChecking studentsChecking = new StudentsChecking(balance4, accountHolder, accountHolder2);
            studentsChecking.setStatus(Status.INACTIVE);
            studentsCheckingRepository.save(studentsChecking);
        }
    }

    @Override
    public AccountPostDTO addAccountByAdmin(TypeAccount typeAccount, AccountPostDTO account) {
        AccountHolder accountHolder = accountHolderRepository.findById(account.getPrimaryOwnerId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "An Account Holder with the given id doesn't exist"));
        AccountHolder accountHolder2 = null;
        if (account.getSecondaryOwnerId() != null) {
            accountHolder2 = accountHolderRepository.findById(account.getSecondaryOwnerId()).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "An Account Holder with the given id doesn't exist"));
        }
        Money balance = new Money(BigDecimal.valueOf(account.getBalance()));
        if (typeAccount == TypeAccount.CHECKING) {
            Money balance1 = new Money(BigDecimal.valueOf(account.getBalance()));
            LocalDate birth1 = accountHolder.getDateOfBirth();
            Period period = Period.between(birth1, LocalDate.now());
            if (period.getYears() < 24) {
                studentsCheckingRepository.save(new StudentsChecking(balance1, accountHolder, accountHolder2));
            }

            Checking checking1 = new Checking(balance, accountHolder, accountHolder2);
            checking1.setMinimumBalance(new Money(BigDecimal.valueOf(account.getMinimumBalance())));
            checking1.setMonthlyMaintenanceFee(new Money(BigDecimal.valueOf(account.getMonthlyFee())));
            checkingRepository.save(checking1);
        }
        if (typeAccount == TypeAccount.CREDITCARD) {
            Money balance2 = new Money(BigDecimal.valueOf(account.getBalance()));
            CreditCard creditCard1 = new CreditCard(balance2, accountHolder, accountHolder2);
            creditCard1.setCreditLimit(new Money(BigDecimal.valueOf(account.getCreditLimit())));
            creditCard1.setInterestRate(account.getInterestRate());
            creditCardRepository.save(creditCard1);
        }
        if (typeAccount == TypeAccount.SAVINGS) {
            Money balance3 = new Money(BigDecimal.valueOf(account.getBalance()));
            Savings savings1 = new Savings(balance3, accountHolder, accountHolder2);
            savings1.setMinimumBalance(new Money(BigDecimal.valueOf(account.getMinimumBalance())));
            savings1.setInterestRate(account.getInterestRate());
            savingsRepository.save(savings1);
        }
        if (typeAccount == TypeAccount.STUDENT_CHECKING) {
            Money balance4 = new Money(BigDecimal.valueOf(account.getBalance()));
            studentsCheckingRepository.save(new StudentsChecking(balance4, accountHolder, accountHolder2));
        }

        return account;
    }

    @Override
    public String deleteAccount(Long id) {
        Account account1 = accountRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "An Account with the given id doesn't exist"));
        accountRepository.delete(account1);
        return "The Account with id " + id + " has been removed correctly.";
    }

    @Override
    public AccountGetDTO patchStatusAccount(Long id) {
        Account account1 = accountRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No Account with this id exist in the system."));
        if (account1.getStatus() == Status.FROZEN) {
            account1.setStatus(Status.ACTIVE);
            AccountGetDTO account2 = new AccountGetDTO(account1.getId(), account1.getPrimaryOwner().getName(),
                    account1.getBalance().getAmount().doubleValue(), account1.getStatus(), account1.getCreationDate());
            accountRepository.save(account1);
            return account2;
        }
        if (account1.getStatus() == Status.ACTIVE) {
            account1.setStatus(Status.FROZEN);
            AccountGetDTO account2 = new AccountGetDTO(account1.getId(), account1.getPrimaryOwner().getName(),
                    account1.getBalance().getAmount().doubleValue(), account1.getStatus(), account1.getCreationDate());
            accountRepository.save(account1);
            return account2;
        }
        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                "This Account is not activated yet. Please, Validate it first.");
    }

    @Override
    public AccountGetDTO validateAndActivateAccount(Long id) {
        Account account1 = accountRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No Account with this id exist in the system."));

        if (account1.getStatus() == Status.ACTIVE || account1.getStatus() == Status.FROZEN) {

            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    "This Account is not an Inactive Account.");
        }
        account1.setStatus(Status.ACTIVE);
            AccountGetDTO account2 = new AccountGetDTO(account1.getId(), account1.getPrimaryOwner().getName(),
                    account1.getBalance().getAmount().doubleValue(), account1.getStatus(), account1.getCreationDate());
            accountRepository.save(account1);
            return account2;
    }

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "An Account with the given id doesn't exist"));
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public AccountGetDTO updateAccountByAdmin(Long id, AccountPostDTO account) {
        if (accountRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This Account doesn't exist");
        }
        AccountHolder accountHolder = accountHolderRepository.findById(account.getPrimaryOwnerId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "An Account Holder with the given id doesn't exist"));
        AccountHolder accountHolder2 = null;
        if (account.getSecondaryOwnerId() != null) {
            accountHolder2 = accountHolderRepository.findById(account.getSecondaryOwnerId()).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "An Account Holder with the given id doesn't exist"));
        }
        Money balance = new Money(BigDecimal.valueOf(account.getBalance()));
        Account account1 = accountRepository.findById(id).get();
        if (account1 instanceof Checking) {
            Money balance1 = new Money(BigDecimal.valueOf(account.getBalance()));
            LocalDate birth1 = accountHolder.getDateOfBirth();
            Period period = Period.between(birth1, LocalDate.now());
            if (period.getYears() < 24) {
                StudentsChecking studentsChecking1 = new StudentsChecking(balance1, accountHolder, accountHolder2);
                studentsChecking1.setId(id);
                studentsCheckingRepository.save(studentsChecking1);
            }

            Checking checking1 = new Checking(balance, accountHolder, accountHolder2);
            checking1.setMinimumBalance(new Money(BigDecimal.valueOf(account.getMinimumBalance())));
            checking1.setMonthlyMaintenanceFee(new Money(BigDecimal.valueOf(account.getMonthlyFee())));
            checking1.setId(id);
            checkingRepository.save(checking1);
        }
        if (account1 instanceof CreditCard) {
            Money balance2 = new Money(BigDecimal.valueOf(account.getBalance()));
            CreditCard creditCard1 = new CreditCard(balance2, accountHolder, accountHolder2);
            creditCard1.setCreditLimit(new Money(BigDecimal.valueOf(account.getCreditLimit())));
            creditCard1.setInterestRate(account.getInterestRate());
            creditCard1.setId(id);
            creditCardRepository.save(creditCard1);
        }
        if (account1 instanceof Savings) {
            Money balance3 = new Money(BigDecimal.valueOf(account.getBalance()));
            Savings savings1 = new Savings(balance3, accountHolder, accountHolder2);
            savings1.setMinimumBalance(new Money(BigDecimal.valueOf(account.getMinimumBalance())));
            savings1.setInterestRate(account.getInterestRate());
            savings1.setId(id);
            savingsRepository.save(savings1);
        }
        if (account1 instanceof StudentsChecking) {
            Money balance4 = new Money(BigDecimal.valueOf(account.getBalance()));
            StudentsChecking studentsChecking1 = new StudentsChecking(balance4, accountHolder, accountHolder2);
            studentsChecking1.setId(id);
            studentsCheckingRepository.save(studentsChecking1);
        }

        return new AccountGetDTO(account1.getId(), accountHolderRepository.findById(account.getPrimaryOwnerId()).get().getName(),
                account.getBalance(), account1.getStatus(), account1.getCreationDate());
    }

    @Override
    public Account patchAdminAnyAccountBalance(Long id, Money amount) {
        Account account1 = accountRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No Account with this id exist in the system."));

        account1.setBalance(amount);
        return accountRepository.save(account1);
    }

    @Override
    public BigDecimal getBalanceAccountAccountHolder (Long id, String secretKey){
        Account account1 = accountRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "An Account with the given id doesn't exist"));
        if (accountRepository.findBySecretKey(secretKey).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The password doesn't match with the system.");
        }
        return account1.getBalance().getAmount();
    }

    @Override
    public PaymentTPGetDTO patchThirdPartyAnyAccountBalance(Long id, Money amount, String hashkey) {

        Account account1 = accountRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "No Account with this id exist in the system."));
        if (thirdPartyRepository.findByHashkey(hashkey).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "This Hashkey doesn't match with the system.");
        }
        if (account1.getBalance().getAmount().compareTo(amount.getAmount()) < 0) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "This Account don't have enough founds.");
        }
        if (account1.getStatus().equals(Status.FROZEN)) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    "This Account is Frozen. Contact with the receiver or try another Account.");
        }
        if (account1.getStatus().equals(Status.INACTIVE)) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    "This Account is not activated yet. Contact with the receiver or try another Account");
        }
            transferService.addTransfer(account1, thirdPartyRepository.findByHashkey(hashkey).get().getName(),
                    account1.getPrimaryOwner(), amount.getAmount());

            account1.setBalance(new Money(account1.getBalance().getAmount().subtract(amount.getAmount())));
            accountRepository.save(account1);
            return new PaymentTPGetDTO(account1.getId(), account1.getPrimaryOwner().getName(), amount.getAmount().doubleValue());
        }

    @Override
    public TransferGetDTO transferAccountHolderAnyAccount(TransferPostDTO transferPostDTO) {
        Money amount2 = new Money(transferPostDTO.getAmount());
        Account accountOut = accountRepository.findById(transferPostDTO.getAccountOutId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "The Account Id of the Sender doesn't exist."));
        Account accountIn = accountRepository.findById(transferPostDTO.getAccountInId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "The Account Id of the Receiver doesn't exist."));
        if (accountRepository.findBySecretKey(transferPostDTO.getSecretKey()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The password doesn't match with the system.");
        }
        if (accountOut.getBalance().getAmount().compareTo(amount2.getAmount()) < 0) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "This Account don't have enough founds.");
        }
        if (accountOut.getStatus().equals(Status.FROZEN)) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    "Your Account is Frozen. Contact with your PaloosaBank Office");
        }
        if (accountIn.getStatus().equals(Status.FROZEN)) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    "The Receiver Account is Frozen. Contact with the receiver or try another Account.");
        }
        if (accountOut.getStatus().equals(Status.INACTIVE)) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    "Your Account is not activated yet. Contact with your PaloosaBank Office");
        }
        if (accountIn.getStatus().equals(Status.INACTIVE)) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    "The Receiver Account is not active yet. Contact with the receiver or try another Account.");
        }

        transferService.addTransfer(accountOut, accountIn.getPrimaryOwner().getName(), accountOut.getPrimaryOwner(), transferPostDTO.getAmount());

        accountOut.setBalance(new Money(accountOut.getBalance().getAmount().subtract(amount2.getAmount())));
        accountIn.setBalance(new Money(accountIn.getBalance().getAmount().add(amount2.getAmount())));

        TransferGetDTO transferGetDTO = new TransferGetDTO(accountOut.getPrimaryOwner().getName(),
                    accountIn.getPrimaryOwner().getName(), amount2.getAmount());
        accountRepository.save(accountOut);
        accountRepository.save(accountIn);
        return transferGetDTO;
    }
}
