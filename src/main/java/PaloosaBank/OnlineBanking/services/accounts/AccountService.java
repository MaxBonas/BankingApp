package PaloosaBank.OnlineBanking.services.accounts;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountPostDTO;
import PaloosaBank.OnlineBanking.DTOs.accounts.TransferDTO;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Account;
import PaloosaBank.OnlineBanking.entities.accounts.Checking;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.repositoriesTest.accounts.AccountRepository;
import PaloosaBank.OnlineBanking.repositoriesTest.users.AccountHolderRepository;
import PaloosaBank.OnlineBanking.repositoriesTest.users.ThirdPartyRepository;
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
    public Account patchThirdPartyAnyAccountBalance(Long id, Money balance, String hashkey) {
        Account account1 = accountRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "No Account with this id exist in the system."));
        if (thirdPartyRepository.findByHashkey(hashkey).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "This Hashkey doesn't match with the system.");
        }
        if (account1.getBalance().getAmount().compareTo(balance.getAmount()) < 0) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "This Account don't have enough founds.");
        }

        account1.setBalance(new Money(account1.getBalance().decreaseAmount(balance.getAmount())));
        return accountRepository.save(account1);
    }

    @Override
    public Account patchAdminAnyAccountBalance(Long id, Money balance) {
        Account account1 = accountRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "No Account with this id exist in the system."));

        account1.setBalance(balance);
        return accountRepository.save(account1);
    }

    @Override
    public TransferDTO transferAccountHolderAnyAccount(Long accountOutId, Long accountInId, Money balance, String secretKey) {
        Account accountOut = accountRepository.findById(accountOutId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "The Account Id of the Sender doesn't exist."));
        Account accountIn = accountRepository.findById(accountInId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "The Account Id of the Receiver doesn't exist."));
        if (accountRepository.findBySecretKey(secretKey).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The password doesn't match with the system.");
        }
        if (accountOut.getBalance().getAmount().compareTo(balance.getAmount()) < 0) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "This Account don't have enough founds.");
        }

        accountOut.setBalance(new Money(accountOut.getBalance().decreaseAmount(balance.getAmount())));
        accountIn.setBalance(new Money(accountIn.getBalance().increaseAmount(balance.getAmount())));

        TransferDTO transferDTO = new TransferDTO(accountOut.getPrimaryOwner().getName(),
                accountIn.getPrimaryOwner().getName(), balance.getAmount());

        accountRepository.save(accountOut);
        accountRepository.save(accountIn);
        return transferDTO;
    }
}
