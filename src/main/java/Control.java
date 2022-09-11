import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Control {
    public static void main(String [] args) {
        Map<Integer, Account> ordinalNumberAcc = new HashMap<>();
        getMap(ordinalNumberAcc);

        for(Map.Entry<Integer, Account> entry : ordinalNumberAcc.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
            // TODO Check what was write inside this HashMap.
        }
        // TODO create function that launch sda application
        // TODO create fun which get account from HashMap by key and get login and set it in
    }
/*
create four different methods that get String with different data handle info inside it
and return in new Object as HashMap.
 */

    /*
    Now we need call method that will check size of HashMap ordinalNumberAcc every 10 minutes and
    if it less than 13 must launch method getMap to fill up it.
     */
    public static void getMap(Map<Integer, Account> map) {
        int counter = 0;
        String line;
        String [] splitLine;
        System.out.println("        Enter path to text file which gonna be used for launch farm");
        Scanner scanner = new Scanner(System.in);
        File file = new File(scanner.nextLine());
        try (Scanner readMe = new Scanner(file)){
            /*
             that variable COUNTER restrict maximum accounts that can launch on one pc
             without changing ip address
             */
            //todo how to read while time after time

            while(((line = readMe.nextLine()) != null)  && counter < 13) {
                splitLine = line.split(" ");
                if (splitLine.length != 5)
                    continue;
                LocalDateTime lastDrop;
                LocalDateTime now;
                try {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                    lastDrop = LocalDateTime.parse(splitLine[0], dtf);
                    now = LocalDateTime.now();
                } catch (DateTimeException e) {
                    System.out.println("There's no such string: " + line);
                    continue;
                }
                System.out.println("Hours from last drop " + ChronoUnit.HOURS.between(lastDrop,now));
                if (ChronoUnit.HOURS.between(lastDrop,now) > 168){
                    map.put(Integer.parseInt(splitLine[1]), new Account(parseDate(splitLine[0]), splitLine[2], splitLine[3], splitLine[4]));
                    counter++;
                    launchSteam();

                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("No such file");
        }
        if (counter > 0) {
                /*
                 1) launch method that open steam and set in:
                 1.1) Field nickname set nickname.
                 1.2) In field password - set password if entering was success.
                 2) Open SDA set password to enter in application:
                 2.1) Set in field filter nickname from first account.
                 2.2) Push button copy from Login token.
                 2.3) Switch to Steam application and paste 2fa code in filed autorization.
                 3) If enter in steam was success:
                 3.1) Swtich to csgo, open it (or open it by game id)
                 3.2) Wait until game is open, set in console one of ip address
                 */
            --counter;
        }
    }

    public static LocalDateTime parseDate(String str) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        LocalDateTime date = LocalDateTime.parse(str, dtf);
        System.out.println(date);
        return date;
    }

    public static void launchSteam() {
        try {
            File file = new File("C:\\Users\\ugand\\Desktop\\steam.lnk");
            System.out.println("Launch steam");
//            Process process = runTime.exec("Steam");
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String SDA(String nickname) {
        /*
        check if sda already open if it isnt open:
        open it, set password if it need.
        set in field filter nickname that was passing in argument of method.
        push button copy, set in authentication
         */
        return "";
    }
}
