package com.finance.web.service.mapper;

import com.finance.web.config.MapStructMapperConfig;
import com.finance.web.dto.InterestGroupDto;
import com.finance.web.entity.InterestGroup;
import org.mapstruct.Mapper;

@Mapper(config = MapStructMapperConfig.class)
public interface InterestGroupMapper extends GenericMapper<InterestGroupDto, InterestGroup> {
}
