package net.aoba.commands.Commands;

import net.aoba.Exception.CommandException;
import net.aoba.commands.Command;
import net.aoba.commands.CommandInfo;
import net.aoba.commands.NCommandManager;
import net.aoba.utils.chat.ChatUtil;
import net.minecraft.util.Formatting;

@CommandInfo(
        name = "help",
        usage = "[Prefix] help <command>",
        description = "Prints out all commands what there usage"
)
public class HelpCommand extends Command{
    @Override
    public void Execute(String... args) throws CommandException {
        if (args.length > 0) {
            ChatUtil.addChatMessage("\n");
            final Command commands = new NCommandManager().getCommand(args[0])
                    .orElseThrow(() ->
                            new CommandException(String.format(Formatting.RED +  "ERROR: Command \"%s\" not found!\n", args[0])));
            return;
        }
        ChatUtil.addChatMessage("\n");
        new NCommandManager()
                .getCommand()
                .values()
                .stream()
                .filter(commands -> !(commands instanceof HelpCommand))
                .forEach(commands -> ChatUtil.addChatMessage(
                        String.format(Formatting.YELLOW + "%s " + Formatting.WHITE +  "- " + Formatting.GRAY + "%s", " " + commands.getClass().getName(), commands.getClass() + "\n")
                        , true));

        ChatUtil.addChatMessage("\n");
    }
}
