package edu.ktu.ds.lab2.utils;

import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;

/*
 * Klasė yra skirta patogiam duomenų paėmimui iš klaviatūros bei
 * efektyviam rezultatų pateikimui į sout ir serr srautus.
 * Visi metodai yra statiniai ir skirti vienam duomenų tipui.
 * studentai savarankiškai paruošia metodus dėl short ir byte skaičių tipų.
 *
 * @author eimutis
 */
public class Ks { // KTU system - imituojama Javos System klasė

    private static final BufferedReader keyboard
            = new BufferedReader(new InputStreamReader(System.in));
    private static String dataFolder = "data";

    static public String giveString(String prompt) {
        Ks.ou(prompt);
        try {
            return keyboard.readLine();
        } catch (IOException e) {
            Ks.ern("Neveikia klaviatūros srautas, darbas baigtas");
            System.exit(0);
        }
        return "";
    }

    static public long giveLong(String prompt) {
        while (true) {
            String s = giveString(prompt);
            try {
                return Long.parseLong(s);
            } catch (NumberFormatException e) {
                Ks.ern("Neteisingas skaičiaus formatas, pakartokite");
            }
        }
    }

    static public long giveLong(String prompt, long bound1, long bound2) {
        while (true) {
            long a = giveLong(prompt + " tarp ribų [" + bound1 + ":" + bound2 + "]=");
            if (a < bound1) {
                Ks.ern("Skaičius mažesnis nei leistina, pakartokite");
            } else if (a > bound2) {
                Ks.ern("Skaičius didesnis nei leistina, pakartokite");
            } else {
                return a;
            }
        }
    }

    static public int giveInt(String prompt) {
        while (true) {
            long a = giveLong(prompt);
            if (a < Integer.MIN_VALUE) {
                Ks.ern("Skaičius mažesnis nei Integer.MIN_VALUE"
                        + ", pakartokite");
            } else if (a > Integer.MAX_VALUE) {
                Ks.ern("Skaičius didesnis nei Integer.MAX_VALUE"
                        + ", pakartokite");
            } else {
                return (int) a;
            }
        }
    }

    static public int giveInt(String prompt, int bound1, int bound2) {
        return (int) giveLong(prompt, bound1, bound2);
    }

    static public double giveDouble(String prompt) {
        while (true) {
            String s = giveString(prompt);
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                if (s.contains(",")) {
                    Ks.ern("Vietoje kablelio naudokite tašką"
                            + ", pakartokite");
                } else {
                    Ks.ern("Neteisingas skaičiaus formatas"
                            + ", pakartokite");
                }
            }
        }
    }

    static public double giveDouble(String prompt, double bound1, double bound2) {
        while (true) {
            double a = giveDouble(prompt + " tarp ribų [" + bound1 + ":" + bound2 + "]=");
            if (a < bound1) {
                Ks.ern("Skaičius mažesnis nei leistina, pakartokite");
            } else if (a > bound2) {
                Ks.ern("Skaičius didesnis nei leistina, pakartokite");
            } else {
                return a;
            }
        }
    }

    static public String giveFileName() {
        File dir = new File(dataFolder);
        dir.mkdir();
        oun("Jums prieinami failai " + Arrays.toString(dir.list()));
        String fn = giveString("Nurodykite pasirinktą duomenų failo vardą: ");
        return (fn);
    }

    static public String getDataFolder() {
        return dataFolder;
    }

    static public void setDataFolder(String folderName) {
        dataFolder = folderName;
    }

    private static final PrintStream sout = System.out;
    private static final PrintStream serr = System.out;
    private static int lineNr;
    private static int errorNr;
    private static final boolean formatStartOfLine = true;

    static public void ou(Object obj) {
        if (formatStartOfLine) {
            sout.printf("%2d| %s", ++lineNr, obj);
        } else {
            sout.println(obj);
        }
    }

    static public void oun(Object obj) {
        if (formatStartOfLine) {
            sout.printf("%2d| %s\n", ++lineNr, obj);
        } else {
            sout.println(obj);
        }
    }

    static public void ounn(Object obj) { // *****nauja
        if (formatStartOfLine) {
            sout.printf("%2d|\n", ++lineNr);
            sout.printf("%s\n", obj);
        } else {
            sout.println(obj);
        }
    }

    static public void out(Object obj) {
        sout.print(obj);
    }

    static public void ouf(String format, Object... args) {
        sout.printf(format, args);
    }

    static public void er(Object obj) {
        serr.printf("***Klaida %d: %s", ++errorNr, obj);
    }

    static public void ern(Object obj) {
        serr.printf("***Klaida %d: %s\n", ++errorNr, obj);
    }

    static public void erf(String format, Object... args) {
        serr.printf(format, args);
    }

    static public LocalDate getDate() {
        return LocalDate.now();
    }
}
