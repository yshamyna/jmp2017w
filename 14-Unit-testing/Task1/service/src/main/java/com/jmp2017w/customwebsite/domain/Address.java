package com.jmp2017w.customwebsite.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 */
@Embeddable
public class Address
{
    @Column(name = "street")
    private String street;

    @Column(name = "flatNumber")
    private Integer flatNumber;

    @Column(name = "houseNumber")
    private Integer houseNumber;

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public Integer getFlatNumber()
    {
        return flatNumber;
    }

    public void setFlatNumber(Integer flatNumber)
    {
        this.flatNumber = flatNumber;
    }

    public Integer getHouseNumber()
    {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber)
    {
        this.houseNumber = houseNumber;
    }
}