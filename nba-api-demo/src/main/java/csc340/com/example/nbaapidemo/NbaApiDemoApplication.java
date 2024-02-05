package csc340.com.example.nbaapidemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class NbaApiDemoApplication {

	public static void main(String[] args) {
		getTeamInfo();
	}

        public static void getTeamInfo() {
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter a number between 1-30 to learn more about "
                + "that NBA team: ");
            String id = input.nextLine();
            
            String url = "https://www.balldontlie.io/api/v1/teams/" + id;
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jSonFact = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonFact);

            String fullName = root.findValue("full_name").asText();
            String city = root.findValue("city").asText();
            String conference = root.findValue("conference").asText();
            String division = root.findValue("division").asText();
            
            System.out.println("\n*** NBA Team Information ***\n");
            System.out.println("Name: " + fullName);
            System.out.println("City: " + city);
            System.out.println("Conference: " + conference);
            System.out.println("Division: " + division);

        } catch (JsonProcessingException ex) {
            Logger.getLogger(NbaApiDemoApplication.class.getName()).log(
                    Level.SEVERE,
                    null, ex);
            System.out.println("error in nba info");

        }
    }
}
