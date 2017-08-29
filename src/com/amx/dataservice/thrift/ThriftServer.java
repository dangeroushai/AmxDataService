package com.amx.dataservice.thrift;

import javax.annotation.Resource;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.springframework.stereotype.Component;

/**
 * Thrift服务器
 * @author DangerousHai
 *
 */
@Component
public class ThriftServer {
	
	@Resource(name = "commonThriftService")
	private CommonThriftService.Iface processor;
	

	private TServer server;
	
	/**
	 * 启动服务端
	 */
	public void startServer(){
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try{
					System.out.println("Thrift Server starting...");
					//设置处理器
					TProcessor tprocessor = new CommonThriftService.Processor<CommonThriftService.Iface>(processor);

					//设置传输通道，普通通道  
					TServerTransport serverTransport = new TServerSocket(CommonThriftService.SERVER_PORT);
					TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(serverTransport);
					tArgs.processor(tprocessor);
					//使用高密度二进制协议  
					tArgs.protocolFactory(new TBinaryProtocol.Factory());
					// tArgs.protocolFactory(new TBinaryProtocol.Factory());
					// tArgs.protocolFactory(new TJSONProtocol.Factory());
					
					server = new TThreadPoolServer(tArgs);
					server.serve();
					
				}catch(Exception e){
					throw new RuntimeException(e);
				}
			}
		}).start();
	}
	
	/**
	 * 关闭服务端
	 */
	public void close(){
		if(server.isServing()){
			server.stop();
		}
	}

}
