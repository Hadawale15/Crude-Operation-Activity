package com.example.crudeoperation_activity;

public class Mod {
    String name,email,dob,password,ld,Id;

    public Mod() {
    }

    public Mod(String name, String email, String dob, String password, String ld, String id) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.password = password;
        this.ld = ld;
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLd() {
        return ld;
    }

    public void setLd(String ld) {
        this.ld = ld;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
