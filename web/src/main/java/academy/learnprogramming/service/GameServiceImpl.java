package academy.learnprogramming.service;


import academy.learnprogramming.Game;
import academy.learnprogramming.MessageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class GameServiceImpl implements GameService{

    // == fields ==
    private final Game game;

    private final MessageGenerator messageGenerator;

    // == init ==
    @PostConstruct
    public void init(){
//        while (true){
        System.out.println(getMainMessage());
        System.out.println(game.getNumber());

        //}
    }
    // == constructors ==
    @Autowired
    public GameServiceImpl(Game game, MessageGenerator messageGenerator) {
        this.game = game;
        this.messageGenerator = messageGenerator;
    }

    // == public methods ==
    @Override
    public boolean isGameOver() {
        return game.isGameLost()||game.isGameWon();
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
