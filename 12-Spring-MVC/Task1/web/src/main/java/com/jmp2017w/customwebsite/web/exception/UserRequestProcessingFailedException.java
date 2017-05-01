package com.jmp2017w.customwebsite.web.exception;

public class UserRequestProcessingFailedException extends Exception
{
    public UserRequestProcessingFailedException()
    {
        super();
    }

    public UserRequestProcessingFailedException(String message)
    {
        super(message);
    }

    public UserRequestProcessingFailedException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public UserRequestProcessingFailedException(Throwable cause)
    {
        super(cause);
    }
}