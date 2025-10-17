package sn.edu.isepdiamniadio.dbe.WorkingExpress;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Role;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.dto.AuthRequest;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.dto.AuthResponse;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static sn.edu.isepdiamniadio.dbe.WorkingExpress.Config.LOGIN_URL;
import static sn.edu.isepdiamniadio.dbe.WorkingExpress.Config.ROLES_URL;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class RoleTest {

    private final Logger logger = LoggerFactory.getLogger(RoleTest.class);
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public  void testListRole(){
        logger.info("test de récupération liste des roles");
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail("djimou.meta081@gmail.com");
        authRequest.setMotDePasse("passer");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AuthRequest> authRequestEntity = new HttpEntity<>(authRequest,headers);

        ResponseEntity<AuthResponse> authResponse = restTemplate.postForEntity(LOGIN_URL, authRequestEntity,AuthResponse.class);

        System.out.println("statut===="+ authResponse.getStatusCode());
        assertEquals(HttpStatus.OK, authResponse.getStatusCode());
        AuthResponse result = authResponse.getBody();
        System.out.println("token = "+result.getToken());

        assertNotNull(result.getToken());
        assertTrue(result.getToken().length()>0);

        String token = result.getToken();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        HttpEntity<AuthRequest> roleRequestEntity = new HttpEntity<>(headers);
        ResponseEntity<List<Role>> rolesListResponse = restTemplate.exchange(
                ROLES_URL,
                HttpMethod.GET,
                roleRequestEntity,
                new ParameterizedTypeReference<List<Role>>() {}
        );

        assertEquals(HttpStatus.OK,rolesListResponse.getStatusCode());
        List<Role> roles = rolesListResponse.getBody();
        assertFalse(roles.isEmpty());
        for(Role role:roles){
            logger.info("role trouvé {}",role.getNom());
        }



    }
}
