package br.com.scherer.pmanager.domain.applicationservice;

import br.com.scherer.pmanager.domain.entity.Member;
import br.com.scherer.pmanager.domain.exception.MemberNotFoundException;
import br.com.scherer.pmanager.domain.repository.MemberRepository;
import br.com.scherer.pmanager.infrastructure.dto.SaveMemberDataDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member createMember(SaveMemberDataDTO saveMemberData) {
        Member member = Member
                .builder()
                .name(saveMemberData.getName())
                .email(saveMemberData.getEmail())
                .secret(UUID.randomUUID().toString())
                .deleted(false)
                .build();

        memberRepository.save(member);
        return member;
    }

    public Member loadMemberById(String memberId) {
        return memberRepository
                .findByIdAndDeleted(memberId, false)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
    }

}
