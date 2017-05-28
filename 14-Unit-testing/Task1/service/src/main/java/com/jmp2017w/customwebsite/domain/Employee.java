package com.jmp2017w.customwebsite.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.List;

/**
 *
 */
@Entity
@Table(name = "employee")
public class Employee
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EmployeeStatus status;

    @Embedded
    private Address address;

    @OneToOne(cascade = {CascadeType.ALL})
    @PrimaryKeyJoinColumn
    private EmployeePersonalInfo personalInfo;

    @ManyToOne
    @JoinColumn(name = "unit")
    private Unit unit;

    @ManyToMany
    @JoinTable(
        name = "employeeProjectCorrelation",
        joinColumns = @JoinColumn(name = "employeeID", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "projectID", referencedColumnName = "id")
    )
    private List<Project> projects;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public EmployeeStatus getStatus()
    {
        return status;
    }

    public void setStatus(EmployeeStatus status)
    {
        this.status = status;
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public EmployeePersonalInfo getPersonalInfo()
    {
        return personalInfo;
    }

    public void setPersonalInfo(EmployeePersonalInfo personalInfo)
    {
        this.personalInfo = personalInfo;
    }

    public Unit getUnit()
    {
        return unit;
    }

    public void setUnit(Unit unit)
    {
        this.unit = unit;
    }

    public List<Project> getProjects()
    {
        return projects;
    }

    public void setProjects(List<Project> projects)
    {
        this.projects = projects;
    }
}