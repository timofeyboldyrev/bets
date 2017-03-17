package ru.boldyrev.parsing;

import ru.boldyrev.db.entity.BetTypeName;

import java.util.EnumMap;
import java.util.Random;

/**
 * Created by t.boldyrev on 16.03.2017.
 */
public class ParseResult {

    private String team1;
    private String team2;
    private String time;
    private Integer score1;
    private Integer score2;
    private EnumMap<BetTypeName,Double> coefficients;

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getScore1() {
        return score1;
    }

    public void setScore1(Integer score1) {
        this.score1 = score1;
    }

    public Integer getScore2() {
        return score2;
    }

    public void setScore2(Integer score2) {
        this.score2 = score2;
    }

    public EnumMap<BetTypeName, Double> getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(EnumMap<BetTypeName,Double> coefficients) {
        this.coefficients = coefficients;
    }

    @Override
    public String toString() {
        return "ParseResult{" +
                "team1='" + team1 + '\'' +
                ", team2='" + team2 + '\'' +
                ", time='" + time + '\'' +
                ", score1=" + score1 +
                ", score2=" + score2 +
                ", coefficients=" + coefficients +
                '}';
    }

    public static ParseResult test() {
        ParseResult test = new ParseResult();
        test.setTeam1("Манчестер Юнайтед");
        test.setTeam2("Ростов");
        test.setTime(randomInt(90) + ":" + randomInt(60));
        test.setScore1(randomInt(3));
        test.setScore2(randomInt(4));
        EnumMap<BetTypeName, Double> coefficients = new EnumMap<>(BetTypeName.class);
        coefficients.put(BetTypeName.W1, randomDouble());
        coefficients.put(BetTypeName.X, randomDouble());
        coefficients.put(BetTypeName.W2, randomDouble());
        test.setCoefficients(coefficients);
        return test;
    }

    public static int randomInt(int max) {
        return new Random().nextInt(max);
    }

    public static double randomDouble() {
        double value = new Random().nextDouble()*5 + 1;
        int places = 2;
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}
