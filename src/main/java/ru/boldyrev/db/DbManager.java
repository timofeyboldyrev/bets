package ru.boldyrev.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.boldyrev.db.entity.*;
import ru.boldyrev.db.repository.BetTypeRepository;
import ru.boldyrev.db.repository.CoefficientRepository;
import ru.boldyrev.db.repository.GameRepository;
import ru.boldyrev.db.repository.GameTimeRepository;
import ru.boldyrev.parsing.ParseResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Timofey Boldyrev on 16.03.2017.
 */
@Service
public class DbManager {

    private static final Logger log = LoggerFactory.getLogger(DbManager.class);
    @Autowired private BetTypeRepository betTypeRepository;
    @Autowired private CoefficientRepository coefficientRepository;
    @Autowired private GameRepository gameRepository;
    @Autowired private GameTimeRepository gameTimeRepository;

    public void save(List<ParseResult> info) {
        info.forEach(this::save);
        System.out.println(LocalDateTime.now());
        System.out.println("\n");
    }

    private void save(ParseResult info) {
        System.out.println(info);
        Long gameId = initGame(info.getTeam1(), info.getTeam2());
        Long gameTimeId = initGameTime(gameId, info.getScore1(), info.getScore2(), info.getTime());
        initCoefficients(gameTimeId, info.getCoefficients());
    }

    private Long initGame(String team1, String team2) {
        Game game = gameRepository.findByTeam1AndTeam2AndGameDate(team1, team2, LocalDate.now());
        if(game==null) {
            game = gameRepository.findByTeam1AndTeam2AndGameDate(team1, team2, LocalDate.now().minusDays(1));
        }
        if (game == null) {
            game = new Game();
            game.setTeam1(team1);
            game.setTeam2(team2);
            game.setGameDate(LocalDate.now());
        }
        gameRepository.save(game);
        return game.getId();
    }

    private Long initGameTime(Long gameId, Integer score1, Integer score2, String time) {
        GameTime gameTime = new GameTime();
        gameTime.setGameId(gameId);
        gameTime.setScore1(score1);
        gameTime.setScore2(score2);
        gameTime.setTime(time);
        gameTimeRepository.save(gameTime);
        return gameTime.getId();
    }

    private void initCoefficients(Long gameTimeId, EnumMap<BetTypeName, Double> coefficients) {
        EnumMap<BetTypeName,Long> betTypeIdMap = new EnumMap<>(BetTypeName.class);
        for (BetTypeName betTypeName : coefficients.keySet()) {
            BetType betType = betTypeRepository.findByName(betTypeName);
            Long betTypeId = betType==null? null : betType.getId();
            betTypeIdMap.put(betTypeName, betTypeId);
        }
        for (Map.Entry<BetTypeName, Double> betTypeCoefficient : coefficients.entrySet()) {
            BetTypeName betTypeName = betTypeCoefficient.getKey();
            Long betTypeId;
            if((betTypeId = betTypeIdMap.get(betTypeName))==null) {
                log.error("Ставка с названием '" + betTypeName + "' не найдена в базе");
                continue;
            }
            Double value = betTypeCoefficient.getValue();
            Coefficient coefficient = new Coefficient();
            coefficient.setGameTimeId(gameTimeId);
            coefficient.setBetTypeId(betTypeId);
            coefficient.setCoefficient(value);
            coefficientRepository.save(coefficient);
        }
    }

}
