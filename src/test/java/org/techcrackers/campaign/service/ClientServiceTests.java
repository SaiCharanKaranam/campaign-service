package org.techcrackers.campaign.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.techcrackers.campaign.dao.Campaign;
import org.techcrackers.campaign.dao.Client;
import org.techcrackers.campaign.exceptions.CampaignException;
import org.techcrackers.campaign.repo.ClientRepository;
import org.techcrackers.campaign.service.impl.ClientServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTests {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    private Client client;
    private List<Campaign> campaigns;

    @BeforeEach
    public void setup() {
        campaigns = new ArrayList<>();
        campaigns.add(new Campaign()); // Add a sample campaign

        client = new Client();
        client.setId(1L);
        client.setName("John Doe");
        client.setEmail("john.doe@example.com");
        client.setCampaigns(campaigns);
    }

    @Test
    public void testGetClientCampaigns_Success() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        List<Campaign> result = clientService.get(1L);

        assertEquals(1, result.size());
        assertEquals(campaigns, result);
    }


    @Test
    public void testGetClientCampaigns_ClientNotFound() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        CampaignException exception = assertThrows(CampaignException.class, () -> {
            clientService.get(1L);
        });
        assertEquals("1112", exception.getErrorMessage().getErrorCode());
    }

    @Test
    public void testGetClientCampaigns_NoCampaigns() {
        client.setCampaigns(new ArrayList<>());
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        List<Campaign> result = clientService.get(1L);

        assertEquals(0, result.size());
    }

    @Test
    public void testGetClientCampaigns_MultipleCampaigns() {
        campaigns.add(new Campaign());
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        List<Campaign> result = clientService.get(1L);

        assertEquals(2, result.size());
        assertEquals(campaigns, result);
    }

    @Test
    public void testGetClientCampaigns_DifferentClientId() {
        Client differentClient = new Client();
        differentClient.setId(2L);
        differentClient.setName("Jane Doe");
        differentClient.setEmail("jane.doe@example.com");
        differentClient.setCampaigns(campaigns);

        when(clientRepository.findById(2L)).thenReturn(Optional.of(differentClient));

        List<Campaign> result = clientService.get(2L);

        assertEquals(1, result.size());
        assertEquals(campaigns, result);
    }


}
