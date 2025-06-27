package logging.logger.endpoints;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import logging.logger.utility.Printer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private Printer printer;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl(String path) {
        return "http://localhost:" + port + path;
    }

    @Test
    void testInfo() {
        // Send 3 info logs
        printer.printDouble();
        printer.printtest(1);
        printer.printDouble();
        for (int i = 1; i <= 3; i++) {
            String url = getBaseUrl("/log/info");
            String json = String.format("{\"message\":\"test info log%d passed\"}", i);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(json, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
            assertThat(response.getBody()).isEqualTo("Passed");
        }

        // Check mapping after 3 info logs
        String mappingUrl = getBaseUrl("/log/mapping");
        ResponseEntity<String> mappingResponse1 = restTemplate.exchange(mappingUrl, HttpMethod.PUT, HttpEntity.EMPTY,
                String.class);
        assertThat(mappingResponse1.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        System.out.println("Mapping after 3 info logs: " + mappingResponse1.getBody());
        String infoUrl = getBaseUrl("/log/info");
        String infoJson = "{\"message\":\"test info log4 passed\"}";
        HttpHeaders infoHeaders = new HttpHeaders();
        infoHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> infoEntity = new HttpEntity<>(infoJson, infoHeaders);
        ResponseEntity<String> infoResponse = restTemplate.postForEntity(infoUrl, infoEntity, String.class);
        assertThat(infoResponse.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        assertThat(infoResponse.getBody()).isEqualTo("Passed");

        // Check mapping after 4th info log
        ResponseEntity<String> mappingResponse2 = restTemplate.exchange(mappingUrl, HttpMethod.PUT, HttpEntity.EMPTY,
                String.class);
        assertThat(mappingResponse2.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        System.out.println("Mapping after 4th info log: " + mappingResponse2.getBody());
    }

    @Test
    void testWarn() {
        printer.printDouble();
        printer.printtest(2);
        printer.printDouble();
        String url = getBaseUrl("/log/warn");
        String json = "{\"message\":\"test warn log1 passed \"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        assertThat(response.getBody()).isEqualTo("Passed");
    }

    @Test
    void testError() {
        printer.printDouble();
        printer.printtest(3);
        printer.printDouble();
        String url = getBaseUrl("/log/error");
        String json = "{\"message\":\"test error log2 passed\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        assertThat(response.getBody()).isEqualTo("Passed");
    }

    @Test
    void testMapping() {
        printer.printDouble();
        printer.printtest(4);
        printer.printDouble();
        String url = getBaseUrl("/log/mapping");
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, HttpEntity.EMPTY, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        assertThat(response.getBody()).isEqualTo("Passed");
    }
}