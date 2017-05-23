package com.jmp2017w.customwebsite.web.controller;

import com.jmp2017w.customwebsite.web.exception.ResourceNotFoundException;
import com.jmp2017w.customwebsite.web.exception.UserRequestProcessingFailedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlingController
{
    /**
     * Fires when someone requests data by wrong url.
     *
     * @param exception thrown exception
     * @return resource not found error page
     */
    @ExceptionHandler({ResourceNotFoundException.class})
    public ModelAndView resourceNotFoundError(ResourceNotFoundException exception) {
        return new ModelAndView("errors/404");
    }

    /**
     * Fires when someone requests data by wrong url.
     *
     * @param exception thrown exception
     * @return resource not found error page
     */
    @ExceptionHandler({UserRequestProcessingFailedException.class})
    public ModelAndView userRequestProcessingFailedError(UserRequestProcessingFailedException exception) {
        return new ModelAndView("errors/503");
    }
}