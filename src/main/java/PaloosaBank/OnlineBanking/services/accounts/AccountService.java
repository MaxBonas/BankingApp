package PaloosaBank.OnlineBanking.services.accounts;

import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Account;
import PaloosaBank.OnlineBanking.repositories.accounts.AccountRepository;
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

    @Override
    public Account getAccountById(Long id) {  // todo. No entiendo fallo. Pero... hace falta?
//        if (accountRepository.findById(account.getId()).isPresent())
//            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
//                    "An Account with this id already exist.");
//        return accountRepository.save(account);
        return null;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll(); //TODO hace falta hacer estos metodos en las clases abstract?
    }

    @Override  // todo SI SE REPITEN ENTRE ACCOUNT, THIRDPARTY Y ADMIN PETA
    public Account patchThirdPartyAnyAccountBalance(Long id, Money balance, String hashkey) {
        Account account1 = accountRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
//        //Todo. hace falta tener en cuenta si el pago es en otra currency? con un if?
        if (thirdPartyRepository.findByHashkey(hashkey).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "This Hashkey doesn't match with the system.");
        }
        if (account1.getBalance().getAmount().compareTo(balance.getAmount()) < 0) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "This Account don't have enough founds.");
        }

        account1.setBalance(new Money(account1.getBalance().decreaseAmount(balance.getAmount())));
        return accountRepository.save(account1);
    }

    @Override  // todo SI SE REPITEN ENTRE ACCOUNT, THIRDPARTY Y ADMIN PETA
    public Account patchAdminAnyAccountBalance(Long id, Money balance) {
        Account account1 = accountRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
//        //Todo. hace falta tener en cuenta si el pago es en otra currency? con un if?
        if (account1.getBalance().getAmount().compareTo(balance.getAmount()) < 0) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "This Account don't have enough founds.");
        }

        account1.setBalance(new Money(account1.getBalance().decreaseAmount(balance.getAmount())));
        return accountRepository.save(account1);
    }
}
