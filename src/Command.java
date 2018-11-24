import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * The command that unable to redo
 */
public abstract class Command implements CommandInterface {
    @Override
    public final boolean isUndoCommand() {
        return false;
    }
}


/**
 * Interface of the command
 */
interface CommandInterface {
    /**
     * Execute the action of the command
     *
     * @return the command executed with no input error or exception
     */
    boolean execute();

    /**
     * To check whether the command is undoable
     *
     * @return the command can undo or not
     */
    boolean isUndoCommand();
}

/**
 * A command that able to undo and redo
 */
abstract class UndoableCommand implements CommandInterface {
    @Override
    public final boolean isUndoCommand() {
        return true;
    }

    /**
     * Redo the action after undo
     */
    public abstract void redo();

    /**
     * Undo the action
     */
    public abstract void undo();
}

class AcceptDonationCommand extends UndoableCommand {
    private Scanner sc;
    private List<DVD> dvdList;
    private StateManager stateManager;
    private int copies;
    private DVD dvd;

    public AcceptDonationCommand(Scanner sc, List<DVD> dvdList, StateManager stateManager) {
        this.sc = sc;
        this.dvdList = dvdList;
        this.stateManager = stateManager;
    }

    @Override
    public boolean execute() {
        System.out.println("Enter ID:");
        String id = sc.nextLine();
        int intId = 0;
        try {
            intId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid id, id can only be integer");
        }
        final int finalIntId = intId;
        dvd = dvdList.stream().filter(dvd -> dvd.getDvdId() == finalIntId).findFirst().orElse(null);
        if (dvd == null) {
            System.out.println("No such dvd");
            return false;
        }

        System.out.println("Enter number of copies donated:");
        String copies = sc.nextLine();
        try {
            this.copies = Integer.parseInt(copies);
        } catch (NumberFormatException e) {
            System.out.println("Invalid copies number, copies number can only be integer");
        }

        stateManager.getDvdCaretaker().backup(dvd.backup());
        dvd.setNumAvailable(dvd.getNumAvailable() + this.copies);
        System.out.println("Donation accepted: " + dvd.getTitle());
        System.out.println("Number of available copies: " + dvd.getNumAvailable());
        System.out.println();
        return true;
    }

    @Override
    public void redo() {
        stateManager.getDvdCaretaker().redo();
    }

    @Override
    public void undo() {
        stateManager.getDvdCaretaker().undo();
    }

    @Override
    public String toString() {
        return "Accept " + dvd.getDvdId() + " " + dvd.getTitle() + " (" + copies + " copies)";
    }
}


class CreateItemCommand extends UndoableCommand {
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
    public boolean execute() {
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
                return false;
        }
        System.out.println("Enter id, title, length, number of available copies, " + extra + ":");
        String parameter = scanner.nextLine();
        String[] parameters = parameter.split(",");

        if (parameters.length != 5) {
            System.out.println("Invalid parameter length");
            return false;
        }

        int numAvailable = 0;
        int dvdId = 0;
        int length = 0;
        try {
            dvdId = Integer.parseInt(parameters[0].trim());
            final int finalDvdId = dvdId;
            DVD targetDvd = dvdList.stream().filter(dvd -> dvd.getDvdId() == finalDvdId).findFirst().orElse(null);
            if (targetDvd != null) {
                System.out.println("DVD with id " + dvdId + " already exist");
                System.out.println();
                return false;
            }

            length = Integer.parseInt(parameters[2].trim());
            numAvailable = Integer.parseInt(parameters[3].trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid parameter: \"dvd id\", \"length\" and \"number of avaliable copies\" should be integer");
            return false;
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
        System.out.println("DVD record created.\n");
        return true;
    }

    @Override
    public String toString() {
        return "Create " + item.getDvdId() + " " + item.getTitle();
    }
}


class LendCommand extends UndoableCommand {
    private Scanner sc;
    private List<DVD> dvdList;
    private StateManager stateManager;
    private DVD dvd;

    public LendCommand(Scanner sc, List<DVD> dvdList, StateManager stateManager) {
        this.sc = sc;
        this.dvdList = dvdList;
        this.stateManager = stateManager;
    }

    @Override
    public boolean execute() {
        System.out.println("Enter ID:");
        String id = sc.nextLine();
        int intId = 0;
        try {
            intId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid id, id can only be integer");
            return false;
        }
        final int finalIntId = intId;
        dvd = dvdList.stream().filter(dvd -> dvd.getDvdId() == finalIntId).findFirst().orElse(null);
        if (dvd == null) {
            System.out.println("No such dvd");
            return false;
        }

        if (dvd.getNumAvailable() <= 0) {
            System.out.println("Invalid request (" + dvd.getTitle() + " has no available copy).");
            System.out.println();
            return false;
        }

        stateManager.getDvdCaretaker().backup(dvd.backup());
        dvd.setNumAvailable(dvd.getNumAvailable() - 1);

        System.out.println("Lent out: " + dvd.getTitle());
        System.out.println("Number of available copies: " + dvd.getNumAvailable());
        System.out.println();
        return true;
    }

