/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ufro.cognitiva.t1.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author matia
 */
@SpringBootApplication
@EnableAutoConfiguration
public class Application {

    public static void main(String[] arg) {
        SpringApplication.run(Application.class, arg);
    }

}
