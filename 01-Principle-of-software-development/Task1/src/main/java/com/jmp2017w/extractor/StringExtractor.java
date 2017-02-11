package com.jmp2017w.extractor;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * This class is responsible for extracting string data from a file.
 */
public class StringExtractor extends DataExtractor<String>
{
    /**
     * {@inheritDoc}
     */
    protected void parse(String line)
    {
        if (!NumberUtils.isNumber(line))
        {
            data.add(line);
        }
    }
}
