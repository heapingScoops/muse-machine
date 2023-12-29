package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.DaoException;
import com.techelevator.tenmo.model.Poem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcPoemDao implements PoemDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcPoemDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }
    @Override
    public Poem getPoemById(int poemId) {
        Poem poem = null;
        String sql = "SELECT * FROM poetry_foundation WHERE id= ? ";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, poemId);
            if (results.next()) {
                poem = mapRowToPoem(results);
            }
        }
        catch(DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation");
        }
        catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Could not connect");
        }

        return poem;
    }

    private Poem mapRowToPoem(SqlRowSet results) {
        Poem poem = new Poem();
        poem.setPoemId(results.getInt("id"));
        poem.setPoem(results.getString("poem"));
        poem.setTitle(results.getString("title"));
        poem.setPoet(results.getString("poet"));
        poem.setTags(results.getString("tags"));

        return poem;
    }

}
