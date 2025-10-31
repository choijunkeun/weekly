package workhelper.weekly.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static workhelper.weekly.domain.MemberFixture.createMemberRegisterRequest;
import static workhelper.weekly.domain.MemberFixture.createPasswordEncoder;

class MemberTest {
    Member member;
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        this.passwordEncoder = createPasswordEncoder();
        member = Member.register(createMemberRegisterRequest(), passwordEncoder);
    }

    @Test
    void register() {
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }

    @Test
    void activate() {
        member.activate();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVE);
    }

    @Test
    void activateFail() {
        member.activate();

        assertThatThrownBy(member::activate).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void deactivate() {
        member.activate();

        member.deactivate();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.DEACTIVATED);
    }
    
    @Test
    void deactivateFail() {
        assertThatThrownBy(() -> member.deactivate())
        .isInstanceOf(IllegalStateException.class);
        
        member.activate();
        member.deactivate();
        
        assertThatThrownBy(() -> member.deactivate())
        .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void verifyPassword() {
        assertThat(member.verifyPassword("secret", passwordEncoder)).isTrue();
        assertThat(member.verifyPassword("verysecret", passwordEncoder)).isFalse();
    }

    @Test
    void changeRole() {
        assertThat(member.getRole()).isEqualTo(MemberRole.DEFAULT);

        member.changeRole(MemberRole.ADMIN);

        assertThat(member.getRole()).isEqualTo(MemberRole.ADMIN);
    }

    @Test
    void changePassword() {
        assertThat(member.verifyPassword("secret", passwordEncoder)).isTrue();
        member.changePassword("change1234", passwordEncoder);

        assertThat(member.verifyPassword("change1234", passwordEncoder)).isTrue();
    }

    @Test
    void isActive() {
        assertThat(member.isActive()).isFalse();

        member.activate();

        assertThat(member.isActive()).isTrue();

        member.deactivate();

        assertThat(member.isActive()).isFalse();
    }



}

