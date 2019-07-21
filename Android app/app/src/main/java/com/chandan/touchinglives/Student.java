package com.chandan.touchinglives;
public class Student {

    private  String rno;
    private String name;

    public Student(){


    }

    @Override
    public String toString() {
        return "Roll No : " + rno +
                "   Name : " + name + "\n" ;
    }

    public Student(String rno, String name) {
        this.rno = rno;
        this.name = name;
    }

    public String getRno() {
        return rno;
    }

    public void setRno(String rno) {
        this.rno = rno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
