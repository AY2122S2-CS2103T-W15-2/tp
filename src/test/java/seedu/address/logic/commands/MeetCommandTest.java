package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_TIME;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalNames.FULL_NAME_FIRST_PERSON;
import static seedu.address.testutil.TypicalNames.NAME_INVALID_PERSON;
import static seedu.address.testutil.TypicalNames.NAME_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalHustleBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.MeetingDate;
import seedu.address.model.person.MeetingTime;
import seedu.address.model.person.Person;
import seedu.address.model.person.ScheduledMeeting;
import seedu.address.testutil.PersonBuilder;

class MeetCommandTest {

    private Model model = new ModelManager(getTypicalHustleBook(), new UserPrefs());

    private ScheduledMeeting validMeeting = new ScheduledMeeting(
            new MeetingDate(VALID_MEETING_DATE), new MeetingTime(VALID_MEETING_TIME));
    private ScheduledMeeting anotherMeeting = new ScheduledMeeting(
            new MeetingDate("2022-12-12"), new MeetingTime("1000"));

    @Test
    public void execute_allFieldsValidScheduleMeet_success() {
        Person personToMeet = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        MeetCommand meetCommand = new MeetCommand(FULL_NAME_FIRST_PERSON, validMeeting);

        String expectedMessage = String.format(MeetCommand.MESSAGE_SCHEDULE_MEETING_PERSON_SUCCESS, personToMeet);

        ModelManager expectedModel = new ModelManager(model.getHustleBook(), new UserPrefs());
        Person scheduledPerson = new PersonBuilder(personToMeet)
                .withScheduledMeeting(VALID_MEETING_DATE, VALID_MEETING_TIME).build();
        expectedModel.setPerson(personToMeet, scheduledPerson);

        assertCommandSuccess(meetCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_allFieldsValidCancelMeet_success() {
        Person personToMeet = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        MeetCommand meetCommand = new MeetCommand(FULL_NAME_FIRST_PERSON, new ScheduledMeeting());

        String expectedMessage = String.format(MeetCommand.MESSAGE_SCHEDULE_MEETING_PERSON_SUCCESS, personToMeet);

        ModelManager expectedModel = new ModelManager(model.getHustleBook(), new UserPrefs());
        Person scheduledPerson = new PersonBuilder(personToMeet).withScheduledMeeting().build();
        expectedModel.setPerson(personToMeet, scheduledPerson);

        assertCommandSuccess(meetCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidName_failure() {
        MeetCommand meetCommand = new MeetCommand(NAME_INVALID_PERSON,
                new ScheduledMeeting(new MeetingDate(VALID_MEETING_DATE), new MeetingTime(VALID_MEETING_TIME)));

        assertCommandFailure(meetCommand, model, Messages.MESSAGE_INVALID_PERSON_NAME);
    }

    @Test
    public void equals() {
        MeetCommand meetCommand = new MeetCommand(FULL_NAME_FIRST_PERSON, validMeeting);
        MeetCommand meetCommandToCancel = new MeetCommand(FULL_NAME_FIRST_PERSON, new ScheduledMeeting());

        // same values -> returns true
        MeetCommand commandWithSameValue = new MeetCommand(FULL_NAME_FIRST_PERSON, validMeeting);
        assertTrue(meetCommand.equals(commandWithSameValue));

        // same object -> returns true
        assertTrue(meetCommand.equals(meetCommand));
        assertTrue(meetCommandToCancel.equals(meetCommandToCancel));

        // null -> returns false
        assertFalse(meetCommand.equals(null));
        assertFalse(meetCommandToCancel.equals(null));

        // different types -> returns false
        assertFalse(meetCommand.equals(new ClearCommand()));
        assertFalse(meetCommandToCancel.equals(new ClearCommand()));

        // different name -> returns false
        assertFalse(meetCommand.equals(new MeetCommand(NAME_SECOND_PERSON, validMeeting)));

        // different descriptor -> returns false
        assertFalse(meetCommand.equals(new MeetCommand(FULL_NAME_FIRST_PERSON, anotherMeeting)));
        assertFalse(meetCommand.equals(meetCommandToCancel));
    }
}
