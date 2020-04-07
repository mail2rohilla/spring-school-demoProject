package com.deepanshu.pojos;

public class Demo {
    int a;
    int b;
    int c;
    int d;
    Demo1 demoVar;

    public Demo() {}

    public void setDemoVar(Demo1 demoVar) {
        this.demoVar = demoVar;
    }

    public Demo1 getDemoVar() {
        return demoVar;
    }

    public Demo(int a, int b, int c, int d, Demo1 dem) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.demoVar = dem;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void setB(int b) {
        this.b = b;
    }

    public void setC(int c) {
        this.c = c;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }

    public int getD() {
        return d;
    }
}
