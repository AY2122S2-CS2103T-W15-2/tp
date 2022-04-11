package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpAdd_success() {
        Command.CommandEnum sortCommand = Command.CommandEnum.valueOf("add");
        CommandResult expectedCommandResult = new CommandResult(AddCommand.MESSAGE_USAGE, false, false);
        assertCommandSuccess(new HelpCommand(sortCommand), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpDelete_success() {
        Command.CommandEnum sortCommand = Command.CommandEnum.valueOf("delete");
        CommandResult expectedCommandResult = new CommandResult(DeleteCommand.MESSAGE_USAGE, false, false);
        assertCommandSuccess(new HelpCommand(sortCommand), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpSort_success() {
        Command.CommandEnum sortCommand = Command.CommandEnum.valueOf("sort");
        CommandResult expectedCommandResult = new CommandResult(SortCommand.MESSAGE_USAGE, false, false);
        assertCommandSuccess(new HelpCommand(sortCommand), model, expectedCommandResult, expectedModel);
    }
}
