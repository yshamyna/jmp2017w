package com.jmp2017w.bean;

/**
 * Bean A.
 */
public class A
{
    private String label;
    private String description;
    private Integer version;
    private Integer historySequence;

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Integer getVersion()
    {
        return version;
    }

    public void setVersion(Integer version)
    {
        this.version = version;
    }

    public Integer getHistorySequence()
    {
        return historySequence;
    }

    public void setHistorySequence(Integer historySequence)
    {
        this.historySequence = historySequence;
    }

    @Override
    public String toString()
    {
        return "A{" +
                "label='" + label + '\'' +
                ", description='" + description + '\'' +
                ", version=" + version +
                ", historySequence=" + historySequence +
                '}';
    }
}