package com.example.spcurrency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    public List<History> getAllHistory() {
        return historyRepository.findAll();
    }

    public History addHistory(String first, String second, Users user) {
        History history = new History(first, second, new Date());
        history.setUser(user);
        return historyRepository.save(history);
    }
}
