package com.jmp2017w;

import com.jmp2017w.bean.Bean;

/**
 *
 */
public class StackOverflowErrorRunner
{
    public static void main(String... args)
    {
        Bean bean = new Bean();
        Bean otherBean = new Bean(bean);
    }
}