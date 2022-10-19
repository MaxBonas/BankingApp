package PaloosaBank.OnlineBanking.services.users;

import PaloosaBank.OnlineBanking.DTOs.accounts.TransferDTO;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Account;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.repositoriesTest.accounts.AccountRepository;
import PaloosaBank.OnlineBanking.repositoriesTest.users.AccountHolderRepository;
import PaloosaBank.OnlineBanking.services.users.interfaces.AccountHolderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AccountHolderService implements AccountHolderServiceInterface {

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public AccountHolder addAccountHolder(AccountHolder accountHolder) {
        if (accountHolderRepository.findById(accountHolder.getId()).isPresent())
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "An Account Holder with this id already exist.");
        return accountHolderRepository.save(accountHolder);
    }

    @Override
    public AccountHolder getAccountHolderById(Long id) {
        return accountHolderRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "A Account Holder with the given id doesn't exist"));
    }

    @Override
    public List<AccountHolder> getAllAccountHolders() {
        return accountHolderRepository.findAll();
    }

    @Override
    public AccountHolder updateAccountHolder(Long id, AccountHolder accountHolder) {
        if (accountHolderRepository.findById(id).isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This Account Holder doesn't exist");

        return accountHolderRepository.save(accountHolder);
    }

    @Override
    public TransferDTO transferAccountHolderAnyAccount(Long accountOutId, Long accountInId, Money balance, String secretKey) {
        Account accountOut = accountRepository.findById(accountOutId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "The Account Id of the Sender doesn't exist."));
        Account accountIn = accountRepository.findById(accountInId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "The Account Id of the Receiver doesn't exist."));
//        //Todo. hace falta tener en cuenta si el pago es en otra currency? con un if?
        if (accountRepository.findBySecretKey(secretKey).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The password doesn't match with the system.");
        }
        if (accountOut.getBalance().getAmount().compareTo(balance.getAmount()) < 0) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "This Account don't have enough founds.");
        }

        accountOut.setBalance(new Money(accountOut.getBalance().decreaseAmount(balance.getAmount())));
        accountIn.setBalance(new Money(accountIn.getBalance().increaseAmount(balance.getAmount())));
        accountRepository.saveAll(List.of(accountOut, accountIn));
        TransferDTO transferDTO = new TransferDTO(accountOut.getPrimaryOwner().getName(),
                accountIn.getPrimaryOwner().getName(), balance.getAmount());
        return transferDTO;
    }

}
