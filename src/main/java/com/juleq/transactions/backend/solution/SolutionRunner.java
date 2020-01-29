package com.juleq.transactions.backend.solution;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SolutionRunner implements CommandLineRunner {

    private final Solution solution;

    public SolutionRunner(Solution solution) {
        this.solution = solution;
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length == 1) {
            System.out.println(solution.solution(args[0]));
        }
    }
}