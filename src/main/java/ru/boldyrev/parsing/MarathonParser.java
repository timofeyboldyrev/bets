package ru.boldyrev.parsing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.boldyrev.db.entity.BetTypeName;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by t.boldyrev on 16.03.2017.
 */
@Service
public class MarathonParser implements Parser {

    private static final Logger log = LoggerFactory.getLogger(MarathonParser.class);
    private Pattern patternBlocks;
    private Pattern patternTeam;
    private Pattern patternScore;
    private Pattern patternTime;
    private Pattern patternAdditionalTime;
    private Pattern patternW1;
    private Pattern patternX;
    private Pattern patternW2;
    private Pattern patternX1;
    private Pattern patternW12;
    private Pattern patternX2;
    private Pattern patternTotal0_5M;
    private Pattern patternTotal0_5B;
    private Pattern patternTotal1M;
    private Pattern patternTotal1B;
    private Pattern patternTotal1_5M;
    private Pattern patternTotal1_5B;
    private Pattern patternTotal2M;
    private Pattern patternTotal2B;
    private Pattern patternTotal2_5M;
    private Pattern patternTotal2_5B;
    private Pattern patternTotal3M;
    private Pattern patternTotal3B;
    private Pattern patternTotal3_5M;
    private Pattern patternTotal3_5B;
    private Pattern patternTotal4M;
    private Pattern patternTotal4B;
    private Pattern patternTotal4_5M;
    private Pattern patternTotal4_5B;
    private Pattern patternTotal5M;
    private Pattern patternTotal5B;
    private Pattern patternTotal5_5M;
    private Pattern patternTotal5_5B;

    public MarathonParser() {
        patternBlocks = Pattern.compile("<tbody class.+?</tbody>");
        patternTeam = Pattern.compile("<span>(.+?)</span>");
        patternScore = Pattern.compile("<div  class=\"cl-left red\">  (.+?) ");
        patternTime = Pattern.compile("<span class=\"time-description\"> <div class=\"green bold nobr\">  (.+?) ");
        patternAdditionalTime = Pattern.compile("<span class=\"additional-time\"> (.+?) ");
        patternW1 = Pattern.compile("@Match_Result\\.1\">(.+?)</span>");
        patternX = Pattern.compile("@Match_Result\\.draw\">(.+?)</span>");
        patternW2 = Pattern.compile("@Match_Result\\.3\">(.+?)</span>");
        patternX1 = Pattern.compile("@Result\\.HD\">(.+?)</span>");
        patternW12 = Pattern.compile("@Result\\.HA\">(.+?)</span>");
        patternX2 = Pattern.compile("@Result\\.AD\">(.+?)</span>");
        Function<String,Pattern> patternTotal = total -> Pattern.compile("@Total_Goals.?\\." + total + "\">(.+?)</span>");
        patternTotal0_5M = patternTotal.apply("Under_0.5");
        patternTotal0_5B = patternTotal.apply("Over_0.5");
        patternTotal1M = patternTotal.apply("Under_1");
        patternTotal1B = patternTotal.apply("Over_1");
        patternTotal1_5M = patternTotal.apply("Under_1.5");
        patternTotal1_5B = patternTotal.apply("Over_1.5");
        patternTotal2M = patternTotal.apply("Under_2");
        patternTotal2B = patternTotal.apply("Over_2");
        patternTotal2_5M = patternTotal.apply("Under_2.5");
        patternTotal2_5B = patternTotal.apply("Over_2.5");
        patternTotal3M = patternTotal.apply("Under_3");
        patternTotal3B = patternTotal.apply("Over_3");
        patternTotal3_5M = patternTotal.apply("Under_3.5");
        patternTotal3_5B = patternTotal.apply("Over_3.5");
        patternTotal4M = patternTotal.apply("Under_4");
        patternTotal4B = patternTotal.apply("Over_4");
        patternTotal4_5M = patternTotal.apply("Under_4.5");
        patternTotal4_5B = patternTotal.apply("Over_4.5");
        patternTotal5M = patternTotal.apply("Under_5");
        patternTotal5B = patternTotal.apply("Over_5");
        patternTotal5_5M = patternTotal.apply("Under_5.5");
        patternTotal5_5B = patternTotal.apply("Over_5.5");
    }

    public List<ParseResult> parsePage(String page) {
        Matcher matcher = patternBlocks.matcher(page.replaceAll("\r\n", ""));
        List<ParseResult> out = new ArrayList<>();
        while (matcher.find()) {
            String block = matcher.group();
            try {
                ParseResult parseResult = parseBlock(block);
                out.add(parseResult);
            } catch (Exception e) {
                log.error("Ошибка при обработке блока: " + block, e);
            }
        }
        return out;
    }

    private ParseResult parseBlock(String block) {
        ParseResult result = new ParseResult();
        addTeams(result, block);
        addScore(result, block);
        addTime(result, block);
        addCoefficients(result,block);
        return result;
    }

