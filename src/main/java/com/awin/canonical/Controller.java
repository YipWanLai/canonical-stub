package com.awin.canonical;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {
    private CanonicalResponseTransformer transformer = new CanonicalResponseTransformer();

    @PostMapping(path = "/transaction/fetch", produces = "application/json")
    public ResponseEntity<String> post(@RequestParam List<Integer> transactionIds, @RequestParam int pageNumber) {
        if(transactionIds.contains(999)) {
            return ResponseEntity.status(404).build();
        }
        String content = transformer.prepareContent(transactionIds, pageNumber);
        return ResponseEntity.ok(content);
    }

    @PostMapping(path = "/authentication/fetchToken", produces = "application/json")
    public ResponseEntity<String> auth() {
        String response = "{\"status\":\"OK\",\"message\":\"The auth token was created.\",\"payload\":\"token\"}";
        return ResponseEntity.ok(response);
    }

}
