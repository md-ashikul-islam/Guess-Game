package guess.game.console;

import guess.game.Game;
import guess.game.MessageGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class consoleNumberGuess {
    //== Constant ==//
    private static final Logger log = LoggerFactory.getLogger(consoleNumberGuess.class);

    //== Fields ==//

    private final Game game;

    private final MessageGenerator msgGen;

    //public Constructors

    @Autowired
    public consoleNumberGuess(Game game, MessageGenerator msgGen) {
        this.game = game;
        this.msgGen = msgGen;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void start() {
        log.info("Start()---> Container Ready for use");

        Scanner sc = new Scanner((System.in));

        while (true) {
            System.out.println(msgGen.getMainMessage());
            System.out.println(msgGen.getResultMessage());

            int guess = sc.nextInt();
            sc.nextLine();
            game.setGuess(guess);
            game.check();

            if (game.isGameWon() || game.isGameLost()) {
                System.out.println(msgGen.getResultMessage());
                System.out.println("Play Again y/n?");

                String PlayAgainString = sc.nextLine().trim();
                if (!PlayAgainString.equalsIgnoreCase("y")) {
                    break;
                }
                game.reset();
            }


        }
    }
}
