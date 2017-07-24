package com.corellidev.personalfinance;

import com.google.api.client.googleapis.apache.GoogleApacheHttpTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/**
 * Created by Kamil on 2017-07-12.
 */
@Component
public class TokenVerifier {

    private static final JacksonFactory jacksonFactory = new JacksonFactory();

    @Value("${google.client.id}")
    private String googleClientId;

    private GoogleIdTokenVerifier verifier;

    @PostConstruct
    public void init() {
        try {
            verifier = new GoogleIdTokenVerifier.Builder(GoogleApacheHttpTransport.newTrustedTransport(), jacksonFactory)
                    .setAudience(Arrays.asList(googleClientId))
                    .build();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String verifyAndGetEmail(String idTokenString) {
        try {
            if(verifier == null || idTokenString == null)
                return null;
            GoogleIdToken idToken = verifier.verify(idTokenString);
            if(idToken != null) {
                return idToken.getPayload().getEmail();
            }
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