    @Override
    public void redo() {
        stateManager.getDvdCaretaker().redo();
    }

    @Override
    public void undo() {
        stateManager.getDvdCaretaker().undo();
    }

    @Override
    public String toString() {
        return "Lend " + dvd.getDvdId() + " " + dvd.getTitle();
    }
}

class ExitCommand extends Command {
    @Override
    public boolean execute() {
        System.out.println("Exiting System...");
        System.exit(0);
        return true;
    }
}


class RedoCommand extends Command {
    private StateManager stateManager;

    public RedoCommand(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    @Override
    public boolean execute() {
        Stack<UndoableCommand> redoList = stateManager.getRedoList();
        if (redoList.isEmpty()) {
            System.out.println("Nothing to redo");
            return false;
        }
        UndoableCommand command = redoList.pop();
        stateManager.getUndoList().push(command);
        command.redo();
        System.out.println("redo completed.");
        return true;
    }
}

class UndoCommand extends Command {
    private StateManager stateManager;

    public UndoCommand(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    @Override
    public boolean execute() {
        Stack<UndoableCommand> undoList = stateManager.getUndoList();
        if (undoList.isEmpty()) {
            System.out.println("Nothing to undo");
            return false;
        }
        UndoableCommand command = undoList.pop();
        stateManager.getRedoList().push(command);
        command.undo();
        System.out.println("undo completed.");
        return true;
    }
}


class ReturnCommand extends UndoableCommand {
    private Scanner sc;
    private List<DVD> dvdList;
    private StateManager stateManager;
    private DVD dvd;

    public ReturnCommand(Scanner sc, List<DVD> dvdList, StateManager stateManager) {
        this.sc = sc;
        this.dvdList = dvdList;
        this.stateManager = stateManager;
    }

    @Override
    public boolean execute() {
        System.out.println("Enter ID:");
        String id = sc.nextLine();
        int intId = 0;
        try {
            intId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid id, id can only be integer");
            return false;
        }
        final int finalIntId = intId;
        dvd = dvdList.stream().filter(dvd -> dvd.getDvdId() == finalIntId).findFirst().orElse(null);
        if (dvd == null) {
            System.out.println("No such dvd");
            return false;
        }

        stateManager.getDvdCaretaker().backup(dvd.backup());
        dvd.setNumAvailable(dvd.getNumAvailable() + 1);

        System.out.println("Returned: " + dvd.getTitle());
        System.out.println("Number of available copies: " + dvd.getNumAvailable());
        System.out.println();
        return true;
    }

    @Override
    public void redo() {
        stateManager.getDvdCaretaker().redo();
    }

    @Override
    public void undo() {
        stateManager.getDvdCaretaker().undo();
    }

    @Override
    public String toString() {
        return "Get back " + dvd.getDvdId() + " " + dvd.getTitle();
    }
}

class UndoRedoListCommand extends Command {
    private StateManager stateManager;

    public UndoRedoListCommand(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    @Override
    public boolean execute() {
        System.out.println("Undo List:");
        if (!stateManager.getUndoList().isEmpty()) {
            stateManager.getUndoList().forEach(System.out::println);
        } else {
            System.out.println("Empty");
        }
        System.out.println();
        System.out.println("Redo List:");
        if (!stateManager.getRedoList().isEmpty()) {
            stateManager.getRedoList().forEach(System.out::println);
        } else {
            System.out.println("Empty");
        }
        System.out.println();
        return true;
    }
}

class ShowItemCommand extends Command {
    private List<DVD> dvdList;
    private Scanner scanner;

    public ShowItemCommand(List<DVD> dvdList, Scanner sc) {
        this.dvdList = dvdList;
        this.scanner = sc;
    }

    @Override
    public boolean execute() {
        System.out.println("Enter ID (a to show all):");
        String id = scanner.nextLine();
        if (id.equalsIgnoreCase("a")) {
            System.out.println("\nDVD information");
            System.out.println("ID\tTitle\t      \tLength(mins)\tNo.available\tOther Info");
            dvdList.forEach(i -> System.out.println(i.toListString()));
            System.out.println();
            return true;
        }
        int intID = 0;
        try {
            intID = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            System.out.println("Please enter \"a\" or an integer id");
            return false;
        }
        final int fId = intID;
        DVD rDvd = dvdList.stream().filter(dvd -> dvd.getDvdId() == fId).findFirst().orElse(null);
        if (rDvd == null) {
            System.out.println("No DVD with id " + fId + " found");
            return false;
        }
        System.out.println("\nDVD information");
        System.out.println(rDvd);
        System.out.println();
        return true;

    }
}
