package academy.learnprogramming;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Getter
@Slf4j
@Component
public class GameImpl implements Game{

    //Constants
//    private static final Logger log = LoggerFactory.getLogger(GameImpl.class);

    //Fields
    @Getter(AccessLevel.NONE)
    private final NumberGenerator numberGenerator;
    private final int guessCount;

    private int number, smallest, biggest, remainingGuesses;
    private boolean validNumberRange = true;

    @Setter
    private int guess;
    public GameImpl(NumberGenerator numberGenerator,@GuessCount int guessCount) {
        this.numberGenerator = numberGenerator;
        this.guessCount = guessCount;
    }

    //    public GameImpl(NumberGenerator numberGenerator) {
//        this.numberGenerator = numberGenerator;
//    }

    // init
    @PostConstruct
    @Override
    public void reset() {
        smallest = 0;
        guess = 0;
        remainingGuesses = guessCount;
        smallest = numberGenerator.getMinNumber();
        biggest = numberGenerator.getMaxNumber();
        number = numberGenerator.next();
        log.debug("the number is {}", number);
    }

    @PreDestroy
    public void preDestroy(){
        log.info("In Game preDestroy");
    }

    //public methods
    @Override
    public void check() {
        checkValidNumberRange();
        if(validNumberRange) {
            if(guess > number){
                biggest = guess - 1;
            }
            if(guess<number){
                smallest = guess + 1;
            }
        }
        remainingGuesses--;
    }
//  Not necessary thanks to lombok
//    @Override
//    public boolean isValidNumberRange() {
//        return validNumberRange;
//    }

    @Override
    public boolean isGameWon() {
        return guess == number;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses <= 0;
    }

    //private Methods
    private void checkValidNumberRange(){
        validNumberRange = (guess >= smallest) && (guess <= biggest);

    }
}
