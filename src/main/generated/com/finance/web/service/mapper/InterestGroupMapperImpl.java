package com.finance.web.service.mapper;

import com.finance.web.dto.InterestGroupDto;
import com.finance.web.dto.InterestGroupDto.InterestGroupDtoBuilder;
import com.finance.web.entity.InterestGroup;
import com.finance.web.entity.InterestGroup.InterestGroupBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-01T00:00:06+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class InterestGroupMapperImpl implements InterestGroupMapper {

    @Override
    public InterestGroupDto toDto(InterestGroup e) {
        if ( e == null ) {
            return null;
        }

        InterestGroupDtoBuilder interestGroupDto = InterestGroupDto.builder();

        interestGroupDto.id( e.getId() );
        interestGroupDto.name( e.getName() );
        interestGroupDto.sequence( e.getSequence() );

        return interestGroupDto.build();
    }

    @Override
    public InterestGroup toEntity(InterestGroupDto d) {
        if ( d == null ) {
            return null;
        }

        InterestGroupBuilder interestGroup = InterestGroup.builder();

        interestGroup.id( d.getId() );
        interestGroup.name( d.getName() );
        interestGroup.sequence( d.getSequence() );

        return interestGroup.build();
    }

    @Override
    public void updateFromDto(InterestGroupDto dto, InterestGroup entity) {
        if ( dto == null ) {
            return;
        }
    }
}
