package com.example.onlineprofile;

public class profilecreation
{
    private  String text;
    private int IButton1Id;
    private  String text1;
    private  String text2;
    private  String text3;

    public   profilecreation(){
    }

    public  profilecreation(String text,int IButton1Id,String text1,String text2, String text3){
        this.text = text;
        this.IButton1Id = IButton1Id;
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIButton1Id() {
        return IButton1Id;
    }

    public void setIButton1Id(int IButton1Id) {
        this.IButton1Id = IButton1Id;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }
}
