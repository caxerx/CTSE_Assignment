package factory.cmd;

import command.AcceptDonationCommand;
import command.Command;
import dataobject.DVD;
import def.StateManager;

import java.util.List;
import java.util.Scanner;

public class AcceptDonationCommandFactory implements CommandFactory {
    private Scanner sc;
    private List<DVD> dvdList;
    private StateManager stateManager;

    public AcceptDonationCommandFactory(Scanner sc, List<DVD> dvdList, StateManager stateManager) {
        this.sc = sc;
        this.dvdList = dvdList;
        this.stateManager = stateManager;
    }

    @Override
    public Command createCommand() {
        return new AcceptDonationCommand(sc, dvdList, stateManager);
    }

    @Override
    public String getName() {
        return "accept donation of DVD";
    }
}
