package com.jmp2017w.db.dao;

import com.jmp2017w.bean.Person;
import com.jmp2017w.db.DatabaseManager;
import com.jmp2017w.service.util.ResourceCloser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class PersonDAOImpl implements PersonDAO
{
    private static final String SELECT_ALL_SQL = "SELECT id, firstName, lastName, birthDate, hobbies FROM persons;";
    private static final String SELECT_BY_ID_SQL = "SELECT firstName, lastName, birthDate, hobbies FROM persons WHERE id = ?";

    private static final String ADD_SQL = "INSERT INTO persons(firstName, lastName, birthDate, hobbies) VALUES(?, ?, ?, ?);";
    private static final String DELETE_SQL = "DELETE FROM persons WHERE id = ?";
    private static final String UPDATE_SQL = "UPDATE persons SET firstName = ?, lastName = ?, birthDate = ?, hobbies = ? WHERE id = ?";


    public List<Person> all()
    {
        List<Person> persons = new ArrayList<Person>();
        Connection connection = null;
        Statement selectAllPersons = null;
        ResultSet resultSet = null;
        try
        {
            connection = DatabaseManager.getConnection();
            selectAllPersons = connection.createStatement();
            resultSet = selectAllPersons.executeQuery(SELECT_ALL_SQL);
            Person person;
            while (resultSet.next())
            {
                person = new Person();
                person.setId(resultSet.getLong("id"));
                person.setFirstName(resultSet.getString("firstName"));
                person.setLastName(resultSet.getString("lastName"));
                person.setBirthDate(resultSet.getTimestamp("birthDate"));
                person.setHobbies(resultSet.getString("hobbies"));
                persons.add(person);
            }
        }
        catch(SQLException e)
        {
            persons = new ArrayList<Person>();
        }
        finally
        {
            ResourceCloser.closeResultSets(resultSet);
            ResourceCloser.closeStatements(selectAllPersons);
            ResourceCloser.closeConnections(connection);
        }

        return persons;
    }

    public Person get(Long id)
    {
        if (id == null || id < 1L)
        {
            return null;
        }

        Person person = null;
        Connection connection = null;
        PreparedStatement selectPersonById = null;
        ResultSet resultSet = null;
        try
        {
            connection = DatabaseManager.getConnection();
            selectPersonById = connection.prepareStatement(SELECT_BY_ID_SQL);
            selectPersonById.setLong(1, id);
            resultSet = selectPersonById.executeQuery();
            if (resultSet.next())
            {
                person = new Person();
                person.setId(id);
                person.setFirstName(resultSet.getString("firstName"));
                person.setLastName(resultSet.getString("lastName"));
                person.setBirthDate(resultSet.getTimestamp("birthDate"));
                person.setHobbies(resultSet.getString("hobbies"));
            }
        }
        catch (SQLException e)
        {
            // nothing
        }
        finally
        {
            ResourceCloser.closeResultSets(resultSet);
            ResourceCloser.closeStatements(selectPersonById);
            ResourceCloser.closeConnections(connection);
        }

        return person;
    }

    public void add(Person person)
    {
        if (person == null)
        {
            return;
        }

        Connection connection = null;
        PreparedStatement insertIntoPersons = null;
        try
        {
            connection = DatabaseManager.getConnection();
            insertIntoPersons = connection.prepareStatement(ADD_SQL);
            insertIntoPersons.setString(1, person.getFirstName());
            insertIntoPersons.setString(2, person.getLastName());
            insertIntoPersons.setTimestamp(3, person.getBirthDate());
            insertIntoPersons.setString(4, person.getHobbies());
            insertIntoPersons.executeUpdate();
        }
        catch (SQLException e)
        {
            // nothing
        }
        finally
        {
            ResourceCloser.closeStatements(insertIntoPersons);
            ResourceCloser.closeConnections(connection);
        }

    }

    public void remove(Long id)
    {
        if (id == null || id < 1L)
        {
            return;
        }

        Connection connection = null;
        PreparedStatement removePersonById = null;
        try
        {
            connection = DatabaseManager.getConnection();
            removePersonById = connection.prepareStatement(DELETE_SQL);
            removePersonById.setLong(1, id);
            removePersonById.executeUpdate();
        }
        catch (SQLException e)
        {
            // nothing
        }
        finally
        {
            ResourceCloser.closeStatements(removePersonById);
            ResourceCloser.closeConnections(connection);
        }
    }

    public void remove(Set<Long> ids)
    {
        if (ids == null)
        {
            return;
        }

        Connection connection = null;
        PreparedStatement removePersonById = null;
        try
        {
            connection = DatabaseManager.getConnection();
            removePersonById = connection.prepareStatement(DELETE_SQL);
            for (Long id : ids)
            {
                if (id == null || id < 1L)
                {
                    continue;
                }

                removePersonById.setLong(1, id);
                removePersonById.addBatch();
            }
            removePersonById.executeBatch();
        }
        catch (SQLException e)
        {
            // nothing
        }
        finally
        {
            ResourceCloser.closeStatements(removePersonById);
            ResourceCloser.closeConnections(connection);
        }
    }

    public void edit(Person person)
    {
        if (person == null || person.getId() == null)
        {
            return;
        }

        Connection connection = null;
        PreparedStatement updatePerson = null;
        try
        {
            connection = DatabaseManager.getConnection();
            updatePerson = connection.prepareStatement(UPDATE_SQL);
            updatePerson.setString(1, person.getFirstName());
            updatePerson.setString(2, person.getLastName());
            updatePerson.setTimestamp(3, person.getBirthDate());
            updatePerson.setString(4, person.getHobbies());
            updatePerson.setLong(5, person.getId());
            updatePerson.executeUpdate();
        }
        catch (SQLException e)
        {
            // nothing
        }
        finally
        {
            ResourceCloser.closeStatements(updatePerson);
            ResourceCloser.closeConnections(connection);
        }
    }
}