package com.jmp2017w.customwebsite.web.exception;

public class ResourceNotFoundException extends Exception
{
    public ResourceNotFoundException()
    {
        super();
    }

    public ResourceNotFoundException(String message)
    {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ResourceNotFoundException(Throwable cause)
    {
        super(cause);
    }
}