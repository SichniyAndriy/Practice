package org.example;

import java.util.*;

public class Main {
    static class Box<T> {
        // your code here
        private T t;

        public Box(T t) {
            this.t = t;
        }

        public T getT() {
            return t;
        }

        public void setT(T t) {
            this.t = t;
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if(sc.hasNextInt()) {
            int num = sc.nextInt();
            // your code here
            System.out.println(new Box<>(num).getT());
        } else if (sc.hasNextFloat()) {
            float num = sc.nextFloat();
            // your code here
            System.out.println(new Box<>(num).getT());
        } else {
            String str = sc.next();
            // your code here
            System.out.println(new Box<>(str).getT());
        }
    }
}