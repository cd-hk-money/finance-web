package com.finance.web.service.mapper;

import com.finance.web.dto.MemberDto;
import com.finance.web.dto.MemberDto.MemberDtoBuilder;
import com.finance.web.entity.Member;
import com.finance.web.entity.Member.MemberBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-01T00:00:06+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public MemberDto toDto(Member e) {
        if ( e == null ) {
            return null;
        }

        MemberDtoBuilder memberDto = MemberDto.builder();

        memberDto.email( e.getEmail() );
        memberDto.username( e.getUsername() );
        memberDto.subscription( e.getSubscription() );

        return memberDto.build();
    }

    @Override
    public Member toEntity(MemberDto d) {
        if ( d == null ) {
            return null;
        }

        MemberBuilder member = Member.builder();

        member.email( d.getEmail() );
        member.username( d.getUsername() );
        member.subscription( d.getSubscription() );

        return member.build();
    }

    @Override
    public void updateFromDto(MemberDto dto, Member entity) {
        if ( dto == null ) {
            return;
        }
    }
}
