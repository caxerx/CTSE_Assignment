import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Assignment {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<DVD> dvdList = new ArrayList<>();
        StateManager stateManager = new StateManager();
        CommandHandler commandHandler = new CommandHandler(stateManager);
        commandHandler.registerCommand("c", new CreateItemCommandFactory(dvdList, sc));
        commandHandler.registerCommand("s", new ShowItemCommandFactory(dvdList, sc));
        commandHandler.registerCommand("a", new AcceptDonationCommandFactory(sc, dvdList, stateManager));
        commandHandler.registerCommand("l", new LendCommandFactory(sc, dvdList, stateManager));
        commandHandler.registerCommand("g", new ReturnCommandFactory(sc, dvdList, stateManager));
        commandHandler.registerCommand("u", new UndoCommandFactory(stateManager));
        commandHandler.registerCommand("r", new RedoCommandFactory(stateManager));
        commandHandler.registerCommand("d", new UndoRedoListCommandFactory(stateManager));
        commandHandler.registerCommand("x", new ExitCommandFactory());
        while (true) {
            System.out.println("DVD Record System");
            commandHandler.printCommandInstruction();
            String cmd = sc.nextLine();
            boolean res = commandHandler.handleCommand(cmd);
            if (!res) {
                System.out.println("No such command");
            }
        }
    }
}
