import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.eclipse.jetty.websocket.WebSocket;

public class TestSocket implements WebSocket {

	Connection connection = null;
	static Set<TestSocket> connections_set = new CopyOnWriteArraySet<TestSocket>();
	static Map<String, TestSocket> socketList = new HashMap<String, TestSocket>();

	public void onClose(int arg0, String arg1) {
		synchronized (socketList) {
			socketList.remove(arg1);
		}
		synchronized (connections_set) {
			connections_set.remove(arg1);
		}
	}

	@Override
	public void onOpen(Connection c) {
		connection = c;
		synchronized (connections_set) {
			connections_set.add(this);
		}

	}

	public String getUSerList() {
		String result = "";
		String add = "";
	}

}
