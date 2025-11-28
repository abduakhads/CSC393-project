package com.example.services;

import com.example.models.Member;
import java.util.List;
import java.util.Optional;

public interface MemberService {
    List<Member> getAllMembers();
    Optional<Member> getMemberById(Long id);
    Member saveMember(Member member);
    void deleteMember(Long id);
}