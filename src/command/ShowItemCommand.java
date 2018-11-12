package command;

import dataobject.DVD;

import java.util.List;
import java.util.Scanner;

public class ShowItemCommand implements Command {
    private List<DVD> dvdList;
    private Scanner scanner;

    public ShowItemCommand(List<DVD> dvdList, Scanner sc) {
        this.dvdList = dvdList;
        this.scanner = sc;
    }

    @Override
    public void execute() {
        System.out.println("Enter ID (a to show all): ");
        String id = scanner.nextLine();
        if (id.equalsIgnoreCase("a")) {
            dvdList.forEach(i -> {
                System.out.println("ID  Title    Length (mins)   No. available   Other Info");
                System.out.println(i.toListString());
            });
            return;
        }
        int intID = 0;
        try {
            intID = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            System.out.println("Please enter \"a\" or an integer id");
        }
        final int fId = intID;
        DVD rDvd = dvdList.stream().filter(dvd -> dvd.getDvdId() == fId).findFirst().orElse(null);
        if (rDvd == null) {
            System.out.println("No DVD with id " + fId + " found");
            return;
        }
        System.out.println(rDvd);


    }
}
