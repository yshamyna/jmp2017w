package com.jmp2017w.memoryleak;

import com.jmp2017w.memoryleak.tasks.CopyTask;
import com.jmp2017w.memoryleak.tasks.FrequencyWordBookTask;
import com.jmp2017w.memoryleak.tasks.Task;
import static java.lang.System.out;


/**
 * Create simple java application (for example text file parsing or something else)
 * with at least two memory leaks and find them using YourKit Profiler tool.
 *
 * Sub-task 1. Parse zipped file and create frequency word book.
 * Sub-task 2. Copy file.
 */
public class Runner
{
    /**
     * Entrance point to the application.
     * @param args arguments
     */
    public static void main(String... args)
    {
        try
        {
            Task[] tasks = {new FrequencyWordBookTask(), new CopyTask()};
            for (Task task : tasks)
            {
                task.execute("data.zip");
            }
            out.println("Finish.");
        }
        catch (Exception exception)
        {
            out.print("Error occurred while performing operation. Please check that all needful files exist, ");
            out.println("can be found, and can be accessed.");
        }
    }
}