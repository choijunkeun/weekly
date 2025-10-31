package workhelper.weekly.domain;

import lombok.Getter;

public enum MemberType {
    EMPLOYEE("@samsung.com"),
    PARTNER("@partner.samsung.com");

    @Getter
    private final String domain;

    MemberType(String domain) {
        this.domain = domain;
    }
}
