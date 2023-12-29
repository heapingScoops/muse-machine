package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class JdbcCreationDao implements CreationDao{

    JdbcTemplate jdbcTemplate;

    public JdbcCreationDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }
    @Override
    public int newCreation(String cloudinaryUrl, int userId, int poemId) {

        String sql = "INSERT INTO creations (url, time_stamp, poem_id, user_id) VALUES(?, now(), ?, ?) RETURNING creation_id";
        int creationId = 0;
        try{
            creationId = jdbcTemplate.queryForObject(sql, int.class, cloudinaryUrl, poemId, userId);
            if(creationId == 0){
                throw new Exception();
            }

        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        catch (Exception e) {
            throw new DaoException("Creation Failed", e);
        }

        return creationId;
    }
}
