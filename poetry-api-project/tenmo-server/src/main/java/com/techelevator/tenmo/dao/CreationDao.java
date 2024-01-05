package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Creation;

import java.util.List;

public interface CreationDao {
    Creation newCreation(String CloudinaryUrl, int userId, int poemId);
    List<Creation> fetchCreationsByUserId(int userId);
    Creation getCreationByCreationId(int creationId);
    void sqlBackup();
}
