package com.example.maskerin;

public class Pengguna {
    public String nama, email, nik;

    public Pengguna(){

    }

    public Pengguna(String email, String nama, String nik) {
        this.email = email;
        this.nama = nama;
        this.nik=nik;
    }

    public String getUserName() {
        return nama;
    }
    public String getUserNIK() {
        return nik;
    }
    public String getUserEmail() {
        return email;
    }


}