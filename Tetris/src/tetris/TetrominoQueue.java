package tetris;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;

import tetris.Tetrominos.IShape;
import tetris.Tetrominos.LShape;
import tetris.Tetrominos.OShape;
import tetris.Tetrominos.ReverseLShape;
import tetris.Tetrominos.SShape;
import tetris.Tetrominos.TShape;
import tetris.Tetrominos.ZShape;

public class TetrominoQueue extends JComponent{
	private ArrayList<String> bag;
	private ArrayList<String> nextBag;
	private ArrayList<String> display;
	private ArrayList<Tetromino> tetrominoDisplay = new ArrayList<>();
	private Gameboard gb;
	public TetrominoQueue(Gameboard gb) {
		this.bag = gb.getBag();
		this.nextBag = gb.getNextBag();
		this.gb = gb;
        this.setPreferredSize(new Dimension(100, 600));
        this.setLayout(null);
        
	}
	
	public void update() {
		display = new ArrayList<String>();
		bag = gb.getBag();
		nextBag = gb.getNextBag();
		if(tetrominoDisplay != null) {
			ArrayList<Tetromino> tempList = new ArrayList<>(tetrominoDisplay);
			for(Tetromino tetromino: tempList) {
				tetromino.displayRemoveSelf(tetrominoDisplay,this);
			}
		}else {
			tetrominoDisplay = new ArrayList<Tetromino>();
		}
		if(bag.size()<3) {
			
			for(String nextTetromino: bag) {
				display.add(nextTetromino);
			}
			for(int i = 0; i<3-bag.size();i++) {
				display.add(nextBag.get(i));
			}
		} else {
			for(int i = 0; i<3;i++) {
				display.add(bag.get(i));
			}
		}

		displayTetromino();
		
		revalidate();
	}
	
	private void displayTetromino() {
		Tetromino temp;
	    for(String tetromino : display) {
	    	switch (tetromino) {
		        case "L":
		            temp = new LShape(gb);
		            break;
		        case "T":
		            temp = new TShape(gb);
		            break;
		        case "RL":
		            temp = new ReverseLShape(gb);
		            break;
		        case "I":
		            temp = new IShape(gb);
		            break;
		        case "O":
		            temp = new OShape(gb);
		            break;
		        case "S":
		            temp = new SShape(gb);
		            break;
		        case "Z":
		            temp = new ZShape(gb);
		            break;
		        default:
		            System.out.println("lul");
		            return;
	    	}
	    	try {
	    		tetrominoDisplay.add(temp);

		    } catch (Exception e) {
		        System.out.println("lul: " + e.getMessage());
		    }
	    }
	    for(int i = 0; i<tetrominoDisplay.size(); i++) {
	    	for(int y = 0; y<3;y++) {
	    		tetrominoDisplay.get(i).moveSideways(0);
	    	}
	    	
	    	for(int x = 0; x<i; x++) {
	    		tetrominoDisplay.get(i).moveDown();
	    		tetrominoDisplay.get(i).moveDown();
	    		tetrominoDisplay.get(i).moveDown();
	    	}
	    	
	        for (Tetromino tetromino : tetrominoDisplay) {
	        	for (Block block : tetromino.getBodyPieces()) {
		            this.add(block);
		        }
	        }
	    }
	    
	}
}
