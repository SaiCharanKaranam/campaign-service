package org.techcrackers.campaign.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.techcrackers.campaign.dao.Campaign;
import org.techcrackers.campaign.dto.CampaignDTO;
import org.techcrackers.campaign.service.CampaignService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CampaignController.class)
public class CampaignControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CampaignService campaignService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSaveCampaign() throws Exception {
        CampaignDTO campaignDTO = new CampaignDTO();
        campaignDTO.setName("New Campaign");
        campaignDTO.setSubject("Campaign Subject");
        campaignDTO.setStatus("Draft");
        campaignDTO.setEmailBody("Email body content");
        campaignDTO.setSubscribers(List.of("subscriber1@example.com", "subscriber2@example.com"));

        Campaign savedCampaign = new Campaign();
        savedCampaign.setId(1L);
        savedCampaign.setName("New Campaign");
        savedCampaign.setSubject("Campaign Subject");
        savedCampaign.setStatus("Draft");
        savedCampaign.setEmailBody("Email body content");
        savedCampaign.setSubscribers(List.of("subscriber1@example.com", "subscriber2@example.com"));


        given(campaignService.save(any(CampaignDTO.class))).willReturn(savedCampaign);

        mockMvc.perform(post("/api/campaign")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(campaignDTO)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SUCCESS"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("New Campaign"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.subject").value("Campaign Subject"));
    }

    @Test
    public void testSendCampaign() throws Exception {
        doNothing().when(campaignService).send(anyLong());


        mockMvc.perform(post("/api/campaign/1/send")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SUCCESS"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").doesNotExist());
    }
}
