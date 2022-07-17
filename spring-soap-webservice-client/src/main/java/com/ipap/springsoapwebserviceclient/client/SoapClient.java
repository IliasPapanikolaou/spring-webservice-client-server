package com.ipap.springsoapwebserviceclient.client;

import com.ipap.springsoapwebserviceclient.generated.Acknowledgement;
import com.ipap.springsoapwebserviceclient.generated.CustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

@Service
public class SoapClient {

    private Jaxb2Marshaller marshaller;
    private WebServiceTemplate template;

    @Autowired
    public SoapClient(Jaxb2Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public Acknowledgement getLoanStatus(CustomerRequest request) {
        template = new WebServiceTemplate(marshaller);
        Acknowledgement acknowledgement = (Acknowledgement) template
                .marshalSendAndReceive("http://localhost:9999/ws", request);
        return acknowledgement;
    }

}
