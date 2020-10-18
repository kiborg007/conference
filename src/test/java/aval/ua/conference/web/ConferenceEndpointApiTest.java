package aval.ua.conference.web;

import aval.ua.conference.api.dto.ConferenceResponse;
import aval.ua.conference.api.dto.TalkRequest;
import aval.ua.conference.domain.entity.Conference;
import aval.ua.conference.domain.entity.Talk;
import aval.ua.conference.domain.mapper.MapperDTO;
import aval.ua.conference.service.ConferenceService;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;

import java.sql.Date;

import static io.restassured.RestAssured.given;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class ConferenceEndpointApiTest extends AbstractEndpointApiTest{
    @Test
    /*@ExportDataSet(format = DataSetFormat.XML, outputName = "target/expert-added.xml",
                includeTables = {"expert", "recommendations"})*/
    public void expertCouldBeAddedWithRecommendations() {
        int id = given()
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .body("{\n" +
                        "  \"id\": \"1\",\n" +
                        "  \"type\": \"\",\n" +
                        "  \"name\": \"Talk 1\",\n" +
                        "  \"desc\": \"Desc Talk 1\",\n" +
                        "  \"person\": \"1\",\n" +
                        "}")
                .when()
                .post("/conferences/talks?conference_id=2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .extract().body().jsonPath().get("id");

        assertThat(id).isPositive();
    }

    @TestConfiguration
    public static class ConferenceTestContext {
        @Bean
        public CommandLineRunner conferenceInitializer(ConferenceService conferenceService, MapperDTO mapperDTO) {
            System.out.println("!!! Insert test data");
            Talk talk = new Talk();
            talk.setId(1L);
            talk.setName("Talk 1");
            talk.setType("");
            talk.setDesc("Desc Talk 1");
            talk.setPerson("1");

            TalkRequest talkRequest = new TalkRequest();
         //   talkRequest.setId(1L);
            talkRequest.setName("Talk 1");
            talkRequest.setType("");
            talkRequest.setDesc("Desc Talk 1");
            talkRequest.setPerson("1");

            Conference conference = new Conference();
            conference.setId(2L);
            conference.setDate(new Date(2020, 10, 10));
            conference.setTalks(asList(talk));

            ConferenceResponse conferenceRequest = new ConferenceResponse();
            conferenceRequest.setId(2L);
            conferenceRequest.setDate(new Date(2020, 10, 10));
            //conferenceRequest.setTalks_lst(asList(talkRequest));


            return (args) -> conferenceService.addTalk(2L, talk);
        }
    }
}
