package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    @Transactional
    public JournalEntry saveNewEntry(JournalEntry journalEntry, String userName) {
        try{
            journalEntry.setDate(LocalDateTime.now());
            User userInDb = userService.findByUserName(userName);
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            userInDb.getJournalEntries().add(saved);
            userService.saveUser(userInDb);
            return saved;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public Optional<JournalEntry> getById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName) {
        boolean removed = false;
        try {
            User userInDb = userService.findByUserName(userName);
            removed = userInDb.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveUser(userInDb);
                journalEntryRepository.deleteById(id);
            }
        }catch (Exception e) {
            throw new RuntimeException("An error occurred while deleting the entry.",e);
        }
        return removed;
    }

    public JournalEntry updateEntry(ObjectId id, JournalEntry journalEntry) {
        journalEntry.setId(id);
        return journalEntryRepository.save(journalEntry);
    }

//    public List<JournalEntry> findByUserName(String userName) {
//
//    }
}