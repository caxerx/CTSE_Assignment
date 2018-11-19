import java.util.LinkedHashMap;
import java.util.Set;
import java.util.stream.Collectors;

public class CommandHandler {
    private LinkedHashMap<String, CommandFactory> commandFactoryMap;
    private StateManager stateManager;

    public CommandHandler(StateManager stateManager) {
        this.stateManager = stateManager;
        commandFactoryMap = new LinkedHashMap<>();
    }

    public void registerCommand(String command, CommandFactory factory) {
        commandFactoryMap.put(command, factory);
    }

    public void printCommandInstruction() {
        Set<String> commands = commandFactoryMap.keySet();
        System.out.print("Please enter command: [");
        System.out.print(String.join(" | ", commands));
        System.out.println("]");

        String descriptions = commands.stream().map(command -> command + " = " + commandFactoryMap.get(command).getName()).collect(Collectors.joining(""));
        System.out.println(descriptions);
    }

    public boolean handleCommand(String s) {
        CommandFactory commandFactory = commandFactoryMap.get(s);
        if (commandFactory == null) {
            return false;
        }
        CommandInterface command = commandFactory.createCommand();
        boolean success = command.execute();
        if (command.isUndoCommand() && success) {
            stateManager.getUndoList().add((UndoableCommand) command);
            stateManager.getRedoList().clear();
        }
        return true;
    }
}
