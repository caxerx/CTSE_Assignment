import java.util.List;
import java.util.Scanner;

public interface CommandFactory {
    CommandInterface createCommand();


    /**
     * Get name of the command for display
     *
     * @return command name
     */
    String getName();
}

class CreateItemCommandFactory implements CommandFactory {
    private Scanner sc;
    private List<DVD> dvdList;

    public CreateItemCommandFactory(List<DVD> dvdList, Scanner sc) {
        this.sc = sc;
        this.dvdList = dvdList;
    }

    @Override
    public CommandInterface createCommand() {
        return new CreateItemCommand(dvdList, sc);
    }

    @Override
    public String getName() {
        return "create item, ";
    }
}

class ExitCommandFactory implements CommandFactory {
    @Override
    public Command createCommand() {
        return new ExitCommand();
    }

    @Override
    public String getName() {
        return "exit system";
    }
}

class UndoRedoListCommandFactory implements CommandFactory {
    private StateManager stateManager;

    public UndoRedoListCommandFactory(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    @Override
    public Command createCommand() {
        return new UndoRedoListCommand(stateManager);
    }

    @Override
    public String getName() {
        return "display undo/redo list, ";
    }
}

class UndoCommandFactory implements CommandFactory {
    private StateManager stateManager;

    public UndoCommandFactory(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    @Override
    public Command createCommand() {
        return new UndoCommand(stateManager);
    }

    @Override
    public String getName() {
        return "undo, ";
    }
}

class LendCommandFactory implements CommandFactory {
    private Scanner sc;
    private List<DVD> dvdList;
    private StateManager stateManager;

    public LendCommandFactory(Scanner sc, List<DVD> dvdList, StateManager stateManager) {
        this.sc = sc;
        this.dvdList = dvdList;
        this.stateManager = stateManager;
    }

    @Override
    public CommandInterface createCommand() {
        return new LendCommand(sc, dvdList, stateManager);
    }

    @Override
    public String getName() {
        return "lend out a DVD,\n";
    }
}

class ReturnCommandFactory implements CommandFactory {
    private Scanner sc;
    private List<DVD> dvdList;
    private StateManager stateManager;

    public ReturnCommandFactory(Scanner sc, List<DVD> dvdList, StateManager stateManager) {
        this.sc = sc;
        this.dvdList = dvdList;
        this.stateManager = stateManager;
    }

    @Override
    public CommandInterface createCommand() {
        return new ReturnCommand(sc, dvdList, stateManager);
    }

    @Override
    public String getName() {
        return "get back a returned DVD, ";
    }
}

class RedoCommandFactory implements CommandFactory {
    private StateManager stateManager;

    public RedoCommandFactory(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    @Override
    public Command createCommand() {
        return new RedoCommand(stateManager);
    }

    @Override
    public String getName() {
        return "redo, ";
    }
}

class ShowItemCommandFactory implements CommandFactory {
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
        return "show item, ";
    }
}

class AcceptDonationCommandFactory implements CommandFactory {
    private Scanner sc;
    private List<DVD> dvdList;
    private StateManager stateManager;

    public AcceptDonationCommandFactory(Scanner sc, List<DVD> dvdList, StateManager stateManager) {
        this.sc = sc;
        this.dvdList = dvdList;
        this.stateManager = stateManager;
    }

    @Override
    public CommandInterface createCommand() {
        return new AcceptDonationCommand(sc, dvdList, stateManager);
    }

    @Override
    public String getName() {
        return "accept donation of DVD, ";
    }
}
