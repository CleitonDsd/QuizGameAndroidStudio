package com.cleitondsd.etec.quizetecv1;

public class Category {

    public static final int PROGRAMMING_LOGIC = 1;
    public static final int JAVA = 2;
    public static final int SQL = 3;

    private int id;
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @androidx.annotation.NonNull
    @Override
    public String toString() {
        return getName();
    }
}
