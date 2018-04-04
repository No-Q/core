package com.noq.api;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        SecureRandom random = new SecureRandom();
        System.out.println(new BigInteger(20, random));

    }
}
