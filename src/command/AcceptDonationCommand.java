package command;

import dataobject.DVD;
import def.StateManager;

import java.util.List;
import java.util.Scanner;

public class AcceptDonationCommand extends UndoableCommand {
    private Scanner sc;
    private List<DVD> dvdList;
    private StateManager stateManager;
    private int copies;

    public AcceptDonationCommand(Scanner sc, List<DVD> dvdList, StateManager stateManager) {
        this.sc = sc;
        this.dvdList = dvdList;
        this.stateManager = stateManager;
    }

    @Override
    public void execute() {
        System.out.println("Enter ID:");
        String id = sc.nextLine();
        int intId = 0;
        try {
            intId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid id, id can only be integer");
        }
        final int finalIntId = intId;
        DVD targetDvd = dvdList.stream().filter(dvd -> dvd.getDvdId() == finalIntId).findFirst().orElse(null);
        if (targetDvd == null) {
            System.out.println("No such dvd");
            return;
        }

        System.out.println("Enter number of copies donated:");
        String copies = sc.nextLine();
        try {
            this.copies = Integer.parseInt(copies);
        } catch (NumberFormatException e) {
            System.out.println("Invalid copies number, copies number can only be integer");
        }

        stateManager.getDvdCaretaker().backup(targetDvd.backup());
        targetDvd.setNumAvailable(targetDvd.getNumAvailable() + this.copies);
    }

    @Override
    void redo() {
        stateManager.getDvdCaretaker().redo();
    }

    @Override
    void undo() {
        stateManager.getDvdCaretaker().undo();
    }
}
