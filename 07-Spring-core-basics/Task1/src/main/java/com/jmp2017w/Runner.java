package com.jmp2017w;

import com.jmp2017w.bean.A;
import com.jmp2017w.bean.B;
import com.jmp2017w.bean.C;
import com.jmp2017w.bean.E;
import com.jmp2017w.bean.F;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Create Spring console app. Use XML as configuration format.
 * 1. Create bean A, use DI via setters, use property placeholder for values
 * 2. Create bean B, use DI via constructor (bean A as argument of constructor)
 * 3. Create bean C with singleton scope and bean D with prototype scope. We need to add bean D as property of bean C.
 *    Pay attention that they have different scopes. Consider Lookup Method Injection.
 * 4. Create bean E and replace logic of one of his method by Method Replacement
 * 5. Create bean F and log all possible steps from his lifecycle (lifecycle of Spring bean).
 */
public class Runner
{
    public static void main(String[] args)
    {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("bean-context.xml");
        context.registerShutdownHook();

        A a = context.getBean("Bean-A", A.class);
        System.out.println(a.toString());

        B b = context.getBean("Bean-B", B.class);
        System.out.println(b.toString());

        C c = context.getBean("Bean-C", C.class);
        System.out.println(c.toString());

        E e = context.getBean("Bean-E", E.class);
        e.greeting();

        context.getBean("Bean-F", F.class);
    }
}