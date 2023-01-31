package hello.servlet.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();


    /**
     * 한번에 여러 테스트를 실행하면 메모리DB에 직전 테스트의 결과가 남을 수 있기 때문에
     * @AfterEach를 사용하여 각 테스트가 종료될 때 마다 이 기능을 실행한다.
     */
    @AfterEach
    void afterEach () {
        memberRepository.clearStore();
    }

    @Test
    void save() {

        //given
        Member member = new Member("hello", 20);

        //when
        Member savedMember = memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember);

    }

    @Test
    void findAll () {

        //given
        Member member1 = new Member("member1",20);
        Member member2 = new Member("member2",40);

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> result = memberRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1,member2);

    }

}
