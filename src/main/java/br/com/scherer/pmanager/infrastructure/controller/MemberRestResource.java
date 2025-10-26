package br.com.scherer.pmanager.infrastructure.controller;

import br.com.scherer.pmanager.domain.applicationservice.MemberService;
import br.com.scherer.pmanager.domain.entity.Member;
import br.com.scherer.pmanager.infrastructure.dto.MemberDTO;
import br.com.scherer.pmanager.infrastructure.dto.SaveMemberDataDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static br.com.scherer.pmanager.infrastructure.controller.RestConstants.PATH_MEMBERS;

@RestController
@RequestMapping(PATH_MEMBERS)
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class MemberRestResource {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberDTO> createMember(@RequestBody @Valid SaveMemberDataDTO saveMemberData) {
        Member member = memberService.createMember(saveMemberData);
        return ResponseEntity
                .created(URI.create(PATH_MEMBERS + "/" + member.getId()))
                .body(MemberDTO.create(member));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> loadMemberById(@PathVariable("id") String memberId) {
        Member member = memberService.loadMemberById(memberId);
        return ResponseEntity.ok(MemberDTO.create(member));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable("id") String memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<MemberDTO> updateMember(
            @PathVariable("id") String memberId,
            @RequestBody @Valid SaveMemberDataDTO saveMemberData
    ) {
        Member member = memberService.updateMember(memberId, saveMemberData);
        return ResponseEntity.ok(MemberDTO.create(member));
    }

    @GetMapping
    public ResponseEntity<List<MemberDTO>> findMembers(
            @RequestParam(value = "email", required = false) String email
    ) {
        List<Member> members = memberService.findMembers(email);
        return ResponseEntity.ok(members.stream().map(MemberDTO::create).toList());
    }

}
