package com.cydeo.enums;

import lombok.Getter;

@Getter
public enum CourseStatus {

    ENROLLED("Enrolled"),
    NOT_ENROLLED("Not Enrolled");

    private final String value;

    CourseStatus(String value) {
        this.value = value;
    }
}
