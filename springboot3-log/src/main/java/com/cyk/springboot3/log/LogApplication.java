package com.cyk.springboot3.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author cyk
 * @date 2023/10/29 09:02
 */
@SpringBootApplication
public class LogApplication {

    /***
     * 慎用System.out.println();尽量使用log4j2或者logback等日志来记录信息。
     * System.out.println()是一个同步方法，当我们在性能要求比较高、高并发的时候（毫秒级别时），
     * 使用System.out.println()就会导致进程在执行它时不能异步进行，导致浪费大量时间，从而对性能产生影响
     * @param args
     */

    public static void main(String[] args) {
        SpringApplication.run(LogApplication.class,args);
    }
}
