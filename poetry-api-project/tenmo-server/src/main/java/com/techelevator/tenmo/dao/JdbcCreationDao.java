package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.DaoException;
import com.techelevator.tenmo.model.Creation;
import com.techelevator.tenmo.model.Poem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCreationDao implements CreationDao{

    @Autowired
    PoemDao poemDao;
    JdbcTemplate jdbcTemplate;

    public JdbcCreationDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }
    @Override
    public Creation newCreation(String cloudinaryUrl, int userId, int poemId) {
        Creation creation = new Creation();
        String sql = "INSERT INTO creations (url, time_stamp, poem_id, user_id) VALUES(?, now(), ?, ?) RETURNING creation_id";
        int creationId = 0;
        try{
            creationId = jdbcTemplate.queryForObject(sql, int.class, cloudinaryUrl, poemId, userId);
            if(creationId == 0){
                throw new Exception();
            }
            creation = getCreationByCreationId(creationId);
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        catch (Exception e) {
            throw new DaoException("Creation Failed", e);
        }

        return creation;
    }

    public Creation getCreationByCreationId(int creationId){
        Creation creation = new Creation();
        String sql = "SELECT creation_id, url, time_stamp, poem_id, user_id FROM creations WHERE creation_id = ?;";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, creationId);
            if(results.next()){
                creation = mapRowToCreation(results);

            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return creation;
    }

    public List<Creation> fetchCreationsByUserId(int userId){
        List<Creation> creations = new ArrayList<>();
        String sql = "SELECT creation_id, url, time_stamp, poem_id, user_id FROM creations WHERE user_id = ?;";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while(results.next()){
                Creation currentCreation = mapRowToCreation(results);
                creations.add(currentCreation);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return creations;
    }

    private Creation mapRowToCreation(SqlRowSet results){

        Creation creation = new Creation();


        creation.setCreationId(results.getInt("creation_id"));
        creation.setPoem(poemDao.getPoemById(results.getInt("poem_id")));
        creation.setUserId(results.getInt("user_id"));
        creation.setImageUrl(results.getString("url"));
        creation.setCreationDate(results.getDate("time_stamp").toLocalDate());

        return creation;
    }

    public void sqlBackup(){
        List<Creation> creations = new ArrayList<>();
        String sql = "SELECT creation_id, url, time_stamp, poem_id, user_id FROM creations;";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while(results.next()){
                Creation currentCreation = mapRowToCreation(results);
                creations.add(currentCreation);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        for(Creation creation : creations){
            System.out.println("INSERT INTO creations (url, time_stamp, poem_id, user_id)");
//            System.out.println("VALUES ('https://res.c', now(), 69, 1)");
            System.out.println("VALUES ('" + creation.getImageUrl() + "', '" + creation.getCreationDate() + "', " + creation.getPoem().getPoemId() + ", " + creation.getUserId() + ");");

        }

    }
}
