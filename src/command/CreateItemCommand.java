package command;

import dataobject.DVD;
import dataobject.MV;
import dataobject.Movie;
import factory.dvd.DVDFactory;
import factory.dvd.MVFactory;
import factory.dvd.MovieFactory;

import java.util.List;
import java.util.Scanner;

public class CreateItemCommand extends UndoableCommand {
    private List<DVD> dvdList;
    private Scanner scanner;
    private DVD item;

    public CreateItemCommand(List<DVD> dvdList, Scanner sc) {
        this.dvdList = dvdList;
        this.scanner = sc;
    }

    @Override
    public void redo() {
        dvdList.add(item);
    }

    @Override
    public void undo() {
        dvdList.remove(item);
    }

    @Override
    public void execute() {
        System.out.println("Enter DVD type (mo=movie/mv=MV):");
        String type = scanner.nextLine();
        DVDFactory factory;
        String extra = "";
        switch (type) {
            case "mo":
                factory = new MovieFactory();
                extra = "director";
                break;
            case "mv":
                factory = new MVFactory();
                extra = "singer";
                break;
            default:
                System.out.println("Invalid DVD Type");
                return;
        }
        System.out.println("Enter id, title, length, number of available copies, " + extra + ":");
        String parameter = scanner.nextLine();
        String[] parameters = parameter.split(",");

        if (parameters.length != 5) {
            System.out.println("Invalid parameter length");
            return;
        }

        int numAvailable = 0;
        int dvdId = 0;
        int length = 0;
        try {
            dvdId = Integer.parseInt(parameters[0].trim());
            length = Integer.parseInt(parameters[2].trim());
            numAvailable = Integer.parseInt(parameters[3].trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid parameter: \"dvd id\", \"length\" and \"number of avaliable copies\" should be integer");
            return;
        }
        item = factory.createDVD(dvdId, parameters[1].trim(), length, numAvailable);
        switch (type) {
            case "mo":
                ((Movie) item).setDirector(parameters[4].trim());
                break;
            case "mv":
                ((MV) item).setSinger(parameters[4].trim());
                break;
        }
        dvdList.add(item);
    }
}
