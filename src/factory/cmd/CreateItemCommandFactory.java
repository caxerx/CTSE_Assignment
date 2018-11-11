package factory.cmd;

import command.Command;
import command.CreateItemCommand;
import dataobject.DVD;

import java.util.List;
import java.util.Scanner;

public class CreateItemCommandFactory implements CommandFactory {
    private Scanner sc;
    private List<DVD> dvdList;

    public CreateItemCommandFactory(List<DVD> dvdList, Scanner sc) {
        this.sc = sc;
        this.dvdList = dvdList;
    }

    @Override
    public Command createCommand() {
        return new CreateItemCommand(dvdList, sc);
    }

    @Override
    public String getName() {
        return "create item";
    }
}
