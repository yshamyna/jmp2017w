package com.jmp2017w.bean;

/**
 *
 */
public interface SomeClass
{
    Leak leak();
    long counter();
    SomeClass copy(SomeClass someClass);
}