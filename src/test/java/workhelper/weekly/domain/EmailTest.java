package workhelper.weekly.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static workhelper.weekly.domain.MemberFixture.createMemberRegisterRequest;
import static workhelper.weekly.domain.MemberFixture.createPasswordEncoder;

class EmailTest {
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        this.passwordEncoder = createPasswordEncoder();
    }


    @Test
    void equality() {
        Member member1 = Member.register(createMemberRegisterRequest(), passwordEncoder);
        Member member2 = Member.register(createMemberRegisterRequest(), passwordEncoder);

        assertThat(member1.getEmail()).isEqualTo(member2.getEmail());
    }

    @Test
    void notEquality() {
        Member member1 = Member.register(createMemberRegisterRequest(), passwordEncoder);
        Member member2 = Member.register(createMemberRegisterRequest("il4u.cjk"), passwordEncoder);

        assertThat(member1.getEmail()).isNotEqualTo(member2.getEmail());
    }

}