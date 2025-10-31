package workhelper.weekly.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.Assert;

import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static org.springframework.util.Assert.state;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {
    private String knoxId;

    private Email email;

    private String name;

    private String passwordHash;

    private MemberStatus status;

//    private MemberDetail detail;

    private MemberType type;

    private MemberRole role;


    public static Member register(MemberRegisterRequest createRequest, PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.knoxId = requireNonNull(createRequest.knoxId());
        member.email = Email.create(requireNonNull(createRequest.knoxId()), createRequest.type());
        member.name = requireNonNull(createRequest.name());
        member.passwordHash = passwordEncoder.encode(requireNonNull(createRequest.password()));
        member.type = requireNonNull(createRequest.type());

        member.status = MemberStatus.PENDING;
        member.role = MemberRole.DEFAULT;

        return member;
    }

    public boolean verifyPassword(String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password, this.passwordHash);
    }

    public void activate() {
        state(status == MemberStatus.PENDING, "등록대기(PENDING) 상태가 아닙니다.");

        this.status = MemberStatus.ACTIVE;
    }

    public void deactivate() {
        state(status == MemberStatus.ACTIVE, "등록완료(ACTIVE) 상태에서만 탈퇴할 수 있습니다.");

        this.status = MemberStatus.DEACTIVATED;
    }

    public void changeRole(MemberRole role) {
        this.role = role;
    }

    public void changePassword(String password, PasswordEncoder passwordEncoder) {
        this.passwordHash = passwordEncoder.encode(requireNonNull(password));
    }

    public boolean isActive() {
        return this.status == MemberStatus.ACTIVE;
    }
}
