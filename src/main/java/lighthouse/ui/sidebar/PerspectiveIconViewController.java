package lighthouse.ui.sidebar;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lighthouse.model.Board;
import lighthouse.model.GameState;
import lighthouse.ui.ViewController;
import lighthouse.ui.board.ScaleTransform;
import lighthouse.ui.board.view.LocalBoardView;
import lighthouse.ui.loop.GameLoop;
import lighthouse.ui.perspectives.GamePerspective;
import lighthouse.ui.util.CenterPanel;

public class PerspectiveIconViewController implements ViewController {
	private final JComponent component;
	
	public PerspectiveIconViewController(GamePerspective perspective, GameState game, GameLoop loop) {
		component = new JPanel();
		component.setLayout(new BorderLayout());
		
		LocalBoardView boardView = new LocalBoardView(new ScaleTransform(4, 4));
		boardView.setActiveBrickScale(1.0);
		boardView.setPlacedBrickScale(1.0);
		boardView.setDrawGrid(false);
		boardView.setEdgeDrawMode(LocalBoardView.EdgeDrawMode.NONE);
		
		Board initialBoard = perspective.getActiveBoard(game);
		boardView.relayout(initialBoard.getColumns(), initialBoard.getRows());
		
		loop.addRenderer(() -> boardView.draw(perspective.getActiveBoard(game)));
		component.add(new CenterPanel(boardView.getComponent()), BorderLayout.CENTER);
		component.add(new JLabel(perspective.getName()), BorderLayout.SOUTH);
	}
	
	@Override
	public JComponent getComponent() { return component; }
}