package aval.ua.conference.web;

import aval.ua.conference.api.dto.ConferenceRequest;
import aval.ua.conference.api.dto.TalkRequest;
import com.github.database.rider.core.api.dataset.DataSet;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.apache.http.HttpStatus;
import  com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class ConferenceControllerApiTest extends AbstractEndpointApiTest {
    @Autowired
    private ObjectMapper om;

    @Test
    @DataSet("all-conferences.yml")
    public void getAllConferences() {
        List<String> result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .get("/conferences")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .extract().body().jsonPath().getList("name");
        assertThat(result).hasSize(2).contains("name2");
    }

    @Test
    @SneakyThrows
    @DataSet("all-conferences.yml")
    public void addConferenceIsHttpStatusOK() {
        ConferenceRequest cr = new ConferenceRequest();
        cr.setName("name new");
        String body = om.writeValueAsString(cr);
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(body)
                .post("/conferences")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    @Test
    @SneakyThrows
    @DataSet("all-conferences.yml")
    public void ConferenceNameAlreadyExist() {
        ConferenceRequest cr = new ConferenceRequest();
        cr.setName("name1");
        String body = om.writeValueAsString(cr);
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(body)
                .post("/conferences")
                .then()
                .statusCode(HttpStatus.SC_CONFLICT)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    @Test
    @SneakyThrows
    @DataSet("all-conferences.yml")
    public void addNewTalk() {
        TalkRequest talk = new TalkRequest();
        talk.setName("new");
        String body = om.writeValueAsString(talk);
        String result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(body)
                .post("/conferences/2/talks")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .extract().body().jsonPath().get("name");
        assertThat(result).isEqualTo("name2");
    }
}
