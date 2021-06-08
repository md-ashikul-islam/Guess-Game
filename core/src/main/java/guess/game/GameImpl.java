package guess.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class GameImpl implements Game{

    //== CONSTANTS ==
    private static final Logger log = LoggerFactory.getLogger(GameImpl.class);

    //== Fields ==

    private final numberGenerator numGen;
    private final int guessCount;

    private int number;
    private int guess;
    private int smallest;
    private int biggest;
    private int remainingGuesses;
    private boolean validNumberRange=true;

    //Public Constructor
    @Autowired
    public GameImpl(numberGenerator numGen,@GuessCount int guessCount){
        this.numGen=numGen;
        this.guessCount=guessCount;
    }

    //== init==
    @PostConstruct
    @Override
    public void reset() {
        smallest= numGen.getMinNumber();
        guess=0;
        remainingGuesses=guessCount;
        biggest=numGen.getMaxNumber();
        number=numGen.next();
        log.debug("the number is {}",number);
    }

    @PreDestroy
    public void preDestroy(){
        log.info("in Game preDestroy()");
    }
    //== Public Methods ==

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public int getGuess() {
        return guess;
    }

    @Override
    public void setGuess(int guess) {
        this.guess=guess;

    }

    @Override
    public int getSmallest() {
        return smallest;
    }

    @Override
    public int getBiggest() {
        return biggest;
    }

    @Override
    public int getRemainingGuesses() {
        return remainingGuesses;
    }

    @Override
    public int getGuessCount() {
        return guessCount;
    }

    @Override
    public void check() {

        checkValidNumberRange();
        if(validNumberRange){
            if(guess>number){
                biggest=guess-1;
            }
            if(guess<number){
                smallest=guess+1;
            }
        }
        remainingGuesses--;
    }

    @Override
    public boolean isValidNumberRange() {
        return validNumberRange;
    }

    @Override
    public boolean isGameWon() {
        return guess == number;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses <=0;
    }

    //== Private Methods ==
    private void checkValidNumberRange(){

        validNumberRange = (guess >= smallest) && (guess <= biggest);
    }


}
