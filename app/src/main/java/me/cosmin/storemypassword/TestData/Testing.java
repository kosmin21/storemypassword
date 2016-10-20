package me.cosmin.storemypassword.TestData;

import me.cosmin.storemypassword.Controllers.NoteController;
import me.cosmin.storemypassword.Models.Card;
import me.cosmin.storemypassword.Models.Credential;
import me.cosmin.storemypassword.Models.Note;

public class Testing {

    public static void generate() {
        if ( NoteController.getAllNotes().size() == 0 ) {
            Note note = new Note("Barclays", "https://www.barclays.com", "#7986CB");
            note.save();
            Card card = new Card(note, "visa_debit", "1234567898765432", "Mr. Arnold Smith", "123", "e-pass", "1234", "2016-07-28", "2019-07-27", "12345678", "122365");
            card.save();
            card = new Card(note, "maestro", "1234567898765431", "Mr. Arnold Smith", "123", "e-pass", "1234", "2016-07-28", "2019-07-27", "12345678", "122345");
            card.save();
            card = new Card(note, "visa_credit", "1234567898765430", "Mr. Arnold Smith", "123", "e-pass", "1234", "2016-07-28", "2019-07-27", "12345678", "122345");
            card.save();
            Credential log = new Credential(note, "yemi1@yahoo.com", "paddorws");
            log.save();
            note = new Note("Natwest", "https://www.natwest.com", "#F06292");
            note.save();
            card = new Card(note, "visa_debit", "1324567898765432", "Mr. Arnold Smith", "123", "e-pass", "1234", "2016-07-28", "2019-07-27", "12345678", "122345");
            card.save();
            note = new Note("Gmail", "https://www.gmail.com", "#FFB74D");
            note.save();
            log = new Credential(note, "acc1@gmail.com", "pandorra");
            log.save();
            log = new Credential(note, "acc2@gmail.com", "pandorra");
            log.save();
        }
    }
}
