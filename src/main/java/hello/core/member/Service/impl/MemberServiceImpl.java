package hello.core.member.Service.impl;

import hello.core.member.Member;
import hello.core.member.Repository.MemberRepository;
import hello.core.member.Repository.impl.MemoryMemberRepository;
import hello.core.member.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemoryMemberRepository memberRepository;

    @Autowired  // ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemoryMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // Test 용도
    public MemberRepository getMemberRepository()   {
        return memberRepository;
    }
}
