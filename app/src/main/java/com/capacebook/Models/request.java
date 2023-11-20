package com.capacebook.Models;

public class request {

    String useridto;
    String uderidfrom;
    String status;
    String created_at;

    public String getUseridto() {
        return useridto;
    }

    public void setUseridto(String useridto) {
        this.useridto = useridto;
    }

    public String getUderidfrom() {
        return uderidfrom;
    }

    public void setUderidfrom(String uderidfrom) {
        this.uderidfrom = uderidfrom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
