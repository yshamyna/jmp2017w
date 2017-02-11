package com.jmp2017w.extractor;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * This class is responsible for extracting of numeric data from a file.
 */
public class NumericExtractor extends DataExtractor<Long>
{
    /**
     * {@inheritDoc}
     */
    protected void parse(String line)
    {
       if (NumberUtils.isNumber(line))
       {
           Long longValue = Long.valueOf(line);
           data.add(longValue);
       }
    }
}
