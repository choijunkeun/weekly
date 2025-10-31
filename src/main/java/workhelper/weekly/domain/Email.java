package workhelper.weekly.domain;

import java.util.regex.Pattern;


public record Email(String address) {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");


    public static Email create(String knoxId, MemberType type) {
        String email = knoxId + type.getDomain();

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("이메일 형식이 바르지 않습니다 : " + email);
        }

        return new Email(email);
    }

}
