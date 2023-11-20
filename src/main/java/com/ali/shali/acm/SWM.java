package com.ali.shali.acm;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * @Author shali
 * @Date 2023/8/18 10:01
 * @PackageName:com.ali.shali.acm
 * @ClassName: Main
 * @Description: TODO
 * @Version 1.0
 */
public class SWM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        for (int i = 0; i < t; i++) {
            LocalTime t1 = LocalTime.parse(sc.nextLine(), formatter);
            LocalTime t2 = LocalTime.parse(sc.nextLine(), formatter);
            LocalTime t3 = LocalTime.parse(sc.nextLine(), formatter);
            if (Math.abs(Duration.between(t2,t3).toHours()) <= 2) {
                if (t3.isAfter(t2)) System.out.println("Yes");
                else System.out.println("No");
            }
            else {
                long abs1 = Math.abs(Duration.between(t1, t3).toMinutes());
                long abs2 = Math.abs(Duration.between(t1, t2).toMinutes());
                System.out.println("abs1 = " + abs1);
                System.out.println("abs2 = " + abs2);
                if (abs1 >= abs2) System.out.println("Yes" );
                else System.out.println("No");
            }
        }
    }
}
