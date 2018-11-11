package factory.cmd;

import command.Command;
import command.ExitCommand;
import dataobject.DVD;
import def.StateManager;

import java.util.List;
import java.util.Scanner;

public class ExitCommandFactory implements CommandFactory {
    @Override
    public Command createCommand() {
        return new ExitCommand();
    }

    @Override
    public String getName() {
        return "exit system";
    }
}
