package com.ipap.springsoapwebservice.service;

import com.ipap.springsoapwebservice.generated.Acknowledgement;
import com.ipap.springsoapwebservice.generated.CustomerRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanEligibilityService {

    public Acknowledgement checkEligibility(CustomerRequest request) {
        Acknowledgement acknowledgement = new Acknowledgement();
        List<String> mismatchCriteriaList = acknowledgement.getCriteriaMismatch();

        if (!(request.getAge() > 30 && request.getAge() <= 60)) {
            mismatchCriteriaList.add("Person age should be between 30 and 60.");
        }
        if (request.getYearlyIncome() < 18_000) {
            mismatchCriteriaList.add("Minimum income should be more than 18.000 per year.");
        }
        if (request.getCibilScore() < 500) {
            mismatchCriteriaList.add("Low CIBIL Score, please try again after 6 months.");
        }
        if (mismatchCriteriaList.size() > 0) {
            acknowledgement.setApprovedAmount(0);
            acknowledgement.setIsEligible(false);
        } else {
            acknowledgement.setApprovedAmount(40_000);
            acknowledgement.setIsEligible(true);
            mismatchCriteriaList.clear();
        }

        return acknowledgement;
    }
}
