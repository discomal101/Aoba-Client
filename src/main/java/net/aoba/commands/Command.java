package net.aoba.commands;

import net.aoba.AobaClient;
import net.aoba.cmd.CommandException;
import net.minecraft.client.MinecraftClient;
import org.apache.commons.lang3.Validate;

public abstract class Command {
    protected static final MinecraftClient mc = AobaClient.MC;

    protected final String name;
    protected final String description;
    protected final String syntax;

    public Command() {
        final CommandInfo commandInfo = this.getClass().getAnnotation(CommandInfo.class);
        Validate.notNull(commandInfo, "CONFUSED ANNOTATION EXCEPTION");
        this.name = commandInfo.name();
        this.description = commandInfo.description();
        this.syntax = commandInfo.syntax();
    }

    public abstract void Execute(String... args) throws CommandEx;
}
