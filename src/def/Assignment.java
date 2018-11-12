package def;

import dataobject.DVD;
import factory.cmd.CreateItemCommandFactory;
import factory.cmd.ExitCommandFactory;
import factory.cmd.ShowItemCommandFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Assignment {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<DVD> dvdList = new ArrayList<DVD>();
        CommandHandler commandHandler = new CommandHandler();
        commandHandler.registerCommand("c", new CreateItemCommandFactory(dvdList, sc));
        commandHandler.registerCommand("s", new ShowItemCommandFactory(dvdList, sc));
        commandHandler.registerCommand("x", new ExitCommandFactory());
        while (true) {
            System.out.println("DVD Record System");
            commandHandler.printCommandInstruction();
            String cmd = sc.nextLine();
            boolean res = commandHandler.handleCommand(cmd);
            if (!res) {
                System.out.println("No such command");
            }
        }

    }
}
