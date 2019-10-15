package assignment1.sequencewithminimum;

import java.util.Random;

import assignment1.sequencewithminimum.SequenceWithMinimum;

public class Program {
    public static void main(String[] args) {

        // Inserting 10 000 random values 0-99 999 randomly from right or left
        SequenceWithMinimum seq = new SequenceWithMinimum();
        Random rand1 = new Random();
        Random rand2 = new Random();
        for (int i = 0; i < 10000; i++) {
            switch (rand2.nextInt(2)) {
                case 0: seq.insertLeft(rand1.nextInt(100000) + 1); 
                        break;
                case 1: seq.insertRight(rand1.nextInt(100000) + 1); 
                        break;
            }            
        }
        
        System.out.println("Size: " + seq.size());
        System.out.println("Minimum of 10 000: " + seq.findMinimum());                        
        
        //Removing 9990 values randomly from left or right
        for (int i = 0; i < 9990; i++) {
            switch (rand2.nextInt(2)) {
                case 0: seq.removeLeft(); 
                        break;
                case 1: seq.removeRight();
                        break;
            }
        }
        System.out.println("Size: " + seq.size());
        System.out.println("Remaining sequence " + seq.printSequence());        
        System.out.println("Minimum of 10 remaining " + seq.findMinimum());

        
    }
}