package parts;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import javax.annotation.PostConstruct;
import handlers.OpenHandler;
import model.Media;
import model.MediaType;

import org.eclipse.e4.core.di.annotations.*;
import org.eclipse.e4.core.services.events.IEventBroker;

import org.eclipse.e4.core.di.annotations.Execute;

//@SuppressWarnings("restriction")
public class MediaListPart {
	
	static class MediaCell extends ListCell<Media> {
		@Override
		protected void updateItem(Media item, boolean empty) {
			if (!empty && item != null) {
				// MADE PATHS DYNAMIC - CREATE METHOD TO SET FILENAME
				String FILE_NAME="";

				setText(item.getName());
				switch (item.getType()) {
				case MOVIE:
					 FILE_NAME = "";
					setGraphic(new ImageView("platform:/MEGA_KERNEL_GFX/icons/kaffeine.png"));
					break;
				case PICTURE:
					setGraphic(new ImageView("platform:/MEGA_KERNEL_GFX/icons/games-config-background.png"));
					break;
				default:
					setGraphic(new ImageView("platform:/MEGA_KERNEL_GFX/icons/player-volume.png"));
					break;
				}
			}
			super.updateItem(item, empty);
		}
	}

	@Inject
	EPartService partService;

	@Inject
	EModelService modelService;

	@Inject
	MApplication application;

	private ListView<Media> list;

	@PostConstruct
	void init(BorderPane pane) {
		list = new ListView<Media>(createList());
		list.setId("mediaList");
		list.setCellFactory(new Callback<ListView<Media>, ListCell<Media>>() {

			@Override
			public ListCell<Media> call(ListView<Media> param) {
				return new MediaCell();
			}
		});
		list.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() > 1) {
					handleOpen();
				}
			}
		});
		pane.setCenter(list);
	}

	void handleOpen() {
		MPartStack stack = (MPartStack) modelService.find("content.stack", application);
		Media m = list.getSelectionModel().getSelectedItem();
		String instance = Media.serialize(m);

		for (MStackElement e : stack.getChildren()) {
			if (e instanceof MPart) {
				if (instance.equals(e.getPersistedState().get(MediaPart.MEDIA_OBJECT_KEY))) {
					partService.activate((MPart) e);
					return;
				}
			}
		}

		MPart p = MBasicFactory.INSTANCE.createPart();
		p.setLabel(m.getName());
		if (m.getType() == MediaType.MOVIE) {
			p.setIconURI("platform:/plugin/1/icons/22/kaffeine.png");
		} else if (m.getType() == MediaType.PICTURE) {
			p.setIconURI("platform:/plugin/1/icons/22/games-config-background.png");
		} else {
			p.setIconURI("platform:/plugin/1/icons/22/player-volume.png");
		}

		p.setContributionURI("bundleclass://1/1.parts.MediaPart");
		p.getPersistedState().put(MediaPart.MEDIA_OBJECT_KEY, instance);
		stack.getChildren().add(p);
		partService.activate(p, true);
	}

	@Focus
	void focus() {
		list.requestFocus();
	}

	@Inject
	@Optional
	public void openMedia(@UIEventTopic(OpenHandler.OPEN_EVENT) String event) {
		handleOpen();
	}

	private static ObservableList<Media> createList() {
		ObservableList<Media> l = FXCollections.observableArrayList();
		l.add(new Media(MediaType.PICTURE, "Desert", "platform:/plugin/1/icons/resources/pics/pic1.jpg"));
		l.add(new Media(MediaType.PICTURE, "Lighthouse", "platform:/plugin/1/icons/resources/pics/pic2.jpg"));
		l.add(new Media(MediaType.MOVIE, "Grog", "platform:/plugin/1/icons/resources/movs/mov1.flv"));
		l.add(new Media(MediaType.MOVIE, "OTN", "http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv"));
		return l;
	}

	public static String GetFilePath(String f) {
		String ret = null;
		
		return ret;
	}

	public static String GetFilePathLib(String folder, String f ) {
		String ret = null;
		
		return ret;
	}
}
