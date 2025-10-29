package br.com.scherer.pmanager.infrastructure.controller;

import br.com.scherer.pmanager.domain.applicationservice.ApíKeyService;
import br.com.scherer.pmanager.domain.document.ApiKey;
import br.com.scherer.pmanager.infrastructure.dto.ApiKeyDTO;
import br.com.scherer.pmanager.infrastructure.dto.MemberDTO;
import br.com.scherer.pmanager.infrastructure.dto.SaveMemberDataDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static br.com.scherer.pmanager.infrastructure.controller.RestConstants.PATH_API_KEYS;

@RestController
@RequestMapping(PATH_API_KEYS)
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class ApiKeyRestResource {

    private final ApíKeyService apíKeyService;

    @PostMapping
    public ResponseEntity<ApiKeyDTO> createApiKey() {
        ApiKey apiKey = apíKeyService.createApiKey();
        return ResponseEntity
                .created(URI.create(PATH_API_KEYS + "/" + apiKey.getId()))
                .body(ApiKeyDTO.create(apiKey));
    }

    @PutMapping("/{id}/revoke")
    public ResponseEntity<Void> revokeApiKey(@PathVariable("id") String id) {
        apíKeyService.revokeApiKey(id);
        return ResponseEntity.noContent().build();
    }

    /*
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

    @GetMapping
    public ResponseEntity<List<MemberDTO>> findMembers(
            @RequestParam(value = "email", required = false) String email
    ) {
        List<Member> members = memberService.findMembers(email);
        return ResponseEntity.ok(members.stream().map(MemberDTO::create).toList());
    }
    */
}
