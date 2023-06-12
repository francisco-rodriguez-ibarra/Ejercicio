package com.buttermove;

import com.buttermove.factory.StatesFactory;
import com.buttermove.model.StateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.List;

@SpringBootApplication
public class ButterMoveApplication {

    private static StatesFactory statesFactory;
    public ButterMoveApplication(StatesFactory states){
        this.statesFactory=states;
    }

    public static void main(String[] args) {
        SpringApplication.run(ButterMoveApplication.class, args);
    }

}
