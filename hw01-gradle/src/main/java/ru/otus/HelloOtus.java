package ru.otus;

import com.google.common.base.Splitter;

public class HelloOtus {
    public void testSplitter() {
        System.out.println(Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split("the ,quick, ,brown, fox, jumps, over, the, lazy, little dog."));
    }
}
