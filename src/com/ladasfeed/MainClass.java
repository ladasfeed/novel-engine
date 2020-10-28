package com.ladasfeed;

import com.sun.tools.javac.Main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;
import javax.swing.JFrame;


public class MainClass {
    public static void main(String[] args) throws IOException {
        Chars Mouse = new Chars("Mouse");
        Chars Tris = new Chars("Triss");


        String MainIndexStr="";
        int Variant=1;
        int FlagFirstEnter = 0;
        int FlagHeroes = 0;
        int VarForChecking = 0;
        int TempForInput = 0;
        String[] massPath = new String[4];
        String NameOfChar="";

        while (true) {
            int[] MassOfRates = new int[4];

            //creating new File
            StringBuilder TempString ;


                if (FlagFirstEnter == 1) {
                    TempString = new StringBuilder("TextFiles/");
                    TempString.append(MainIndexStr);
                }
                else  {
                    TempString = new StringBuilder("TextFiles/MainText1"); //FirstFile
                    FlagFirstEnter = 1;
                }
                TempString.append(".txt");

                 String path = TempString.toString();

                 List<String> lines = Files.readAllLines(Paths.get(path));








            //parsing parameter
            String lastSent = lines.get(lines.size()-1);
            Variant = Integer.parseInt(lastSent);
            lines.remove(lines.size()-1);
            VarForChecking = Variant;



            if (Variant==0) { //Rating
                FlagHeroes=1;

                int CountOfRates = Integer.parseInt(lines.get(lines.size() - 1));
                VarForChecking = CountOfRates;
                lines.remove(lines.size()-1);
                for (int i =0; i < CountOfRates; i++) {
                    MassOfRates[i]=Integer.parseInt(lines.get(lines.size() - 1));
                    lines.remove(lines.size()-1);
                    massPath[i] = lines.get(lines.size() - 1);
                    lines.remove(lines.size()-1);
                }


            }
            else {

                if (Variant!=-1 && (Variant != -2)) { //Usual Interface

                    for (int i = 0; i < Variant; i++) {
                        massPath[i] = lines.get(lines.size() - 1);
                        lines.remove(lines.size() - 1);
                    }


                } else { //Checking events
                    if (Variant != -2) { //if not exit
                        NameOfChar = lines.get(lines.size() - 1);
                        lines.remove(lines.size() - 1);
                        if (Mouse.getName().equals(NameOfChar)) {
                            if (Mouse.getRating() > 2) {
                                Mouse.setChecks(true);
                            }
                            System.out.println(Mouse.getRating());
                            Mouse.Flag = true;

                        } else {
                            Tris.setChecks(Boolean.parseBoolean(lines.get(lines.size() - 1)));
                            lines.remove(lines.size() - 1);
                            Tris.Flag = true;
                        }

                        if (!Mouse.Flag) {
                            MainIndexStr = "Mouse1";
                        }
                        if (!Tris.Flag) {
                            MainIndexStr = "MainTris1";
                        }
                        if (Mouse.Flag && Tris.Flag) {
                            if (Mouse.getChecks()) {
                                MainIndexStr = "MouseHelp1";
                            }
                            if (Tris.getChecks()) {
                                MainIndexStr = "TrissHelp1";

                            }
                            if (Mouse.getChecks() && Tris.getChecks()) {
                                MainIndexStr = "MTHelp1";
                            }
                            if (!Mouse.getChecks() && !Tris.getChecks()) {
                                MainIndexStr = "NoHelp1";
                            }
                        }
                    }
                }
            }


            //Output
            for (String s : lines) {
                System.out.println(s);
            }
            if (Variant==-2) break;

            //Input
            Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                TempForInput = Integer.parseInt(input);
                TempForInput = CheckInput.Check(VarForChecking, TempForInput);
                if (Variant!=-1) { //if var==-1, input does not matter
                    if (FlagHeroes != 1) {
                        MainIndexStr = massPath[TempForInput - 1];
                    } else {
                        MainIndexStr = massPath[TempForInput - 1];
                        Mouse.Rate(MassOfRates[TempForInput - 1]);
                        FlagHeroes=0;
                    }
                }
        }
    }
}

    class Chars { //Class is about persons in quest, it helps to make reputation
    private String name;
    Chars (String name) {
        this.name = name;
    }
    private int Rating=0;
    public boolean Flag=false;
    private boolean Checks;
    public void setChecks(boolean a) {
            Checks = a;
        }
        public boolean getChecks() {
           return Checks;
        }
    public String getName() {
        return name;
    }
    public int getRating() {
        return Rating;
    }
    public void Rate(int rate) {
        Rating += rate;
    }
 }

