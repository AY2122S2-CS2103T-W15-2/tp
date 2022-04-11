package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MEETING_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MEETING_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MEETING_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MEETING_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CANCEL_MEETING;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalNames.FULL_NAME_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.MeetCommand;
import seedu.address.model.person.MeetingDate;
import seedu.address.model.person.MeetingTime;
import seedu.address.model.person.ScheduledMeeting;

class MeetCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, MeetCommand.MESSAGE_USAGE);

    private MeetCommandParser parser = new MeetCommandParser();

    private ScheduledMeeting validMeeting = new ScheduledMeeting(
            new MeetingDate(VALID_MEETING_DATE), new MeetingTime(VALID_MEETING_TIME));

    @Test
    public void parse_missingParts_failure() {
        // No given name
        assertParseFailure(parser, "" , MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, " ", MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, FULL_NAME_FIRST_PERSON.fullName, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // No given name
        assertParseFailure(parser, "" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, " " + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "@#$", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "@#$" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, FULL_NAME_FIRST_PERSON + " invalid/string", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, FULL_NAME_FIRST_PERSON + " invalid/ string", MESSAGE_INVALID_FORMAT);

    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser,
                FULL_NAME_FIRST_PERSON + INVALID_MEETING_DATE_DESC + MEETING_TIME_DESC,
                MeetingDate.MESSAGE_CONSTRAINTS); // invalid date

        assertParseFailure(parser,
                FULL_NAME_FIRST_PERSON + MEETING_DATE_DESC + INVALID_MEETING_TIME_DESC,
                MeetingTime.MESSAGE_CONSTRAINTS); // invalid time
    }

    @Test
    public void parse_invalidPrefixes_failure() {
        assertParseFailure(parser,
                FULL_NAME_FIRST_PERSON + MEETING_DATE_DESC
                        + MEETING_TIME_DESC + " " + PREFIX_CANCEL_MEETING,
                MESSAGE_INVALID_FORMAT); // all three prefix present

        assertParseFailure(parser,
                FULL_NAME_FIRST_PERSON + MEETING_DATE_DESC + " " + PREFIX_CANCEL_MEETING,
                MESSAGE_INVALID_FORMAT); // d/ and c/ present

        assertParseFailure(parser,
                FULL_NAME_FIRST_PERSON + MEETING_TIME_DESC + " " + PREFIX_CANCEL_MEETING,
                MESSAGE_INVALID_FORMAT); // t/ and c/ present

        assertParseFailure(parser,
                FULL_NAME_FIRST_PERSON + MEETING_TIME_DESC,
                MESSAGE_INVALID_FORMAT); // t/ present but not d/

        assertParseFailure(parser,
                FULL_NAME_FIRST_PERSON + MEETING_DATE_DESC,
                MESSAGE_INVALID_FORMAT); // d/ present but not t/
    }

    @Test
    public void parse_validValues_success() {
        // Valid schedule meeting values
        MeetCommand expectedCommand = new MeetCommand(FULL_NAME_FIRST_PERSON, validMeeting);
        assertParseSuccess(parser, FULL_NAME_FIRST_PERSON + MEETING_DATE_DESC
                + MEETING_TIME_DESC, expectedCommand);

        // Valid cancel meeting
        MeetCommand expectedCancelMeetingCommand = new MeetCommand(FULL_NAME_FIRST_PERSON, new ScheduledMeeting());
        assertParseSuccess(parser, FULL_NAME_FIRST_PERSON
                + " " + PREFIX_CANCEL_MEETING, expectedCancelMeetingCommand);
    }

}
