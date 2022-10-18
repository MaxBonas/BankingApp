package PaloosaBank.OnlineBanking.services.users;

import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Account;
import PaloosaBank.OnlineBanking.entities.users.ThirdParty;
import PaloosaBank.OnlineBanking.repositories.accounts.AccountRepository;
import PaloosaBank.OnlineBanking.repositories.users.ThirdPartyRepository;
import PaloosaBank.OnlineBanking.services.users.interfaces.ThirdPartyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ThirdPartyService implements ThirdPartyServiceInterface {

    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public ThirdParty addThirdParty(ThirdParty thirdParty) {
        if (thirdPartyRepository.findById(thirdParty.getId()).isPresent())
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "A Third Party with this id already exist.");
        return thirdPartyRepository.save(thirdParty);
    }

    @Override
    public ThirdParty getThirdPartyById(Long id) {
        return thirdPartyRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "A Third Party with the given id doesn't exist"));
    }

    @Override
    public List<ThirdParty> getAllThirdPartys() {
        return thirdPartyRepository.findAll();
    }

    @Override
    public ThirdParty updateThirdParty(Long id, ThirdParty thirdParty) {
        if (thirdPartyRepository.findById(id).isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This Third Party doesn't exist");

        return thirdPartyRepository.save(thirdParty);
    }

    @Override
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


}
