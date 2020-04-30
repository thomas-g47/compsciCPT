package com.example.SMCSLearn;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class PersonalityController {

    @GetMapping("/personality")
    @CrossOrigin("http://localhost:4200")
    public Question[] personalityQuestions() {
        Question[] questions = new Question[32];
        questions[0] = new Question("Do you trust information after hearing from one source?");
        questions[1] = new Question("Do you get easily upset during conversations with others who contradict your views?");
        questions[2] = new Question("Do you change your mind easily if you were wrong?");
        questions[3] = new Question("Do you get upset at those who use insults as arguments in their favour?");
        questions[4] = new Question("Do you notice personal growth every day?");
        questions[5] = new Question("Do you have innovative ideas?");
        questions[6] = new Question("Do you over analyze situations?");
        questions[7] = new Question("Do you expect too much from yourself?");
        questions[8] = new Question("Do you like to go out to the movies with friends?");
        questions[9] = new Question("Do you like to play games by yourself?");
        questions[10] = new Question("Do you enjoy reading?");
        questions[11] = new Question("Do you enjoy meeting new people?");
        questions[12] = new Question("Would you rather stay in then go out?");
        questions[13] = new Question("Do you like to reflect on your experiences?");
        questions[14] = new Question("Do you like being the centre of attention?");
        questions[15] = new Question("Do you usually get noticed right away in class?");
        questions[16] = new Question("Are you not interested in other people's problems?");
        questions[17] = new Question("Are you concerned about others?");
        questions[18] = new Question("Do you use others for your own ends?");
        questions[19] = new Question("Do you try not to think about the needy?");
        questions[20] = new Question("Are you indifferent to the feelings of others?");
        questions[21] = new Question("Do you obstruct others' plans?");
        questions[22] = new Question("Do you boast about my virtues?");
        questions[23] = new Question("Do you think you are better then others?");
        questions[24] = new Question("Do you have an academic goal in mind?");
        questions[25] = new Question("Do you try to complete all your homework?");
        questions[26] = new Question("Do you go in for extra help?");
        questions[27] = new Question("Do you pay attention in class?");
        questions[28] = new Question("Do you have an organized binder?");
        questions[29] = new Question("Do you bring all materials needed to class?");
        questions[30] = new Question("Do you take pride in your work?");
        questions[31] = new Question("Are you interested in what you are learning?");

        //For questions they are divided up from 0-7 as critical thinking, 8-15 as introvert and extrovert,
        // 16-23 as agreeableness and the rest for eagerness to learn

        return questions;
    }


    RestTemplate restTemplate;

    @PostMapping(path = "/personality", consumes = "application/json")
    public String receiveAnswers(@RequestBody String[] answers, @RequestParam(value = "id") int id) {
        String[] responses;
        responses = answers;

        String JP;
        String EI;
        String TF;
        String SI;

        int judging_score=0;
        int extro_score =0;
        int sensing_score=0;
        int thinking_score=0;

        for (int i = 0; i < 9; i++) {

            if (responses[i] == "0") {
                judging_score++;
            } else if (responses[i] == "1") {
                judging_score += 2;
            } else if (responses[i] == "2") {
                judging_score += 3;
            } else {
                judging_score += 4;
            }
        }

        for (int i = 9; i < 17; i++) {
            if (responses[i] == "0") {
                extro_score++;
            } else if (responses[i] == "1") {
                extro_score += 2;
            } else if (responses[i] == "2") {
                extro_score += 3;
            } else {
                extro_score += 4;
            }
        }
        for (
                int i = 17;
                i < 25; i++) {
            if (responses[i] == "0") {
                thinking_score++;
            } else if (responses[i] == "1") {
                thinking_score += 2;
            } else if (responses[i] == "2") {
                thinking_score += 3;
            } else {
                thinking_score += 4;
            }
        }
        for (
                int i = 25;
                i < 33; i++) {
            if (responses[i] == "0") {
                sensing_score++;
            } else if (responses[i] == "1") {
                sensing_score += 2;
            } else if (responses[i] == "2") {
                sensing_score += 3;
            } else {
                sensing_score += 4;
            }

        }

        if (judging_score >= 17) {
            JP = "J";
        } else {
            JP = "P";
        }

        if (extro_score >= 17) {
            EI = "E";
        } else {
            EI = "I";
        }

        if (thinking_score >= 17) {
            TF = "T";
        } else {
            TF = "F";
        }

        if (sensing_score >= 17) {
            SI = "S";
        } else {
            SI = "I";
        }

        return (JP + EI + TF + SI);
    }
}