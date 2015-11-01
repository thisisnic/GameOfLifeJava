import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

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

class MyCanvas extends JComponent {

  public void paint(Graphics g) {
	 int sideLength = 30;
	 int numWide = gameOfLife.size;
	 int numHigh = gameOfLife.size;
	 
	 int rowCount = 0;
	 for(int i = 10; i<=numWide*sideLength;i+=sideLength){ //numwide*size wrong?
		 
		 int colCount = 0;
		 for(int j = 10;j<=numHigh*sideLength;j+=sideLength){
			 //System.out.println("r:"+rowCount+" "+colCount);
			 // If cell[][].alivenow is true, else
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
	 
	 boolean aliveNow = false;;
	 boolean aliveNext = false; 
	
}

public class gameOfLife {
	static int size = 30;
	
	// between one iteration and the next, the central block turns off
	public static void drawIt(){
		 
		 int numWide = size;
		 int numHigh = size;
		 
		 int rowCount = 0;
		 /**
		   _ _ _ _ _ _ _ _ _ _ 
		  | |X| |X| |X|X| | |X|
		  | |X| |X| |X|X| | |X|
		  | |X| |X| |X|X| | |X|
		  | |X| |X| |X|X| | |X|
		  | |X| |X| |X|X| | |X|

		  */
		 String print = " ";
		 for(int i=0;i<size;i++){
			 print = print+"_ ";
		 }
		 
		 
		 print = print+'\n';
		 String x;
		 for(int i = 0; i<size;i++){ //numwide*size wrong?
			 x="|";
			 int colCount = 0;
			 
			 for(int j = 0;j<size;j++){
				 
				 // If cell[][].alivenow is true, else
				 if(gameOfLife.cells[rowCount][colCount].aliveNow==true){
					 x = (x+"X|");
					 
					// System.out.println("l");
				 }
				 else{
					x=(x+" |");
					

				 }
				 		
				 //g.fillRect(i, j, size, size);
				 colCount ++;
			 }
			 print=print+x+'\n';
			 rowCount++;
		 }
		
		 print = print+" ";
		 for(int i=0;i<size;i++){
			 print = print+"‾ ";
		 }
		 
		 System.out.println(print);
	  }
	
	
	static cell[][] cells = new cell[size][size];
	
	public static void popCells(){
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				cells[i][j] = new cell();
			}
		}
		
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
		
	}
	
	// This method is broke. Check neighbours produces the right results tho.
	public static void calcCells(){
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				int n = checkNeighbours(i,j);
				//System.out.println("Before: Alive?"+cells[i][j].aliveNow+"/n?"+n+"/Alive next?"+cells[i][j].aliveNext);
				//fucks up before here
				//System.out.println(i+","+j+": "+n);
				// If the cell is currently alive...
				if(cells[i][j].aliveNow==true){
					
					// If it has 2 or 3 neighbours it lives
					if((n==2)||(n==3)){
						cells[i][j].aliveNext = true;
					}
					// Else it dies
					else{
						cells[i][j].aliveNext = false;
					}	
				}
				
				// If it's dead currently but has 3 neighbours, it comes to life
				else{
					if(n==3){
						cells[i][j].aliveNext = true;
					}
					else{
						cells[i][j].aliveNext = false;
					}
				}
				//System.out.println("After: Alive?"+cells[i][j].aliveNow+"/n?"+n+"/Alive next?"+cells[i][j].aliveNext);
			}
		}
		
		//Fucks up here? :S
		
		// Updates the current status so we can paint this.
		
		
	}
	
	public static void updateCells(){
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				//System.out.println("In loop, before:"+i+","+j+": now="+cells[i][j].aliveNow+"/next="+cells[i][j].aliveNext);
				cells[i][j].aliveNow = cells[i][j].aliveNext;
				
				//System.out.println("In loop, after:"+i+","+j+": now="+cells[i][j].aliveNow+"/next="+cells[i][j].aliveNext);
			}
		}
	}
	
	public static int checkNeighbours(int midValR, int midValC){
		int n = 0;
		//System.out.println("Cell: "+midValR+", "+midValC);
		for(int i = -1;i<=1; i++){
			for(int j=-1;j<=1;j++){
				int thisCellR = midValR+i;
				int thisCellC = midValC+j;
				//System.out.println(thisCellR+" "+thisCellC);
				//System.out.println();
				// If values are legit between 0 and size, and it's not the orig cell in question
				
				
				if(thisCellR>=0&&thisCellR<size&&thisCellC>=0&&thisCellC<size&&(!((i==0)&&(j==0)))){
					
					//System.out.println(i+" "+j);
					//System.out.println(thisCellR+" "+thisCellC+" examined.");
					// if that cell is alive, increase n
					if(cells[thisCellR][thisCellC].aliveNow==true){
					//	System.out.println(thisCellR+" "+thisCellC+" counted.");
						n++;
					}
				}
			}
		}
		//System.out.println("Cell ["+midValR+"]["+midValC+"] has " + n + " living neighbours.");
		//System.out.println("Total: "+n);
		return n;
	}
	
	
	
	
	public static void main(String[] a) throws InterruptedException {
		
		/**
		popCells();
		drawIt();
		
		for(int i=0;i<5;i++){
			
			calcCells();
			updateCells();
			
			SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				try {
					Thread.sleep((500));	
				}
				catch (InterruptedException e1){
					System.err.println(e1);
				}
			}
		});


			drawIt();
			
		}
		*/
		
		// LEARN HOW TO CODE AND UPDATE GUI STUFF PROPERLY!!!!!
		
		popCells();
		
		JFrame window = new JFrame();
		JPanel myPanel = new JPanel();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(30, 30, 920, 945);
		calcCells();
		updateCells();
		
		for(int i=0;i<100;i++){
			MyCanvas v = new MyCanvas();
			myPanel.removeAll();
			myPanel.invalidate();
			myPanel.add(v);
			myPanel.revalidate();
			window.repaint();
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