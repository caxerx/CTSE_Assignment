package factory.cmd;

import command.Command;
import command.CreateItemCommand;
import command.ShowItemCommand;
import dataobject.DVD;

import java.util.List;
import java.util.Scanner;

public class ShowItemCommandFactory implements CommandFactory {
    private Scanner sc;
    private List<DVD> dvdList;

    public ShowItemCommandFactory(List<DVD> dvdList, Scanner sc) {
        this.sc = sc;
        this.dvdList = dvdList;
    }

    @Override
    public Command createCommand() {
        return new ShowItemCommand(dvdList, sc);
    }

    @Override
    public String getName() {
        return "show item";
    }
}
