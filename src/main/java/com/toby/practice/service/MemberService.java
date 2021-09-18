package com.toby.practice.service;

import com.toby.practice.controller.dto.MemberSaveRequestDto;
import com.toby.practice.controller.dto.MemberUpdateRequestDto;
import com.toby.practice.domain.Member;
import com.toby.practice.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Member save(MemberSaveRequestDto requestDto){
        return memberRepository.save(requestDto.toEntity());
    }

    @Transactional
    public Member findById(Long id){
        return memberRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("존재하지 않는 멤버입니다: " + id)
        );
    }

    @Transactional
    public Long updateMember(Long id, MemberUpdateRequestDto requestDto){
        Member member = memberRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("존재하지 않는 멤버입니다: " + id));
        member.update(requestDto.getPw(), requestDto.getUserEmail());
        return id;
    }

    @Transactional
    public void deleteAll(){
        memberRepository.deleteAll();;
    }
}
