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

    /**
     * Register the command to system
     *
     * @param command the command string that used to execute command
     * @param factory the factory of the command
     */
    public void registerCommand(String command, CommandFactory factory) {
        commandFactoryMap.put(command, factory);
    }

    /**
     * print instruction of commands, auto generated based on registed command
     */
    public void printCommandInstruction() {
        Set<String> commands = commandFactoryMap.keySet();
        System.out.print("Please enter command: [");
        System.out.print(String.join(" | ", commands));
        System.out.println("]");

        String descriptions = commands.stream().map(command -> command + " = " + commandFactoryMap.get(command).getName()).collect(Collectors.joining(""));
        System.out.println(descriptions);
    }

    /**
     * handle the inputted command
     *
     * @param s the inputted command
     * @return true if the command executed, false if the command not exist.
     */
    public boolean handleCommand(String s) {
        CommandFactory commandFactory = commandFactoryMap.get(s);
        if (commandFactory == null) {
            return false;
        }
        CommandInterface command = commandFactory.createCommand();
        boolean success = command.execute();

        //If the command is able to undo and it executed successfully, add it to undo list and reset redo list.
        if (command.isUndoCommand() && success) {
            stateManager.getUndoList().add((UndoableCommand) command);
            stateManager.getRedoList().clear();
        }
        return true;
    }
}
