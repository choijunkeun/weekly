package workhelper.weekly.domain;

public class MemberFixture {
    public static MemberRegisterRequest createMemberRegisterRequest(String knoxId) {
        return new MemberRegisterRequest(knoxId, "최준근", "secret", MemberType.PARTNER);
    }

    public static MemberRegisterRequest createMemberRegisterRequest() {
        return createMemberRegisterRequest("junkeun.choi");
    }

    public static PasswordEncoder createPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(String password) {
                return password.toUpperCase();
            }

            @Override
            public boolean matches(String password, String passwordHash) {
                return encode(password).matches(passwordHash);
            }
        };
    }
}
