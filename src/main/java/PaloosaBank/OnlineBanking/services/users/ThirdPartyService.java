package PaloosaBank.OnlineBanking.services.users;

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
        return null;
    }
}
