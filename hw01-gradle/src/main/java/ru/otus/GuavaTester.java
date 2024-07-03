package ru.otus;

import com.google.common.base.Splitter;

public class GuavaTester {
   public static void main(String args[]) {
      GuavaTester tester = new GuavaTester();
      tester.testSplitter();
   }

   void testSplitter() {
      System.out.println(Splitter.on(',')
         .trimResults()
         .omitEmptyStrings()
         .split("the ,quick, ,brown, fox, jumps, over, the, lazy, little dog."));
   }
}