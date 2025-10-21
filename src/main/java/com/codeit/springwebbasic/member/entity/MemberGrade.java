package com.codeit.springwebbasic.member.entity;

public enum MemberGrade {
    BRONZE(0),
    SILVER(5),
    GOLD(10),
    PLATINUM(15);

    private final int requiredRentals;

    MemberGrade(int requiredRentals) {
        this.requiredRentals = requiredRentals;
    }

    public MemberGrade upgrade() {
        return switch (this) {
            case BRONZE -> SILVER;
            case SILVER -> GOLD;
            case GOLD, PLATINUM -> PLATINUM;
        };
    }

}

