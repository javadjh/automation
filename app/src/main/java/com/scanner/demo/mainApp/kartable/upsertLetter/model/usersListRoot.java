package com.scanner.demo.mainApp.kartable.upsertLetter.model;

import java.util.List;

public class usersListRoot {
    private List<DataUsersList> data;
    private boolean status;
    private String message;

    public List<DataUsersList> getData() {
        return data;
    }

    public void setData(List<DataUsersList> data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
