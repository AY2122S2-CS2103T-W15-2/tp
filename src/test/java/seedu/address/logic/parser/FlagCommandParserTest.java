package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalNames.FULL_NAME_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FlagCommand;
import seedu.address.model.person.Flag;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside the FlagCommand code. The path variation occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class FlagCommandParserTest {

    private FlagCommandParser parser = new FlagCommandParser();

    @Test
    public void parseFlag_validArgs_returnsFlagCommand() {
        parser.setCommand("flag");
        assertParseSuccess(parser, "Alice Pauline",
                new FlagCommand(FULL_NAME_FIRST_PERSON, new Flag("true")));
    }

    @Test
    public void parseFlag_invalidArgs_throwsParseException() {
        parser.setCommand("flag");
        assertParseFailure(parser, "invalid!",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FlagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parseUnflag_validArgs_returnsFlagCommand() {
        parser.setCommand("unflag");
        assertParseSuccess(parser, "Alice Pauline",
                new FlagCommand(FULL_NAME_FIRST_PERSON, new Flag("false")));
    }

    @Test
    public void parseUnflag_invalidArgs_throwsParseException() {
        parser.setCommand("unflag");
        assertParseFailure(parser, "invalid!",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FlagCommand.MESSAGE_USAGE));
    }
}
