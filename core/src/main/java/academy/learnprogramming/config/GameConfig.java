package academy.learnprogramming.config;


import academy.learnprogramming.GuessCount;
import academy.learnprogramming.MaxNumber;
import academy.learnprogramming.MinNumber;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "academy.learnprogramming")
@PropertySource("classpath:config/game.properties")
public class GameConfig {

    // == fields ==
    @Value("${game.maxNumber:50}") // If it can't find that val in properties file it defaults as 20
    private int maxNumber;
    @Value("${game.minNumber:5}")
    private int minNumber;
    @Value("${game.guessCount:5}")
    private int guessCount;

    // == bean methods ==
    @Bean
    @MaxNumber
    public int maxNumber(){
        return maxNumber;
    }

    @Bean
    @MinNumber
    public int minNumber(){
        return minNumber;
    }


    @Bean
    @GuessCount
    public int guessCount(){
        return guessCount;
    }

}
