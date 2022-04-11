package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalNames.FULL_NAME_FIRST_PERSON;
import static seedu.address.testutil.TypicalNames.NAME_INVALID_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalHustleBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Flag;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code FlagCommand}.
 */
public class FlagCommandTest {

    private Model model = new ModelManager(getTypicalHustleBook(), new UserPrefs());

    @Test
    public void execute_validNameFlag_success() {
        Person personToFlag = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        FlagCommand flagCommand = new FlagCommand(FULL_NAME_FIRST_PERSON, new Flag("true"));

        String expectedMessage = String.format(FlagCommand.MESSAGE_FLAG_PERSON_SUCCESS, personToFlag);

        ModelManager expectedModel = new ModelManager(model.getHustleBook(), new UserPrefs());
        Person flaggedPerson = new PersonBuilder(personToFlag).withFlag("true").build();
        expectedModel.setPerson(personToFlag, flaggedPerson);

        assertCommandSuccess(flagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidNameFlag_throwsCommandException() {
        FlagCommand flagCommand = new FlagCommand(NAME_INVALID_PERSON, new Flag("true"));

        assertCommandFailure(flagCommand, model, Messages.MESSAGE_INVALID_PERSON_NAME);
    }

    @Test
    public void execute_validNameUnflag_success() {
        Person personToFlag = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person flaggedPerson = new PersonBuilder(personToFlag).withFlag("true").build();
        model.setPerson(personToFlag, flaggedPerson);

        FlagCommand flagCommand = new FlagCommand(FULL_NAME_FIRST_PERSON, new Flag("false"));

        String expectedMessage = String.format(FlagCommand.MESSAGE_FLAG_PERSON_SUCCESS, flaggedPerson);

        Model expectedModel = new ModelManager(model.getHustleBook(), new UserPrefs());
        Person unflaggedPerson = new PersonBuilder(personToFlag).withFlag("false").build();
        expectedModel.setPerson(personToFlag, unflaggedPerson);

        assertCommandSuccess(flagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidNameUnflag_throwsCommandException() {
        FlagCommand flagCommand = new FlagCommand(NAME_INVALID_PERSON, new Flag("false"));

        assertCommandFailure(flagCommand, model, Messages.MESSAGE_INVALID_PERSON_NAME);
    }

    @Test
    public void equals() {
        FlagCommand flagCommand = new FlagCommand(FULL_NAME_FIRST_PERSON, new Flag("true"));
        FlagCommand unflagCommand = new FlagCommand(FULL_NAME_FIRST_PERSON, new Flag("false"));

        // same object -> returns true
        assertTrue(flagCommand.equals(flagCommand));

        // same values -> returns true
        FlagCommand flagCommandCopy = new FlagCommand(FULL_NAME_FIRST_PERSON, new Flag("true"));
        assertTrue(flagCommand.equals(flagCommandCopy));

        // different types -> returns false
        assertFalse(flagCommand.equals(1));

        // null -> returns false
        assertFalse(flagCommand.equals(null));

        // different person -> returns false
        assertFalse(flagCommand.equals(unflagCommand));
    }
}
