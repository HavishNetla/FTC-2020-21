package com.mrhs_robotics.robotlib.debug;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.json.JSONObject;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class MessageSender extends WebSocketServer {
  private MessageSender s;
  int port;

  public MessageSender(int port) throws UnknownHostException {
    super(new InetSocketAddress(port));
    this.port = port;
  }

  public MessageSender(InetSocketAddress address) {
    super(address);
  }

  @Override
  public void onOpen(WebSocket conn, ClientHandshake handshake) {
    System.out.println(
        conn.getRemoteSocketAddress().getAddress().getHostAddress() + " established a connection");
  }

  @Override
  public void onClose(WebSocket conn, int code, String reason, boolean remote) {
    System.out.println(conn + " left");
  }

  @Override
  public void onMessage(WebSocket conn, String message) {
    System.out.println(conn + ": " + message);
  }

  @Override
  public void onError(WebSocket conn, Exception ex) {}

  @Override
  public void onStart() {
      System.out.println("Server started");
      setConnectionLostTimeout(0);
      setConnectionLostTimeout(100);
  }
  public void startServer() throws UnknownHostException {
    s = new MessageSender(port);
    s.start();
    System.out.println("server started on port: " + s.getPort());
  }

  public void sendMessage(JSONObject jo) {
    s.broadcast(jo.toString());
  }
}
