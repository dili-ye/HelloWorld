package com.example.demo.test;

public class RandomWords{
    public static void start(GamesFactory factory){
        Games game = factory.getGame();
        while (game.event()){
            ;
        }
    }

    public static void main(String[] args) {
        start(new CoinGameFactory());
        start(new DiceGameFactory());
    }

 }

 interface Games{
     boolean event();
 }
 interface GamesFactory{
     Games getGame();
 }
 class CoinGame implements Games{

     private int even;
     private static final int EVEN=2;
     @Override
     public boolean event() {
         System.out.println("coin : "+even);
         return ++even !=EVEN;
     }
 }

 class CoinGameFactory implements GamesFactory{
     @Override
     public Games getGame() {
         return new CoinGame();
     }
 }

 class DiceGame implements Games{
     private int even;
     private static final int EVEN=6;

     @Override
     public boolean event() {
         System.out.println("dice : "+even);
         return ++even !=EVEN;
     }
 }

 class DiceGameFactory implements GamesFactory{

     @Override
     public Games getGame() {
         return new DiceGame();
     }
 }
