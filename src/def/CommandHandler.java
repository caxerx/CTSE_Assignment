package def;

import factory.cmd.CommandFactory;

import java.util.LinkedHashMap;
import java.util.Set;
import java.util.stream.Collectors;

public class CommandHandler {
    private LinkedHashMap<String, CommandFactory> commandFactoryMap;

    public CommandHandler() {
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
        String descriptions = commands.stream().map(command -> command + " = " + commandFactoryMap.get(command).getName()).collect(Collectors.joining(", "));
        System.out.println(descriptions);
    }
}
