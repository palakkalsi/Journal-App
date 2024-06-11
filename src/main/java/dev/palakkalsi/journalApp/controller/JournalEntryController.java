package dev.palakkalsi.journalApp.controller;

import dev.palakkalsi.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private HashMap<Long, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry){
        journalEntries.put(myEntry.getId(), myEntry);
        return true;
    }

    @GetMapping("/id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable Long myId){
        return journalEntries.get(myId);
    }

    @DeleteMapping("/id/{myId}")
    public void deleteJournalEntryById(@PathVariable Long myId){
        journalEntries.remove(myId);
    }

    @PutMapping("/id/{myId}")
    public void updateJournalEntryById(@PathVariable Long myId, @RequestBody JournalEntry updatedEntry){
        journalEntries.put(myId,updatedEntry);
    }
}
