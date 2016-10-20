package me.cosmin.storemypassword.Controllers;

import java.util.List;

import me.cosmin.storemypassword.Models.Card;
import me.cosmin.storemypassword.Models.Credential;
import me.cosmin.storemypassword.Models.Note;

public class NoteController {

    public NoteController() {
    }

    public static List<Note> getAllNotes() {
        List<Note> noteList = Note.listAll(Note.class);
        for( Note note : noteList ) {
            note = loadRelated(note);
        }

        return noteList;
    }

    public static Note getNoteById(long id) {
        Note note = Note.findById(Note.class, id);
        return loadRelated(note);
    }

    private static Note loadRelated(Note note) {
        note.setRecordId(note.getId());
        List<Card> cards = Card.find(Card.class, "note = ?", String.valueOf(note.getId()));
        for ( Card card : cards ) {
            card.setRecordId(card.getId());
            card.note.setRecordId(card.note.getId());
        }
        note.setCards(cards);
        List<Credential> credentials = Credential.find(Credential.class, "note = ?", String.valueOf(note.getId()));
        for ( Credential credential : credentials ) {
            credential.setRecordId(credential.getId());
            credential.note.setRecordId(credential.note.getId());
        }
        note.setCredentials(credentials);

        return note;
    }

    public static void deleteNote(Note note) {
        deleteRelated(note);
        note.delete();
    }

    private static void deleteRelated(Note note) {
        note = loadRelated(note);
        for ( Credential credential : note.getCredentials() ) {
            credential.delete();
        }
        for ( Card card : note.getCards() ) {
            card.delete();
        }
    }
}
