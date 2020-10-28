package com.ladasfeed;

import java.util.Scanner;

public class CheckInput {
    public static int Check(int Var, int In) {
        if (Var > 0 || Var == 0)
            if (In > 0 && In <= Var) {
                return In;
            } else {
                System.out.println("Пожалуйста, введите корректное значение:");
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                In = Integer.parseInt(input);
                return  CheckInput.Check(Var, In);

            }
        return In;
    }
}
