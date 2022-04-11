package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FlagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Flag(null));
    }

    @Test
    public void constructor_invalidFlag_throwsIllegalArgumentException() {
        String invalidFlag = "";
        assertThrows(IllegalArgumentException.class, () -> new Flag(invalidFlag));
    }

    @Test
    public void isValidFlag() {
        // null name
        assertThrows(NullPointerException.class, () -> Flag.isValidFlag(null));

        // invalid name
        assertFalse(Flag.isValidFlag("")); // empty string
        assertFalse(Flag.isValidFlag(" ")); // spaces only
        assertFalse(Flag.isValidFlag("TRUE")); // capitalised characters
        assertFalse(Flag.isValidFlag("FALSE")); // capitalised characters

        // valid name
        assertTrue(Flag.isValidFlag("true")); // flag
        assertTrue(Flag.isValidFlag("false")); // unflag
    }
}
