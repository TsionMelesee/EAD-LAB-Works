package com.task.servlet;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class TestLombok {
    private String message;

    public static void main(String[] args) {
        TestLombok test = new TestLombok();
        test.setMessage("Lombok Works  !");
        System.out.println(test.getMessage());
    }
}
