package algorithmic;

import java.lang.Math;
/* Task 58 */
public class GameOfLife {
  private int x;
  private int y;
  private boolean[][] universe;

  public GameOfLife(int x, int y) {
    this.x = x;
    this.y = y;
    universe = new boolean[x][y];
  }

  public void start() throws InterruptedException {
    this.seed();
    while (true) {
      this.print();
      Thread.sleep(1000);
      this.nextTick();
    }
  }

  private void seed() {
    for (int i = 0; i < x; i++) {
      for (int j = 0; j < y; j++) {
        universe[i][j] = Math.random() >= 0.8; // a cell is alive with a chance of 0.2
      }
    }
  }

  private void print() {
    System.out.print("\033[H\033[2J");
    for (boolean[] bs : universe) {
      for (boolean cell : bs) {
        if (cell) {
          System.out.print("▫");
        } else {
          System.out.print(" ");
        }
      }
      System.out.print('\n');
    }
    System.out.flush();
  }

  private void nextTick() {
    boolean[][] newUniverse = new boolean[x][y];
    for (int i = 0; i < x; i++) {
      for (int j = 0; j < y; j++) {
        // count neighbors
        int n = 0;
        for (int x1 = i - 1; x1 <= i + 1; x1++) {
          for (int y1 = j - 1; y1 <= j + 1; y1++) {
            if (universe[(x1 + x) % x][(y1 + y) % y])
              n++;
          }
        }

        // except the current cell
        if (universe[i][j])
          n--;
        newUniverse[i][j] = (n == 3 || (n == 2 && universe[i][j]));
      }
    }

    for (int i = 0; i < x; i++) {
      for (int j = 0; j < y; j++) {
        universe[i][j] = newUniverse[i][j];
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    
    GameOfLife game = new GameOfLife(30, 30);
    game.start();
  }
}
