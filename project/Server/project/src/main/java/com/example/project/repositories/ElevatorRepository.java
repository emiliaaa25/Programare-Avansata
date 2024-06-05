package com.example.project.repositories;

import com.example.project.model.Elevators;
import com.example.project.model.ElevatorsDetails;
import com.example.project.model.Floors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ElevatorRepository {
    private static final Logger log = LoggerFactory.getLogger(ElevatorRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Elevators> elevatorsRowMapper = (resultSet, rowNum) -> {
        Elevators elevators = new Elevators();
        elevators.setId(resultSet.getInt("id"));
        elevators.setName(resultSet.getString("name"));
        elevators.setDirection(resultSet.getString("direction"));
        elevators.setCurrentFloor(resultSet.getInt("currentFloor"));
        elevators.setStartFloor(resultSet.getInt("startFloor"));
        elevators.setIsOcupied(resultSet.getString("isOcupied"));
        return elevators;
    };

    public Elevators findById(int id) {
        return jdbcTemplate.query("SELECT * FROM elevators WHERE id = ?", elevatorsRowMapper, id).get(0);
    }

    public List<Elevators> findAll() {
        return jdbcTemplate.query("SELECT * FROM elevators", elevatorsRowMapper);
    }


    public int seeWhichElevatorComeFirst(int floor, String direction, int wantedFloor) {
        List<Elevators> allElevators = jdbcTemplate.query("SELECT * FROM elevators", elevatorsRowMapper);
        Elevators firstElevator = jdbcTemplate.query("SELECT * FROM elevators WHERE id=?", elevatorsRowMapper, 1).get(0);


        Elevators closestElevator = firstElevator;
        int minDistance = Integer.MAX_VALUE;
        int isOcupied = 1;
        for (var elevator : allElevators)
            if (floor >= elevator.getStartFloor() && wantedFloor >= elevator.getStartFloor()) {
                switch (elevator.getDirection()) {
                    case "UP":
                        if(isOcupied==0) {
                            if (elevator.getCurrentFloor() <= floor && minDistance > floor - elevator.getCurrentFloor()) {
                                closestElevator = elevator;
                                minDistance = floor - elevator.getCurrentFloor();
                                isOcupied = 1;
                            } else if (minDistance > (5 - elevator.getCurrentFloor()) + (5 - floor)) {
                                closestElevator = elevator;
                                minDistance = (5 - elevator.getCurrentFloor()) + (5 - floor);
                                isOcupied = 1;
                            }
                        }
                        else {
                            if (elevator.getCurrentFloor() <= floor) {
                                closestElevator = elevator;
                                minDistance = floor - elevator.getCurrentFloor();
                                isOcupied = 0;
                            } else {
                                closestElevator = elevator;
                                minDistance = (5 - elevator.getCurrentFloor()) + (5 - floor);
                                isOcupied = 0;
                            }
                        }
                        break;
                    case "DOWN":
                        if(isOcupied == 0) {
                            if (elevator.getCurrentFloor() >= floor && minDistance > elevator.getCurrentFloor() - floor) {
                                closestElevator = elevator;
                                minDistance = elevator.getCurrentFloor() - floor;
                                isOcupied = 1;
                            } else if (minDistance > elevator.getCurrentFloor() + floor) {
                                closestElevator = elevator;
                                minDistance = elevator.getCurrentFloor() + floor;
                                isOcupied = 1;
                            }
                        }
                        else {
                            if (elevator.getCurrentFloor() >= floor) {
                                closestElevator = elevator;
                                minDistance = floor - elevator.getCurrentFloor();
                                isOcupied = 0;
                            } else {
                                closestElevator = elevator;
                                minDistance = (5 - elevator.getCurrentFloor()) + (5 - floor);
                                isOcupied = 0;
                            }
                        }
                    case "STOPPED":
                        if(isOcupied == 0) {
                            if (minDistance > Math.abs(elevator.getCurrentFloor() - floor)) {
                                closestElevator = elevator;
                                minDistance = Math.abs(elevator.getCurrentFloor() - floor);
                            }
                        }
                        else{
                                closestElevator = elevator;
                                minDistance = Math.abs(elevator.getCurrentFloor() - floor);
                                isOcupied = 0;
                        }

                }
            }

        return closestElevator.getId();
    }

    public void updateFloor(int id, int floor) {
        jdbcTemplate.update("UPDATE elevators SET currentFloor = ? WHERE id = ?", floor, id);
    }

    public void updateDirection(int id, String direction) {
        jdbcTemplate.update("UPDATE elevators SET direction = ? WHERE id = ?", direction, id);
    }

    public int getNumberOfElevators() {
        return jdbcTemplate.query("SELECT * FROM elevators", elevatorsRowMapper).size();
    }


    public String verifyElevator(int id) {
        return jdbcTemplate.query("SELECT * FROM elevators WHERE id = ?", elevatorsRowMapper,id).get(0).getIsOcupied();

    }

    public void updateIsOcupied(int id, String isOcupied) {
        jdbcTemplate.update("UPDATE elevators SET isOcupied = ? WHERE id = ?", isOcupied, id);
    }
}

