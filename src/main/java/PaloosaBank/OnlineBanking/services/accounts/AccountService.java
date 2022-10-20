package PaloosaBank.OnlineBanking.services.accounts;

import PaloosaBank.OnlineBanking.DTOs.accounts.PaymentTPGetDTO;
import PaloosaBank.OnlineBanking.DTOs.accounts.AccountPostDTO;
import PaloosaBank.OnlineBanking.DTOs.accounts.TransferGetDTO;
import PaloosaBank.OnlineBanking.DTOs.accounts.TransferPostDTO;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Account;
import PaloosaBank.OnlineBanking.entities.accounts.Checking;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.enums.Status;
import PaloosaBank.OnlineBanking.repositories.accounts.AccountRepository;
import PaloosaBank.OnlineBanking.repositories.users.AccountHolderRepository;
import PaloosaBank.OnlineBanking.repositories.users.ThirdPartyRepository;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.AccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService implements AccountServiceInterface {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Override
    public Account addAccount(AccountPostDTO account) {
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

        return accountRepository.save(new Checking(balance, accountHolder, accountHolder2));
    }

    @Override
    public Account deleteAccount(Long id) {
        Account account1 = accountRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "An Account with the given id doesn't exist"));
        accountRepository.delete(account1);
        return account1;
    }

    @Override
    public Account patchStatusAccount(Long id) {
        Account account1 = accountRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No Account with this id exist in the system."));

        if (account1.getStatus() == Status.ACTIVE){
            account1.setStatus(Status.FROZEN);
        }
        account1.setStatus(Status.ACTIVE);
        return accountRepository.save(account1);
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
    public PaymentTPGetDTO patchThirdPartyAnyAccountBalance(Long id, Money amount, String hashkey) {
        Account account1 = accountRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "No Account with this id exist in the system."));
        if (thirdPartyRepository.findByHashkey(hashkey).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "This Hashkey doesn't match with the system.");
        }
        if (account1.getBalance().getAmount().compareTo(amount.getAmount()) < 0) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "This Account don't have enough founds.");
        }

        account1.setBalance(new Money(account1.getBalance().decreaseAmount(amount.getAmount())));

//        if (account1.getClass() == Checking && account1.getBalance().getAmount().compareTo(account1.)) // TODO ????
        accountRepository.save(account1);
        return new PaymentTPGetDTO(account1.getId(), account1.getPrimaryOwner().getName(), amount.getAmount().doubleValue());
    }

    @Override
    public Account patchAdminAnyAccountBalance(Long id, Money amount) {
        Account account1 = accountRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "No Account with this id exist in the system."));

        account1.setBalance(amount);
        return accountRepository.save(account1);
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

        accountOut.setBalance(new Money(accountOut.getBalance().decreaseAmount(amount2.getAmount())));
        accountIn.setBalance(new Money(accountIn.getBalance().increaseAmount(amount2.getAmount())));

        TransferGetDTO transferGetDTO = new TransferGetDTO(accountOut.getPrimaryOwner().getName(),
                accountIn.getPrimaryOwner().getName(), amount2.getAmount());

//        LocalDate monthly1 = accountHolder.getDateOfBirth();
//        Period period = Period.between(birth1, LocalDate.now()); // TODO ????????
//        if (period.getYears() < 24) {
//            return studentsCheckingRepository.save(new StudentsChecking(balance, accountHolder, accountHolder2));
//        }

        accountRepository.save(accountOut);
        accountRepository.save(accountIn);
        return transferGetDTO;
    }

    @Override
    public BigDecimal getBalanceAccountAccountHolder(Long id) {
        Account account1 = accountRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "An Account with the given id doesn't exist"));
        return account1.getBalance().getAmount();
    }

}
