package com.example.pronoymukherjee.hyperxchangediagnosticnew.Objects;

public class Test {
    private String testName;
    private int score;
    private int testSuccessImageID;

    public Test(String testName, int score, int testSuccessImageID) {
        this.testName = testName;
        this.score = score;
        this.testSuccessImageID = testSuccessImageID;
    }

    public String getTestName() {
        return testName;
    }

    public int getScore() {
        return score;
    }

    public int getTestSuccessImageID() {
        return testSuccessImageID;
    }
}
