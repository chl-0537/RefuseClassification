package com.example.refuseclassification.Database;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class Knowledge extends LitePalSupport implements Serializable {

    private int id;
    private String name;
    private String kind;
    private String answer;

    public Knowledge() {

    }

    public Knowledge(int id, String name, String kind, String answer) {
        this.id = id;
        this.name = name;
        this.kind = kind;
        this.answer = answer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getKind() {
        return kind;
    }

    public String getAnswer() {
        return answer;
    }
}
