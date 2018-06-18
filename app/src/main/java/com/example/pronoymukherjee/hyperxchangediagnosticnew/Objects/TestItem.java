package com.example.pronoymukherjee.hyperxchangediagnosticnew.Objects;

public class TestItem {
    private int imageID;
    private String testName;

    public TestItem(int imageID, String testName) {
        this.imageID = imageID;
        this.testName = testName;
    }

    public int getImageID() {
        return imageID;
    }

    public String getTestName() {
        return testName;
    }
}
