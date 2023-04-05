import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domain.Character;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;


@JsonIgnoreProperties(ignoreUnknown = true)


public class CharacterTest {

    @Test
    public void shouldReturnStatusCode200() {
        final Integer expectedCode = 200;
        HttpResponse response = getHttpResponse("https://rickandmortyapi.com/api/character/40");
        debug(response);
        Assertions.assertEquals(expectedCode, response.statusCode());
    }

    private void debug(HttpResponse<String> response) {
        System.out.println("Response code: " + response.statusCode());
        System.out.println("Response Body : " + response.body());
    }

    private HttpResponse getHttpResponse(String url) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(url))
                    .build();
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException();
        }
    }

    @Test
    public void ContentTestWithJson() throws IOException {

        final String expected = Files.readString(Path.of("src/test/resources/expectedCharacter.json"));
        System.out.println("-------------------");
        System.out.println(expected);
        System.out.println("-------------------");
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse response = getHttpResponse("https://rickandmortyapi.com/api/character/2");
        debug(response);
        Assertions.assertEquals(expected, response.body());
    }



    @Test
    public void contentTestWithObjects() throws JsonProcessingException {

        Character expected = Character.builder()
                .id(1L)
                .name("Rick Sanchez")
                .gender("Male")
                .type("")
                .status("Alive")
                .species("Human")
                .build();
        ObjectMapper mapper = new ObjectMapper();
        HttpResponse<String> response = getHttpResponse("https://rickandmortyapi.com/api/character/1");
        Character actual = mapper.readValue(response.body().toString(), Character.class);
        System.out.println(actual.toString());

        Assertions.assertEquals(expected, actual);
    }


}






//    @Test
//    public void ContentTestWithJson() throws IOException {
//
//        final String expected = Files.readString(Path.of("src/test/resources/expectedCharacter.json"));
//        System.out.println("-------------------");
//        System.out.println(expected);
//        System.out.println("-------------------");
//        HttpClient client = HttpClient.newHttpClient();
//        HttpResponse response = getHttpResponse("https://rickandmortyapi.com/api/character/2");
//        debug(response);
//        Assertions.assertEquals(expected, response.body());
//    }
