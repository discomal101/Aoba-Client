package net.aoba.commands;

import net.aoba.AobaClient;
import net.aoba.Exception.CommandException;
import net.minecraft.client.MinecraftClient;
import org.apache.commons.lang3.Validate;

public abstract class Command {
    protected static final MinecraftClient mc = AobaClient.MC;

    private final String name;
    private final String description;
    private final String usage;

    public Command() {
        final CommandInfo commandInfo = this.getClass().getAnnotation(CommandInfo.class);
        Validate.notNull(commandInfo, "CONFUSED ANNOTATION EXCEPTION");
        this.name = commandInfo.name();
        this.description = commandInfo.description();
        this.usage = commandInfo.usage();
    }

    public abstract void Execute(String... args) throws CommandException;
}
