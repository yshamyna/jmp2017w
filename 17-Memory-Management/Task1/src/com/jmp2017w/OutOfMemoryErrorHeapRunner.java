package com.jmp2017w;

import com.jmp2017w.bean.Key;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class OutOfMemoryErrorHeapRunner
{
    private static final Integer TRY_COUNT = 1000000;

    public static void main(String... args)
    {
        Map<Key, String> map = new HashMap<>();
        for (long i = 0; i < TRY_COUNT; i++)
        {
            for (long j = 0; j < TRY_COUNT; j++)
            {
                if (!map.containsKey(new Key(j)))
                {
                    map.put(new Key(j), "Some long sentence in this great application with number " + j);
                }
            }
        }
    }
}