package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD + " meet";

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    private final CommandEnum commandToPrint;

    public HelpCommand() {
        this.commandToPrint = null;
    }

    public HelpCommand(CommandEnum commandToPrint) {
        this.commandToPrint = commandToPrint;
    }

    @Override
    public CommandResult execute(Model model) {
        if (commandToPrint == null) {
            return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        } else {
            return new CommandResult(commandToPrint.getMessageUsage(), false, false);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HelpCommand // instanceof handles nulls
                && (commandToPrint == ((HelpCommand) other).commandToPrint
                || commandToPrint.equals(((HelpCommand) other).commandToPrint))); // state check
    }
}