    private void addTeams(ParseResult result, String block) {
        Matcher matcher = patternTeam.matcher(block);
        BiConsumer<Consumer<String>,String> addTeam = (setter,field) -> {
            if (matcher.find()) {
                setter.accept(matcher.group(1));
            } else {
                exception(field, patternTeam);
            }
        };
        addTeam.accept(result::setTeam1, "team1");
        addTeam.accept(result::setTeam2, "team2");
    }

    private void addScore(ParseResult result, String block) {
        Matcher matcher = patternScore.matcher(block);
        if(matcher.find()) {
            String score = matcher.group(1);
            String[] goals = score.split(":");
            if(goals.length!=2) {
                throw new RuntimeException("Ошибка при парсинге счёта '" + score + "', " +
                        "полученного по шаблону '" + patternScore + "'");
            }
            try {
                result.setScore1(Integer.valueOf(goals[0]));
                result.setScore2(Integer.valueOf(goals[1]));
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Ошибка при парсинге счёта '" + score + "', " +
                        "полученного по шаблону '" + patternScore + "'. Значения не являются числами");
            }
        } else {
            exception("score", patternScore);
        }
    }

    private void addTime(ParseResult result, String block) {
        Matcher matcher = patternTime.matcher(block);
        if(matcher.find()) {
            String time = matcher.group(1);
            Matcher matcherAdditionalTime = patternAdditionalTime.matcher(block);
            if(matcherAdditionalTime.find()) {
                time = time + matcherAdditionalTime.group(1);
            }
            result.setTime(time);
        } else {
            exception("time", patternTime);
        }
    }

    private void addCoefficients(ParseResult result, String block) {
        EnumMap<BetTypeName,Double> coefficientsMap = new EnumMap<>(BetTypeName.class);
        BiConsumer<Pattern,BetTypeName> addCoefficient = (pattern, betType) -> {
            Matcher matcher = pattern.matcher(block);
            if(matcher.find()) {
                String coefficient = matcher.group(1);
                try {
                    coefficientsMap.put(betType, Double.valueOf(coefficient));
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Ошибка при парсинге коэффициента '" + coefficient + "' для ставки " + betType);
                }
            }
        };
        addCoefficient.accept(patternW1, BetTypeName.W1);
        addCoefficient.accept(patternX, BetTypeName.X);
        addCoefficient.accept(patternW2, BetTypeName.W2);
        addCoefficient.accept(patternX1, BetTypeName.X1);
        addCoefficient.accept(patternW12, BetTypeName.W12);
        addCoefficient.accept(patternX2, BetTypeName.X2);
        addCoefficient.accept(patternTotal0_5M, BetTypeName.TOTAL0_5M);
        addCoefficient.accept(patternTotal0_5B, BetTypeName.TOTAL0_5B);
        addCoefficient.accept(patternTotal1M, BetTypeName.TOTAL1M);
        addCoefficient.accept(patternTotal1B, BetTypeName.TOTAL1B);
        addCoefficient.accept(patternTotal1_5M, BetTypeName.TOTAL1_5M);
        addCoefficient.accept(patternTotal1_5B, BetTypeName.TOTAL1_5B);
        addCoefficient.accept(patternTotal2M, BetTypeName.TOTAL2M);
        addCoefficient.accept(patternTotal2B, BetTypeName.TOTAL2B);
        addCoefficient.accept(patternTotal2_5M, BetTypeName.TOTAL2_5M);
        addCoefficient.accept(patternTotal2_5B, BetTypeName.TOTAL2_5B);
        addCoefficient.accept(patternTotal3M, BetTypeName.TOTAL3M);
        addCoefficient.accept(patternTotal3B, BetTypeName.TOTAL3B);
        addCoefficient.accept(patternTotal3_5M, BetTypeName.TOTAL3_5M);
        addCoefficient.accept(patternTotal3_5B, BetTypeName.TOTAL3_5B);
        addCoefficient.accept(patternTotal4M, BetTypeName.TOTAL4M);
        addCoefficient.accept(patternTotal4B, BetTypeName.TOTAL4B);
        addCoefficient.accept(patternTotal4_5M, BetTypeName.TOTAL4_5M);
        addCoefficient.accept(patternTotal4_5B, BetTypeName.TOTAL4_5B);
        addCoefficient.accept(patternTotal5M, BetTypeName.TOTAL5M);
        addCoefficient.accept(patternTotal5B, BetTypeName.TOTAL5B);
        addCoefficient.accept(patternTotal5_5M, BetTypeName.TOTAL5_5M);
        addCoefficient.accept(patternTotal5_5B, BetTypeName.TOTAL5_5B);
        result.setCoefficients(coefficientsMap);
    }

    private void exception(String field, Pattern pattern) {
        throw new RuntimeException("Поле " + field + " не найдено по шалону '" + pattern + "'");
    }

}
