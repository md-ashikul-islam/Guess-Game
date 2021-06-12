package guess.game.service;

import guess.game.Game;
import guess.game.MessageGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class GameServiceImpl implements GameService{

    //Fields
    private final Game game;
    private final MessageGenerator messageGenerator;

    //constructors
    @Autowired
    public GameServiceImpl(Game game , MessageGenerator messageGenerator) {
        this.game = game;
        this.messageGenerator = messageGenerator;
    }

    //init
    @PostConstruct
    public void init(){
        log.info("number={}",game.getNumber());
        log.info("mainMessage={}", messageGenerator.getMainMessage());
    }

    //methods
    @Override
    public boolean isGameOver() {
        return game.isGameWon() || game.isGameLost();
    }

    @Override
    public String getMainMessage() {
        return messageGenerator.getMainMessage();
    }

    @Override
    public String getResultMessage() {
        return messageGenerator.getResultMessage();
    }

    @Override
    public void checkGuess(int guess) {
        game.setGuess(guess);
        game.check();

    }

    @Override
    public void reset() {
        game.reset();
    }
}
