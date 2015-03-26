import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketHandler;

public class TestServer extends Server {
	SelectChannelConnector connector;
	WebSocketHandler web_handler;
	ResourceHandler res_handler;

	public TestServer(int port, String dir){
		connector =new SelectChannelConnector();
		connector.setPort(port);
		addConnector(connector);
		
		web_handler = new WebSocketHandler(){

			@Override
			public WebSocket doWebSocketConnect(HttpServletRequest request,
					String param) {
				// TODO Auto-generated method stub
				return null;
			}
			
		};
		res_handler = new ResourceHandler();
		res_handler.setDirectoriesListed(true);
		res_handler.setResourceBase(dir);
		
		web_handler.setHandler(res_handler);
		setHandler(web_handler);

		
		
	}
	public static void main(String...args) throws Exception{
		TestServer server =new TestServer(8800, "html");
		server.start();
		server.join();
	}
	
}
