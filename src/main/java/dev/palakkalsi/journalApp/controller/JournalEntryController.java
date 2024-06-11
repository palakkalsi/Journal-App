package dev.palakkalsi.journalApp.controller;

import dev.palakkalsi.journalApp.entity.JournalEntry;
import dev.palakkalsi.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Optional;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryService.getAll();
    }

    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return myEntry;
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId){
        Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);

        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{myId}")
    public boolean deleteJournalEntryById(@PathVariable ObjectId myId){
       journalEntryService.deleteById(myId);
       return true;
    }

    @PutMapping("/id/{myId}")
    public JournalEntry updateJournalEntryById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry){

        JournalEntry old = journalEntryService.findById(myId).orElse(null);

        if(old != null){
            old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent()!=null && newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
        }
        journalEntryService.saveEntry(old);
        return old;
    }
}
