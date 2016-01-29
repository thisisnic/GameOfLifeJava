import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;


	/**
	 * Sleeps the thread for 90 milliseconds
	 */
	class aThread extends Thread {
	    public void run() {
	    	try {
				Thread.sleep(90);
			}
	    	catch (InterruptedException e) {
				e.printStackTrace();
			}
	    }
	}

/**
 * 
 */
class MyCanvas extends JComponent {

  public void paint(Graphics g) {
	 int sideLength = 30; // Size of each square 
	 int numWide = gameOfLife.size; // Width of the grid
	 int numHigh = gameOfLife.size; // Height of the grid
	 
	 /**
	  * Cycle through the array of cells and determine what colour to paint each cell
	  *  based on the aliveNow attribute.
	  */
	 int rowCount = 0; 
	 for(int i = 10; i<=numWide*sideLength;i+=sideLength){ 
		 
		 int colCount = 0;
		 for(int j = 10;j<=numHigh*sideLength;j+=sideLength){
			 
			 if(gameOfLife.cells[rowCount][colCount].aliveNow==true){
				 g.setColor(Color.WHITE);
			 }
			 else{
				 g.setColor(Color.BLACK);
			 }
			 			
			 g.fillRect(i, j, sideLength, sideLength);
			 colCount ++;
		 }
		 rowCount++;
	 }
  }
  
}

 class cell{
	 boolean aliveNow = false;
	 boolean aliveNext = false; 
}

public class gameOfLife {
	static int size = 30; // Length of grid edge, in squares
	

	/*
	 * Create and populate an array of cells on the grid
	 */
	
	static cell[][] cells = new cell[size][size]; // array of cells on the grid
	
	public static void popCells(){
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				cells[i][j] = new cell();
			}
		}
		
		/*
		 * For demonstration purposes, a number of cells are set to be alive at the start
		 */
		cells[1][3].aliveNow=true;
		cells[2][1].aliveNow=true;
		cells[2][3].aliveNow=true;
		cells[3][2].aliveNow=true;
		cells[1][3].aliveNow=true;
		cells[3][3].aliveNow=true;
		
		
		cells[15][15].aliveNow=true;
		cells[15][16].aliveNow=true;
		cells[15][17].aliveNow=true;
		
		cells[19][15].aliveNow=true;
		cells[19][16].aliveNow=true;
		cells[19][17].aliveNow=true;
		cells[18][17].aliveNow=true;
		
	}
	
	/*
	 * For each cell, calculate where it will be alive or dead on the next iteration.
	 */
	public static void calcCells(){
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				int n = checkNeighbours(i,j); // count the number of alive neighbours for this cell
				
				// If the current cell is alive...
				if(cells[i][j].aliveNow==true){
					
					// ...and it has 2 or 3 neighbours it lives...
					if((n==2)||(n==3)){
						cells[i][j].aliveNext = true;
					}
					// ...else it dies.
					else{
						cells[i][j].aliveNext = false;
					}	
				}
				
				// If the current cell is dead...
				else{
					// ...if it has 3 neighbours, it comes to life...
					if(n==3){
						cells[i][j].aliveNext = true;
					}
					// ...else it dies.
					else{
						cells[i][j].aliveNext = false;
					}
				}
			}
		}
	}
	
	
	/*
	 * Cycle through all cells and take the value for in aliveNext and assign it to aliveNow.
	 */
	public static void updateCells(){
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				cells[i][j].aliveNow = cells[i][j].aliveNext;		
			}
		}
	}
	
	/*
	 * Given the row and column number of a cell, return the number of neighbours it has.
	 */
	public static int checkNeighbours(int midValR, int midValC){
		
		int neighbours = 0;
		
		for(int i = -1;i<=1; i++){
			for(int j=-1;j<=1;j++){
				int thisCellR = midValR+i; //The row of the cell being examined
				int thisCellC = midValC+j; //The column of the cell being examined
				
				/*
				 * If the row and column of the current cell are both between 0 and the maximum value (size of the grid),
				 * 	making sure to exclude the cell itself...
				 */
				if(thisCellR>=0&&thisCellR<size&&thisCellC>=0&&thisCellC<size&&(!((i==0)&&(j==0)))){
					
					// ...then if the current cell is alive, increase the count of neighbours by 1.
					if(cells[thisCellR][thisCellC].aliveNow==true){
					
						neighbours++;
					}
				}
			}
		}
		
		return neighbours;
	}
	
	
	
	
	public static void main(String[] a) throws InterruptedException {	
	
		int totalUpdates = 120; // The number of times to update the grid.
		
		popCells();
		
		JFrame window = new JFrame();
		JPanel myPanel = new JPanel();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(30, 30, 920, 945);
		calcCells();
		updateCells();
		
		for(int i=0;i<totalUpdates;i++){
			MyCanvas v = new MyCanvas();
			
			window.getContentPane().add(v);
			window.setLocationRelativeTo(null);
			window.setVisible(true);
			aThread myThread = new aThread();
			myThread.start();
			myThread.join();
							
			calcCells();
			updateCells();
		}
		
	}
}