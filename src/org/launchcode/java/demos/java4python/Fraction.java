package org.launchcode.java.demos.java4python;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by LaunchCode
 */
public class Fraction extends Number implements Comparable<Fraction> {

    private Integer numerator;
    private Integer denominator;

    /** Creates a new instance of Fraction */
    public Fraction(Integer num, Integer den) {
        this.numerator = num;
        this.denominator = den;
    }

    public Fraction(Integer num) {
        this.numerator = num;
        this.denominator = 1;
    }

    public Fraction add(Fraction other) {
        Integer newNum = other.getDenominator()*this.numerator + this.denominator*other.getNumerator();
        Integer newDen = this.denominator * other.getDenominator();
        Integer common = gcd(newNum,newDen);
        return new Fraction(newNum/common, newDen/common );
    }

    public Fraction add(Integer other) {
        return add(new Fraction(other));
    }

    public Integer getNumerator() {
        return numerator;
    }

    public void setNumerator(Integer numerator) {
        this.numerator = numerator;
    }

    public Integer getDenominator() {
        return denominator;
    }

    public void setDenominator(Integer denominator) {
        this.denominator = denominator;
    }

    public String toString() {
        return numerator.toString() + "/" + denominator.toString();
    }

    public boolean equals(Fraction other) {
        Integer num1 = this.numerator * other.getDenominator();
        Integer num2 = this.denominator * other.getNumerator();
        if (num1 == num2)
            return true;
        else
            return false;
    }

    public double doubleValue() {
        return numerator.doubleValue() / denominator.doubleValue();
    }

    public float floatValue() {
        return numerator.floatValue() / denominator.floatValue();
    }

    public int intValue() {
        return numerator.intValue() / denominator.intValue();
    }

    public long longValue() {
        return numerator.longValue() / denominator.longValue();
    }

    public int compareTo(Fraction other) {
        Integer num1 = this.numerator * other.getDenominator();
        Integer num2 = this.denominator * other.getNumerator();
        return num1 - num2;
    }

    private static Integer gcd(Integer m, Integer n) {
        while (m % n != 0) {
            Integer oldm = m;
            Integer oldn = n;
            m = oldn;
            n = oldm%oldn;
        }
        return n;
    }

    /*
    *   Our main method tests our class and demonstrates behavior
    * */
    public static void main(String[] args) {
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(2, 3);
        Fraction f3 = new Fraction(1, 4);

        System.out.println(f1.add(1));
        System.out.println(f1.intValue());
        System.out.println(f1.doubleValue());

        ArrayList<Fraction> myFracs = new ArrayList<Fraction>();
        myFracs.add(f1);
        myFracs.add(f2);
        myFracs.add(f3);
        Collections.sort(myFracs);

        for(Fraction f : myFracs) {
            System.out.println(f);
        }
    }

}