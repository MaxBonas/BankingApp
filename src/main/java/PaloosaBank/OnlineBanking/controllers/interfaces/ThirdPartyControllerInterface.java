package PaloosaBank.OnlineBanking.controllers.interfaces;

import PaloosaBank.OnlineBanking.DTOs.PaymentTPGetDTO;

import java.math.BigDecimal;

public interface ThirdPartyControllerInterface {

    PaymentTPGetDTO patchThirdPartyAnyAccountBalance(Long accountId, BigDecimal amount, String hashkey);
}
