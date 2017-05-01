package com.jmp2017w.customwebsite.dao.service;

import com.jmp2017w.customwebsite.bean.Person;
import com.jmp2017w.customwebsite.dao.PersonDAO;
import com.jmp2017w.customwebsite.dao.exception.DataAccessException;
import com.jmp2017w.customwebsite.dao.exception.DataNotFoundException;
import com.jmp2017w.customwebsite.db.DatabaseManager;
import com.jmp2017w.customwebsite.db.utils.DBResourceLiberator;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonDAOImpl implements PersonDAO
{
    public List<Person> all() throws DataAccessException
    {
        List<Person> persons = new ArrayList<Person>();
        Connection connection = null;
        Statement selectAllPersons = null;
        ResultSet resultSet = null;
        try
        {
            connection = DatabaseManager.getConnection();
            selectAllPersons = connection.createStatement();
            resultSet = selectAllPersons.executeQuery("SELECT id, firstName, lastName, age, passportNumber FROM persons");
            Person person;
            while (resultSet.next())
            {
                person = new Person();
                person.setId(resultSet.getLong("id"));
                person.setFirstName(resultSet.getString("firstName"));
                person.setLastName(resultSet.getString("lastName"));
                person.setAge(resultSet.getLong("age"));
                person.setPassportNumber(resultSet.getString("passportNumber"));
                persons.add(person);
            }
        }
        catch(SQLException e)
        {
            throw new DataAccessException(e);
        }
        finally
        {
            DBResourceLiberator.closeResultSets(resultSet);
            DBResourceLiberator.closeStatements(selectAllPersons);
            DBResourceLiberator.closeConnections(connection);
        }

        return persons;
    }

    public Person getById(Long id) throws DataAccessException, DataNotFoundException
    {
        Person person = null;
        Connection connection = null;
        PreparedStatement selectPersonById = null;
        ResultSet resultSet = null;
        try
        {
            connection = DatabaseManager.getConnection();
            selectPersonById = connection.prepareStatement("SELECT firstName, lastName, age, passportNumber FROM persons WHERE id = ?");
            selectPersonById.setLong(1, id);
            resultSet = selectPersonById.executeQuery();
            if (resultSet.next())
            {
                person = new Person();
                person.setId(id);
                person.setFirstName(resultSet.getString("firstName"));
                person.setLastName(resultSet.getString("lastName"));
                person.setAge(resultSet.getLong("age"));
                person.setPassportNumber(resultSet.getString("passportNumber"));
            }

            if (person == null)
            {
                throw new DataNotFoundException("Persons with id '" + id + "' not found.");
            }
        }
        catch (SQLException e)
        {
            throw new DataAccessException(e);
        }
        finally
        {
            DBResourceLiberator.closeResultSets(resultSet);
            DBResourceLiberator.closeStatements(selectPersonById);
            DBResourceLiberator.closeConnections(connection);
        }

        return person;
    }

    public void add(Person person) throws DataAccessException
    {
        Connection connection = null;
        PreparedStatement insertIntoPersons = null;
        try
        {
            connection = DatabaseManager.getConnection();
            insertIntoPersons = connection.prepareStatement("INSERT INTO persons(firstName, lastName, age, passportNumber) VALUES(?, ?, ?, ?);");
            insertIntoPersons.setString(1, person.getFirstName());
            insertIntoPersons.setString(2, person.getLastName());
            insertIntoPersons.setLong(3, person.getAge());
            insertIntoPersons.setString(4, person.getPassportNumber());
            insertIntoPersons.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new DataAccessException(e);
        }
        finally
        {
            DBResourceLiberator.closeStatements(insertIntoPersons);
            DBResourceLiberator.closeConnections(connection);
        }
    }

    public void update(Person person) throws DataAccessException
    {
        Connection connection = null;
        PreparedStatement updatePerson = null;
        try
        {
            connection = DatabaseManager.getConnection();
            updatePerson = connection.prepareStatement("UPDATE persons SET firstName = ?, lastName = ?, age = ?, passportNumber = ? WHERE id = ?");
            updatePerson.setString(1, person.getFirstName());
            updatePerson.setString(2, person.getLastName());
            updatePerson.setLong(3, person.getAge());
            updatePerson.setString(4, person.getPassportNumber());
            updatePerson.setLong(5, person.getId());
            updatePerson.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new DataAccessException(e);
        }
        finally
        {
            DBResourceLiberator.closeStatements(updatePerson);
            DBResourceLiberator.closeConnections(connection);
        }
    }

    public void delete(Person person) throws DataAccessException
    {
        Connection connection = null;
        PreparedStatement removePersonById = null;
        try
        {
            connection = DatabaseManager.getConnection();
            removePersonById = connection.prepareStatement("DELETE FROM persons WHERE id = ?");
            removePersonById.setLong(1, person.getId());
            removePersonById.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new DataAccessException(e);
        }
        finally
        {
            DBResourceLiberator.closeStatements(removePersonById);
            DBResourceLiberator.closeConnections(connection);
        }
    }

    public void delete(List<Person> persons) throws DataAccessException
    {
        Connection connection = null;
        PreparedStatement removePersonById = null;
        try
        {
            connection = DatabaseManager.getConnection();
            removePersonById = connection.prepareStatement("DELETE FROM persons WHERE id = ?");
            for (Person person : persons)
            {
                removePersonById.setLong(1, person.getId());
                removePersonById.addBatch();
            }
            removePersonById.executeBatch();
        }
        catch (SQLException e)
        {
            throw new DataAccessException(e);
        }
        finally
        {
            DBResourceLiberator.closeStatements(removePersonById);
            DBResourceLiberator.closeConnections(connection);
        }
    }
}