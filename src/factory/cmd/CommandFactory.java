package factory.cmd;

import command.Command;
import dataobject.DVD;
import def.StateManager;

import java.util.List;
import java.util.Scanner;

public interface CommandFactory {
    Command createCommand();


    /**
     * Get name of the command for display
     *
     * @return command name
     */
    String getName();
}
