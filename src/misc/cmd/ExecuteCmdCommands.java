package misc.cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * <b>Description</b> : Program to execute any cmd command from within java code
 *
 * @author Vinod Akkepalli
 */
public class ExecuteCmdCommands {

    public static void main(String[] args) throws IOException {

        ArrayList<String> commandsList = new ArrayList<>();
        commandsList.add("cd D:\\JavaPracticeProjects && dir");
        commandsList.add("java --version");
        commandsList.add("notepad.exe");

        ProcessBuilder builder;
        for (String command : commandsList) {
            builder = new ProcessBuilder("cmd.exe", "/c", command);
            builder.redirectErrorStream(true);
            Process p = builder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = reader.readLine();
                if (line == null) break;
                System.out.println(line);
            }
        }
    }
}
