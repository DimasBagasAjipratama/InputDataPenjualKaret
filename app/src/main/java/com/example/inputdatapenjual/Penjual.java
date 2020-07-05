package com.example.inputdatapenjual;

import java.io.Serializable;

public class Penjual  implements Serializable {
    private String nama;
    private  String tanggal;
    private String harga;
    private String berat;

    public Penjual(){
    }

    public String getNama(){
        return nama;
    }

    public void setNama(String nm) {
        this.nama=nm;
    }

    public  String getTanggal(){
        return tanggal;
    }

    public void  setTanggal(String tgl){
        this.tanggal = tgl;
    }

    public String getHarga(){
        return harga;
    }
    public  void  setHarga(String hrg){
        this.harga = hrg;
    }

    public String getBerat(){
        return  berat;
    }
    public void setBerat(String brt){
        this.berat = brt;
    }

    @Override
    public String toString(){
        return ""+nama+"\n" +
                " "+tanggal +"\n" +
                " "+harga+"\n"+
                " "+berat+"\n";
    }
    public  Penjual (String nm, String tgl , String hrg , String brt){
        nama = nm;
        tanggal = tgl;
        harga = hrg;
        berat = brt;
    }
}
