/* Homework6Test.java */

import dict.*;

/**
 *  Initializes a hash table, then stocks it with random SimpleBoards.
 **/

public class Homework6Test {

  /**
   *  Generates a random 8 x 8 SimpleBoard.
   **/

  private static SimpleBoard randomBoard() {
    SimpleBoard board = new SimpleBoard();
    for (int y = 0; y < 8; y++) {
      for (int x = 0; x < 8; x++) {
	double fval = Math.random() * 12;
	int value = (int) fval;
	board.setElementAt(x, y, value);
      }
    }
    return board;
  }

  private static SimpleBoard nonRandomBoard() {
    SimpleBoard board = new SimpleBoard();
    for (int y = 0; y < 8; y++) {
      for (int x = 0; x < 8; x++) {
        board.setElementAt(x, y, 11);
      }
    }
    return board;
  }

  /**
   *  Empties the given table, then inserts "numBoards" boards into the table.
   *  @param table is the hash table to be initialized.
   *  @param numBoards is the number of random boards to place in the table.
   **/

  public static void initTable(HashTableChained table, int numBoards) {
    table.makeEmpty();
    for (int i = 0; i < numBoards; i++) {
      table.insert(randomBoard(), new Integer(i));
    }
  }

  /**
   *  Creates a hash table and stores a certain number of SimpleBoards in it.
   *  The number is 100 by default, but you can specify any number at the
   *  command line.  For example:
   *
   *    java Homework6Test 12000
   **/

  public static void main(String[] args) {
    int numBoards;

    if (args.length == 0) {
      numBoards = 100;
    } else {
      numBoards = Integer.parseInt(args[0]);
    }
    HashTableChained table = new HashTableChained(numBoards);
    initTable(table, numBoards);

    System.out.println(table.countCollisions());

    SimpleBoard board = randomBoard();
    SimpleBoard boardCopy = board;
    SimpleBoard boardDiff = randomBoard();

    System.out.println("Board is copy of self?: " + board.equals(boardCopy));
    System.out.println("Board is copy of random board?: " + board.equals(boardDiff));

    SimpleBoard board2 = nonRandomBoard();
    SimpleBoard board2Copy = nonRandomBoard();
    SimpleBoard board2Diff = nonRandomBoard();
    board2Diff.setElementAt(1,1,3);
    System.out.println("Board 2 is copy of identical board?: " + board2.equals(board2Copy));
    System.out.println("Board 2 is copy of augmented board?: " + board2.equals(board2Diff));

    dict.HashTableChained table1 = new dict.HashTableChained(3);
    table1.insert(1, "board");
    table1.insert(2, "board2");
    table1.insert(3, "board2Diff");
    Entry index1 = table1.find(1);
    Entry index2 =table1.find(2);
    Entry index3 =table1.find(3);
    System.out.println(index1.value());
    System.out.println(index2.value());
    System.out.println(index3.value());
    table1.remove(2);
    System.out.println(table1.find(2)==null);









    // To test your hash function, add a method to your HashTableChained class
    // that counts the number of collisions--or better yet, also prints
    // a histograph of the number of entries in each bucket.  Call this method
    // from here.
  }

}
