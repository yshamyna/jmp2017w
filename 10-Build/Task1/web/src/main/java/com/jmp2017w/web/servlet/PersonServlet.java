package com.jmp2017w.web.servlet;

import com.jmp2017w.bean.Person;
import com.jmp2017w.service.PersonService;
import com.jmp2017w.service.PersonServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class PersonServlet extends HttpServlet
{
    private PersonService service = new PersonServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String servletPath = req.getServletPath();
        String view;
        if ("/persons/remove".equals(servletPath))
        {
            view = "/jsp/person/person-remove.jsp";
            List<Person> persons = service.all();
            req.setAttribute("persons", persons);
            req.setAttribute("isEmpty", persons.isEmpty());
        }
        else
        {
            if ("/persons/add".equals(servletPath))
            {
                view = "/jsp/person/person-add.jsp";
            }
            else
            {
                if ("/persons/edit".equals(servletPath))
                {
                    view = "/jsp/person/person-edit.jsp";
                    Person personToEdit = service.get(Long.valueOf(req.getParameter("id")));
                    req.setAttribute("person", personToEdit);
                }
                else
                {
                    view = "/jsp/person/person-all.jsp";
                    List<Person> persons = service.all();
                    req.setAttribute("persons", persons);
                    req.setAttribute("isEmpty", persons.isEmpty());
                }
            }
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(view);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        if (req.getParameterValues("ids") != null)
        {
            delete(req, resp);
        }
        else
        {
            if (req.getParameter("id") == null)
            {
                add(req, resp);
            }
            else
            {
                edit(req, resp);
            }
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Person person = new Person();
        person.setFirstName(req.getParameter("firstName"));
        person.setLastName(req.getParameter("lastName"));
        person.setHobbies(req.getParameter("hobbies"));

        String birthDateAsString = req.getParameter("birthDate");
        Timestamp birthDate = null;
        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(birthDateAsString);
            birthDate = new java.sql.Timestamp(parsedDate.getTime());
        }
        catch(Exception e)
        {
            // nothing
        }
        person.setBirthDate(birthDate);

        service.add(person);

        List<Person> persons = service.all();
        req.setAttribute("persons", persons);
        req.setAttribute("isEmpty", persons.isEmpty());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/person/person-all.jsp");
        dispatcher.forward(req, resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String[] idsAsString = req.getParameterValues("ids");

        Set<Long> ids = new HashSet<Long>(idsAsString.length);
        for (String idAsString : idsAsString)
        {
            ids.add(Long.valueOf(idAsString));
        }
        service.remove(ids);

        List<Person> persons = service.all();
        req.setAttribute("persons", persons);
        req.setAttribute("isEmpty", persons.isEmpty());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/person/person-remove.jsp");
        dispatcher.forward(req, resp);
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Person person = new Person();
        person.setId(Long.valueOf(req.getParameter("id")));
        person.setFirstName(req.getParameter("firstName"));
        person.setLastName(req.getParameter("lastName"));
        person.setHobbies(req.getParameter("hobbies"));

        String birthDateAsString = req.getParameter("birthDate");
        Timestamp birthDate = null;
        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(birthDateAsString);
            birthDate = new java.sql.Timestamp(parsedDate.getTime());
        }
        catch(Exception e)
        {
            // nothing
        }
        person.setBirthDate(birthDate);

        service.edit(person);

        List<Person> persons = service.all();
        req.setAttribute("persons", persons);
        req.setAttribute("isEmpty", persons.isEmpty());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/person/person-all.jsp");
        dispatcher.forward(req, resp);
    }
}