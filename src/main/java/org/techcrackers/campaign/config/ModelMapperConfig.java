package org.techcrackers.campaign.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.techcrackers.campaign.dao.Campaign;
import org.techcrackers.campaign.dto.CampaignDTO;

/**
 * Configuration class for setting up ModelMapper bean.
 */
@Configuration
public class ModelMapperConfig {


    /**
     * Configures and returns a ModelMapper bean.
     *
     * @return a configured ModelMapper bean.
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper =  new ModelMapper();
        TypeMap<CampaignDTO, Campaign> campaignDTOTypeMap = modelMapper.createTypeMap(CampaignDTO.class, Campaign.class);
        campaignDTOTypeMap.addMappings(mapper -> mapper.skip(Campaign::setClients));
        return modelMapper;
    }
}
