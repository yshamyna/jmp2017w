package com.jmp2017w.bean;

/**
 * Bean B.
 */
public class B
{
    private A a;

    public B(A a)
    {
        this.a = a;
    }

    public A getA()
    {
        return a;
    }

    public void setA(A a)
    {
        this.a = a;
    }

    @Override
    public String toString()
    {
        return "B{" +
                "a=" + a +
                '}';
    }
}