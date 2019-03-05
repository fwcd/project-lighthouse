package lighthouse.ui.scene.viewmodel.graphics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SceneViewModel implements Iterable<SceneLayer> {
	private final List<SceneLayer> layers = new ArrayList<>();
	
	public void addLayer(SceneLayer layer) {
		layers.add(layer);
	}
	
	public void removeLayer(SceneLayer layer) {
		layers.remove(layer);
	}
	
	@Override
	public Iterator<SceneLayer> iterator() {
		return layers.iterator();
	}
}
