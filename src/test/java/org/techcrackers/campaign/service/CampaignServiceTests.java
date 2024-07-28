package org.techcrackers.campaign.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.techcrackers.campaign.dao.Campaign;
import org.techcrackers.campaign.dao.Client;
import org.techcrackers.campaign.dto.CampaignDTO;
import org.techcrackers.campaign.exceptions.CampaignException;
import org.techcrackers.campaign.repo.CampaignRepository;
import org.techcrackers.campaign.repo.ClientRepository;
import org.techcrackers.campaign.service.impl.CampaignServiceImpl;

import java.util.Arrays;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CampaignServiceTests {

    @Mock
    private CampaignRepository campaignRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private EmailService emailService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CampaignServiceImpl campaignService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCampaign_ClientNotFound() {
        CampaignDTO campaignDTO = new CampaignDTO();
        campaignDTO.setClients(Arrays.asList(1L));

        Campaign campaign = new Campaign(null, null, null, "ACTIVE", null, null, null);

        when(clientRepository.findById(1L)).thenReturn(Optional.empty());
        when(modelMapper.map(campaignDTO, Campaign.class)).thenReturn(campaign);

        CampaignException exception = assertThrows(CampaignException.class, () -> campaignService.save(campaignDTO));

        assertEquals("1112", exception.getErrorMessage().getErrorCode());

    }

    @Test
    void testSendCampaign_Success() {
        Campaign campaign = new Campaign();
        campaign.setId(1L);

        when(campaignRepository.findById(1L)).thenReturn(Optional.of(campaign));

        campaignService.send(1L);

        verify(campaignRepository, times(1)).findById(1L);
        verify(emailService, times(1)).sendEmail(campaign);
    }

    @Test
    void testSendCampaign_CampaignNotFound() {
        when(campaignRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CampaignException.class, () -> campaignService.send(1L));

        verify(campaignRepository, times(1)).findById(1L);
        verify(emailService, times(0)).sendEmail(any(Campaign.class));
    }
}
