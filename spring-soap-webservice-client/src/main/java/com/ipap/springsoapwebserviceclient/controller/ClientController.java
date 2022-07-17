package com.ipap.springsoapwebserviceclient.controller;

import com.ipap.springsoapwebserviceclient.client.SoapClient;
import com.ipap.springsoapwebserviceclient.generated.Acknowledgement;
import com.ipap.springsoapwebserviceclient.generated.CustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/*
Request sample:
--------------
{
    "customerName":"John"
    "age": 35
    "yearlyIncome": 20000
    "cibilScore": 500
    "employmentMode": "something"
}
 */

@RestController
@RequestMapping("/api")
public class ClientController {

    private SoapClient client;

    @Autowired
    public ClientController(SoapClient client) {
        this.client = client;
    }

    @PostMapping("/getLoanStatus")
    public ResponseEntity<Acknowledgement> invokeSoapClinet(@RequestBody CustomerRequest request) {
        return ResponseEntity.ok(client.getLoanStatus(request));
    }
}
