package com.jmp2017w.customwebsite.service.exception;

public class InvalidInputParameterFoundException extends Exception
{
    public InvalidInputParameterFoundException()
    {
        super();
    }

    public InvalidInputParameterFoundException(String message)
    {
        super(message);
    }

    public InvalidInputParameterFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public InvalidInputParameterFoundException(Throwable cause)
    {
        super(cause);
    }
}