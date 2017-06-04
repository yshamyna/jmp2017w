package com.jmp2017w.bean;

/**
 *
 */
public class Author
{
    private String surname;
    private Integer age;
    private Boolean sex;

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public void setSex(Boolean sex)
    {
        this.sex = sex;
    }

    public boolean isMale()
    {
        return sex != null && sex;
    }

    public boolean isFemale()
    {
        return !(sex == null || sex);
    }

    @Override
    public String toString()
    {
        return "Author{" +
                "surname='" + surname + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}