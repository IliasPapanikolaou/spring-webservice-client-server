package com.ipap.springsoapwebservice.endpoint;

import com.ipap.springsoapwebservice.generated.Acknowledgement;
import com.ipap.springsoapwebservice.generated.CustomerRequest;
import com.ipap.springsoapwebservice.service.LoanEligibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/*
* Endpoint WSDL: localhost:9999/ws/loanEligibility.wsdl
*
Request Sample:
--------------
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:gen="http://ipap.com/springsoapwebservice/generated">
   <soapenv:Header/>
   <soapenv:Body>
      <gen:CustomerRequest>
         <gen:customerName>Ilias</gen:customerName>
         <gen:age>60</gen:age>
         <gen:yearlyIncome>18000</gen:yearlyIncome>
         <gen:cibilScore>500</gen:cibilScore>
         <gen:employmentMode>govt</gen:employmentMode>
      </gen:CustomerRequest>
   </soapenv:Body>
</soapenv:Envelope>
* */

@Endpoint
public class LoanEligibilityEndpoint {

    public static final String NAMESPACE="http://ipap.com/springsoapwebservice/generated";

    private LoanEligibilityService service;

    @Autowired
    public LoanEligibilityEndpoint(LoanEligibilityService service) {
        this.service = service;
    }

    // localPart = @XmlRootElement(name = "CustomerRequest")
    @PayloadRoot(namespace = NAMESPACE, localPart = "CustomerRequest")
    @ResponsePayload
    public Acknowledgement getLoanStatus(@RequestPayload CustomerRequest request) {
        return service.checkEligibility(request);
    }
}
