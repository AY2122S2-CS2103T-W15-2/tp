package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.HelpCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside the HelpCommand code. The path variation occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class HelpCommandParserTest {

    private HelpCommandParser parser = new HelpCommandParser();

    @Test
    public void parse_noArgs_returnsEmptyHelpCommand() {
        assertParseSuccess(parser, "", new HelpCommand());
    }

    @Test
    public void parse_validArgs_returnsHelpCommand() {
        assertParseSuccess(parser, "add", new HelpCommand(Command.CommandEnum.add));
        assertParseSuccess(parser, "clear", new HelpCommand(Command.CommandEnum.clear));
        assertParseSuccess(parser, "delete", new HelpCommand(Command.CommandEnum.delete));
        assertParseSuccess(parser, "edit", new HelpCommand(Command.CommandEnum.edit));
        assertParseSuccess(parser, "exit", new HelpCommand(Command.CommandEnum.exit));
        assertParseSuccess(parser, "find", new HelpCommand(Command.CommandEnum.find));
        assertParseSuccess(parser, "flag", new HelpCommand(Command.CommandEnum.flag));
        assertParseSuccess(parser, "unflag", new HelpCommand(Command.CommandEnum.unflag));
        assertParseSuccess(parser, "help", new HelpCommand(Command.CommandEnum.help));
        assertParseSuccess(parser, "list", new HelpCommand(Command.CommandEnum.list));
        assertParseSuccess(parser, "meet", new HelpCommand(Command.CommandEnum.meet));
        assertParseSuccess(parser, "redo", new HelpCommand(Command.CommandEnum.redo));
        assertParseSuccess(parser, "sort", new HelpCommand(Command.CommandEnum.sort));
        assertParseSuccess(parser, "undo", new HelpCommand(Command.CommandEnum.undo));
    }

    @Test
    public void parseFlag_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "NotACommand",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
    }
}
