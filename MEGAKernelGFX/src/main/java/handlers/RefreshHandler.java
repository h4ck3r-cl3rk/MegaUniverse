package handlers;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

//@SuppressWarnings({ "restriction" })
public class RefreshHandler {
	public static final String REFRESH_EVENT = "media/refresh";
	
	@Execute
	public void execute(IEventBroker broker)
			throws InvocationTargetException, InterruptedException {
		broker.send(REFRESH_EVENT, UUID.randomUUID().toString());
	}
}
