package guess.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class  numberGeneratorImp implements numberGenerator{

    //Fields-------------
    private final Random random= new Random();
    private final int maxNumber;
    private final int minNumber;

    //Public Constructors
    @Autowired
    public numberGeneratorImp(@MaxNumber int maxNumber, @MinNumber int minNumber){
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
    }
    //Public Methods--------------
    @Override
    public int next() {
        return random.nextInt(maxNumber);
    }

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }

    @Override
    public int getMinNumber() {
        return minNumber;
    }
}
