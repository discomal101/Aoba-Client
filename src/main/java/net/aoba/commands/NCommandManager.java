package net.aoba.commands;


import lombok.Getter;
import net.aoba.AobaClient;
import net.aoba.Exception.CommandException;
import net.aoba.utils.chat.ChatUtil;
import org.reflections.Reflections;

import java.util.*;

@Getter
public final class NCommandManager {
    public static final String COMMAND_PREFIX = AobaClient.COMMAND_PREFIX;

    private Map<String, Command> command;

    public NCommandManager() {
        this.Init();
    }

    private void Init() {
        this.command = new HashMap<>();

        try {
            this.register();
        } catch (CommandException e) {
            throw new CommandException("Failed to register commands!");
        }
    }

    public boolean HandleCommand(final String message) {
        if (message.isEmpty()) return false;

        final String[] args = message.substring(1).split(" ");

        if (message.equalsIgnoreCase(COMMAND_PREFIX)) {
            ChatUtil.addChatMessage(String
                            .format("Type %shelp to get a list of all commands and their usage \n Type %s <command> to see infomation for a specific command\n", COMMAND_PREFIX, COMMAND_PREFIX));
            return true;
        }

        try {
            this.getCommand(args[0]).orElseThrow(() ->
                    new CommandException(String .format("ERROR: \n Command \"%s\" not found. \n Use %shelp to see the full list of commands!", args[0], COMMAND_PREFIX)))
                    .Execute(Arrays.copyOfRange(args, 1, args.length));
        } catch (CommandException ignored) {
        }

        return true;
    }

    private void register() throws CommandException {
        final Reflections reflections = new Reflections("net.aoba.commands.Commands");
        final Set<Class<? extends Command>> classes = reflections.getSubTypesOf(Command.class);

        for (Class<? extends Command> command : classes) {
            try {
                final Command commands = command.newInstance();
                this.command.put(command.getName(), commands);
            } catch (InstantiationException | IllegalAccessException e) {
                System.err.println("Failed To Initialize Command" + command.getName());
            }
        }
    }

    public Optional<Command> getCommand(final String commandName) {
        final Command command = this.command.get(commandName);
        if (command != null) {
            return Optional.of(command);
        } else {
            return Optional.ofNullable(this.command.values()
                    .stream()
                    .findFirst()
                    .orElseThrow(() ->
                            new CommandException("Couldn't Find Command Specified")));
        }
    }
}
