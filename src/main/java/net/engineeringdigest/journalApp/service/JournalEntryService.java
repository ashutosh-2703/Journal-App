package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    
    @Autowired
    private UserService userService;

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public JournalEntry saveNewEntry(JournalEntry journalEntry, String userName) {
        journalEntry.setDate(LocalDateTime.now());
        User userInDb = userService.findByUserName(userName);
        if (userInDb == null) {
            throw new RuntimeException("User not found");
        }
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        userInDb.getJournalEntries().add(saved);
        userService.saveUser(userInDb);
        return saved;
    }

    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public Optional<JournalEntry> getById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public boolean deleteById(ObjectId id, String userName) {
        User userInDb = userService.findByUserName(userName);
        if (userInDb == null) {
            return false;
        }
        boolean removed = userInDb.getJournalEntries().removeIf(x -> x.getId().equals(id));
        if (removed) {
            userService.saveUser(userInDb);
            journalEntryRepository.deleteById(id);
        }
        return removed;
    }
}