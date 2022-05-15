package com.finance.web.service.mapper;

import com.finance.web.config.MapStructMapperConfig;
import com.finance.web.dto.MemberDto;
import com.finance.web.entity.Member;
import org.mapstruct.Mapper;

@Mapper(config = MapStructMapperConfig.class)
public interface MemberMapper extends GenericMapper<MemberDto, Member> {
}
