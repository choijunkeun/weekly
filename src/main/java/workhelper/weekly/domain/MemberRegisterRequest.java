package workhelper.weekly.domain;

import jakarta.validation.constraints.Size;

public record MemberRegisterRequest(
        @Size(min = 5, max = 30) String knoxId,
        @Size(min = 2, max = 10) String name,
        @Size(min = 8, max = 80)String password,
        MemberType type) {
}
