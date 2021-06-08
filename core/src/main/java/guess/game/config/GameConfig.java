package guess.game.config;

import guess.game.GuessCount;
import guess.game.MaxNumber;
import guess.game.MinNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "guess.game")
@PropertySource("classpath:config/game.properties")
public class GameConfig {

    //== Fields ==//
    @Value("${game.maxNumber}")
    private int maxNumber;
    @Value("${game.guessCount}")
    private int guessCount;
    @Value(("${game.minNumber}"))
    private int minNumber;

    //== Bean Methods ==/
    @Bean
    @MaxNumber
    public int maxNumberBean(){
        return maxNumber;
    }

    @Bean
    @GuessCount
    public int guessCountBean(){
        return guessCount;
    }

    @Bean
    @MinNumber
    public int minNumberBean(){
        return minNumber;
    }
}
