package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ErrorInResponse implements Serializable {
    @JsonProperty("Error")
    private String error;

    public ErrorInResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
