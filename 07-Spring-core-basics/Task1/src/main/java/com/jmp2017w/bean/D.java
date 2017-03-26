package com.jmp2017w.bean;

/**
 * Bean D.
 */
public class D
{
    private Boolean isGoodObject;
    private String opinion;

    public Boolean getGoodObject()
    {
        return isGoodObject;
    }

    public void setGoodObject(Boolean goodObject)
    {
        isGoodObject = goodObject;
    }

    public String getOpinion()
    {
        return opinion;
    }

    public void setOpinion(String opinion)
    {
        this.opinion = opinion;
    }

    @Override
    public String toString()
    {
        return "D{" +
                "isGoodObject=" + isGoodObject +
                ", opinion='" + opinion + '\'' +
                '}';
    }
}