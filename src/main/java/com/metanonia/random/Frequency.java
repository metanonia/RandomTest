package com.metanonia.random;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;
import org.apache.commons.math3.special.Erf;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class Frequency {
    public static void main(String[] args) {
        try {
            File file = new File(args[0]);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            Integer sum = 0;
            Integer count = 0;
            while ((line = bufferedReader.readLine()) != null) {
                if(line.startsWith("0x")) line = line.substring(2);
                BigInteger bigInteger = new BigInteger(line, 16);
                String binString = bigInteger.toString(2);

                for(int i=0; i< binString.length();i++) {
                    if(binString.substring(i,i+1).matches("1")) sum = sum+1;
                    else sum = sum -1;
                    count = count+1;
                }
            }
            Double obs = abs(sum)/sqrt(count);
            Double erfc = Erf.erfc(obs/sqrt(2));
            System.out.println("sum = " + sum.toString() + " count= " + count.toString()
                    + " obs = " + obs.toString() + " erf=" + erfc.toString());
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
