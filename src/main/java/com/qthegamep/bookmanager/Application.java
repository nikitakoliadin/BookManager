package com.qthegamep.bookmanager;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class Application {

    public static void main(String[] args) {
        log.info(System.lineSeparator() +
                "------------------------------------------------------------------------------------------------" +
                "Book Manager" +
                "------------------------------------------------------------------------------------------------"
        );

        log.info("Preparing the application to start");

        System.out.println("Realization is empty!");
    }
}
