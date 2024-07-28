package org.techcrackers.campaign.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.techcrackers.campaign.dao.Campaign;
import org.techcrackers.campaign.service.ClientService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
public class ClientControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    private List<Campaign> campaigns;

    @BeforeEach
    void setUp() {
        campaigns = new ArrayList<>();
        Campaign campaign1 = new Campaign();
        campaign1.setId(1L);
        campaign1.setName("Campaign 1");

        Campaign campaign2 = new Campaign();
        campaign2.setId(2L);
        campaign2.setName("Campaign 2");

        campaigns.add(campaign1);
        campaigns.add(campaign2);
    }

    @Test
    public void testGetCampaigns() throws Exception {
        // Mock the service call
        List<Campaign> mockCampaigns = Arrays.asList(
                new Campaign(1L, "Campaign 1", "Subject 1", "Active", "Email body 1", Arrays.asList("subscriber1@example.com"), new ArrayList<>()),
                new Campaign(2L, "Campaign 2", "Subject 2", "Inactive", "Email body 2", Arrays.asList("subscriber2@example.com"), new ArrayList<>())
        );

        when(clientService.get(1L)).thenReturn(mockCampaigns);

        // Perform the GET request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.get("/api/client/campaign/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"status\":\"SUCCESS\",\"errorMessage\":null,\"data\":[{\"id\":1,\"name\":\"Campaign 1\",\"subject\":\"Subject 1\",\"status\":\"Active\",\"emailBody\":\"Email body 1\",\"subscribers\":[\"subscriber1@example.com\"]},{\"id\":2,\"name\":\"Campaign 2\",\"subject\":\"Subject 2\",\"status\":\"Inactive\",\"emailBody\":\"Email body 2\",\"subscribers\":[\"subscriber2@example.com\"]}]}"));
    }
}
