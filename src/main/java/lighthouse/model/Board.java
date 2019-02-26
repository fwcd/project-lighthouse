package lighthouse.model;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import lighthouse.util.IntVec;

/**
 * The game board model representing
 * the entire state of the "Schimmler"-game.
 */
public class Board implements Serializable {
	private static final long serialVersionUID = 6367414981719952292L;
	private int columns;
	private int rows;
	private List<Brick> bricks = new ArrayList<>();
	private transient BoardEditState editState;
	
	/** Creates a new board with the default size of 4x6. */
	public Board() {
		this(4, 6);
	}
	
	/** Constructs a new board of the given size. */
	public Board(int columns, int rows) {
		this.columns = columns;
		this.rows = rows;
	}
	
	/** Fetches the board's column count. */
	public int getColumns() { return columns; }
	
	/** Fetches the board's row count. */
	public int getRows() { return rows; }
	
	/** Fetches all bricks on this board. */
	public Collection<Brick> getBricks() { return bricks; }
	
	/** Pushes a brick onto the board. */
	public void add(Brick brick) { bricks.add(brick); }
	
	/** Replaces a brick. */
	public void replace(Brick oldBrick, Brick newBrick) {
		Collections.replaceAll(bricks, oldBrick, newBrick);
	}
	
	/** Removes and returns a brick at a certain position. */
	public Brick removeBrickAt(IntVec gridPos) {
		Iterator<Brick> iterator = bricks.iterator();
		while (iterator.hasNext()) {
			Brick brick = iterator.next();
			if (brick.contains(gridPos)) {
				iterator.remove();
				return brick;
			}
		}
		return null;
	}
	
	/** Clears the board's contents. */
	public void clear() {
		bricks.clear();
		editState.reset();
	}
	
	/** Fetches the cell's color at the specified position. */
	public Color colorAt(IntVec gridPos) {
		Brick brick = locateBrick(gridPos);
		if (brick == null) {
			return Color.BLACK;
		} else {
			return brick.getColor();
		}
	}
	
	public Color colorAt(int x, int y) {
		return colorAt(new IntVec(x, y));
	}
	
	public boolean hasBrickAt(IntVec gridPos) {
		return bricks.stream().anyMatch(brick -> brick.contains(gridPos));
	}
	
	public Brick locateBrick(IntVec gridPos) {
		return bricks.stream()
			.filter(brick -> brick.contains(gridPos))
			.findFirst()
			.orElse(null);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Board)) return false; 
		Board board = (Board) obj;
		return board.bricks.equals(bricks);
	}
	
	/** Deeply copies this board. */
	public Board copy() {
		Board copied = new Board(columns, rows);
		copied.bricks.addAll(bricks);
		return copied;
	}
	
	/** Fetches the current editing state of the board. */
	public BoardEditState getEditState() {
		if (editState == null) {
			// Lazy initialization/reinitalization after deserialization
			editState = new BoardEditState();
		}
		return editState;
	}
}
