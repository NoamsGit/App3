package com.example.app3.Entities;

public class ParcelChange {

    private boolean initial;
    private boolean success;
    private boolean failure;


    public ParcelChange(){
        setInitial(true);
        setSuccess(false);
        setFailure(false);
    }
    public ParcelChange(boolean initial, boolean success, boolean failure) {
        this.initial = initial;
        this.success = success;
        this.failure = failure;
    }

    public boolean isInitial() {
        return initial;
    }

    public void setInitial(boolean initial) {
        this.initial = initial;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isFailure() {
        return failure;
    }

    public void setFailure(boolean failure) {
        this.failure = failure;
    }
}
