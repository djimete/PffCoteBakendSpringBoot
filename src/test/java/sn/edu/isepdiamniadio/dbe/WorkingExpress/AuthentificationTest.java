package sn.edu.isepdiamniadio.dbe.WorkingExpress;

import org.junit.jupiter.api.Test;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.dto.AuthRequest;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.dto.AuthResponse;

import static org.junit.jupiter.api.Assertions.*;
import static sn.edu.isepdiamniadio.dbe.WorkingExpress.Config.LOGIN_URL;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AuthentificationTest {
    private final Logger logger = LoggerFactory.getLogger(AuthentificationTest.class);


    @Autowired
    private TestRestTemplate restTemplate;
    @Test
    public void authentificationCorrect(){
        logger.info("###### test authentification avec un bon login et mot de passe");

        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail("djimou.meta081@gmail.com");
        authRequest.setMotDePasse("passer");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AuthRequest> request = new HttpEntity<>(authRequest,headers);

        ResponseEntity<AuthResponse> response = restTemplate.postForEntity(LOGIN_URL,request,AuthResponse.class);

        System.out.println("statut===="+response.getStatusCode());
        assertEquals(HttpStatus.OK,response.getStatusCode());
        AuthResponse result = response.getBody();
        System.out.println("token = "+result.getToken());

        assertNotNull(result.getToken());
        assertTrue(result.getToken().length()>0);
    }

    @Test
    public void authentificationLoginIncorrect(){
        logger.info("###### test authentification avec un bon login et mot de passe");

        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail("djimou.meta081@exemple.com");
        authRequest.setMotDePasse("passer");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AuthRequest> request = new HttpEntity<>(authRequest,headers);

        ResponseEntity<AuthResponse> response = restTemplate.postForEntity(LOGIN_URL,request,AuthResponse.class);

        System.out.println("statut===="+response.getStatusCode());
        assertEquals(444, response.getStatusCode().value());

        System.out.println("token="+response.getBody());
    }

    @Test
    public void authentificationMotDePasseIncorrect(){
        logger.info("###### test authentification avec un login  correct et mot de passe incorrect");

        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail("djimou.meta081@gmail.com");
        authRequest.setMotDePasse("passermeta");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AuthRequest> request = new HttpEntity<>(authRequest,headers);

        ResponseEntity<AuthResponse> response = restTemplate.postForEntity(LOGIN_URL,request,AuthResponse.class);

        System.out.println("statut===="+response.getStatusCode());
        assertEquals(444, response.getStatusCode().value());
    }

    @Test
    public void authentificationMotDePasseIncorrectEtLoginIncorrect(){
        logger.info("###### test authentification avec un login  incorrect et mot de passe incorrect");

        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail("djimou.meta081@exemple.com");
        authRequest.setMotDePasse("passermeta");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AuthRequest> request = new HttpEntity<>(authRequest,headers);

        ResponseEntity<AuthResponse> response = restTemplate.postForEntity(LOGIN_URL,request,AuthResponse.class);

        System.out.println("statut===="+response.getStatusCode());
        assertEquals(444, response.getStatusCode().value());
    }
}
