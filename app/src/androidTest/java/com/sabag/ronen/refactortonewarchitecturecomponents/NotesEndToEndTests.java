package com.sabag.ronen.refactortonewarchitecturecomponents;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.sabag.ronen.refactortonewarchitecturecomponents.notesList.NotesActivity;
import com.sabag.ronen.refactortonewarchitecturecomponents.storage.INotesTable;
import com.sabag.ronen.refactortonewarchitecturecomponents.storage.notesTableImpl.NoteData;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class NotesEndToEndTests {

    @Rule public ActivityTestRule<NotesActivity> mActivityTestRule =
            new ActivityTestRule<NotesActivity>(NotesActivity.class) {
                @Override
                protected void beforeActivityLaunched() {
                    super.beforeActivityLaunched();
                    INotesTable notesTable =
                            ((App) InstrumentationRegistry.getTargetContext().getApplicationContext()).getNotesTable();
                    List<NoteData> allNotes = notesTable.getAll();
                    for (NoteData note : allNotes) {
                        notesTable.delete(note.getId());
                    }
                }
            };

    @Test
    public void addedNoteShouldAppear() throws InterruptedException {
        onView(withId(R.id.addNote)).perform(click());
        onView(withId(R.id.noteText)).perform(typeText("tests are cool"));
        onView(withId(R.id.createNote)).perform(click());
        onView(withText("tests are cool")).check(matches(isDisplayed()));
    }

    @Test
    public void deleteExistNote() {
        onView(withId(R.id.addNote)).perform(click());
        onView(withId(R.id.noteText)).perform(typeText("I about to deleted"));
        onView(withId(R.id.createNote)).perform(click());
        onView(withText("I about to deleted")).perform(click());
        onView(withId(R.id.delete)).perform(click());
        onView(withText("tests are cool")).check(doesNotExist());
    }

    @Test
    public void updateExistNote() {
        onView(withId(R.id.addNote)).perform(click());
        onView(withId(R.id.noteText)).perform(typeText("I'll be changed"));
        onView(withId(R.id.createNote)).perform(click());
        onView(withText("I'll be changed")).perform(click());
        onView(withId(R.id.noteText)).perform(clearText());
        onView(withId(R.id.noteText)).perform(typeText("I have changed"));
        onView(withId(R.id.update)).perform(click());
        onView(withText("I'll be changed")).check(doesNotExist());
        onView(withText("I have changed")).check(matches(isDisplayed()));
    }
}
