package mx.com.domotic.controller;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Enumeration;

import javax.ws.rs.QueryParam;
import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/service/domotic")//this annotation is used for defining incoming request url
public class DomoticController {

	private static Logger logger= Logger.getLogger(DomoticController.class);
	
	public static String incoming=null;
	private OutputStream output = null;
	private BufferedReader input = null;
	SerialPort serialPort;
	CommPortIdentifier portId = null;
	private static final String PORT_NAME_LINUX = "/dev/ttyACM0";
	private static final String PORT_NAME_WINDOWS = "COM3";
	private static final int TIME_OUT = 2000;
	private static final int DATA_RATE = 9600;
	
	//json
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<byte[]> control(@QueryParam("id") String id, @QueryParam("msg") String msg) throws IOException
	{		
		logger.info("receiving Information. ID: "+id+" Message: "+msg);
		JSONObject outJson = new JSONObject();		
		switch (Integer.parseInt(id)) {			
		case 1://rele 1			
			outJson.put("S", "1");
			if("1".equals(msg))	{	sendData("A");	outJson.put("r", true);		}	
			else				{	sendData("a");	outJson.put("r", false);	}
			break;
		case 2://rele 2
			outJson.put("S", "1");
			if("1".equals(msg))	{	sendData("B");	outJson.put("r", true);		}	
			else				{	sendData("b");	outJson.put("r", false);	}
			break;
		case 3://rele 3
			outJson.put("S", "1");
			if("1".equals(msg))	{	sendData("C");	outJson.put("r", true);		}	
			else				{	sendData("c");	outJson.put("r", false);	}
			break;
		case 4://rele 4
			outJson.put("S", "1");
			if("1".equals(msg))	{	sendData("D");	outJson.put("r", true);		}	
			else				{	sendData("d");	outJson.put("r", false);	}
			break;
		case 5://rele 5
			outJson.put("S", "1");
			if("1".equals(msg))	{	sendData("E");	outJson.put("r", true);		}	
			else				{	sendData("e");	outJson.put("r", false);	}
			break;
		case 6://rele 6
			outJson.put("S", "1");
			if("1".equals(msg))	{	sendData("F");	outJson.put("r", true);		}	
			else				{	sendData("f");	outJson.put("r", false);	}
			break;
		case 7://rele 7
			outJson.put("S", "1");
			if("1".equals(msg))	{	sendData("G");	outJson.put("r", true);		}	
			else				{	sendData("g");	outJson.put("r", false);	}
			break;
		case 8://rele 8
			outJson.put("S", "1");
			if("1".equals(msg))	{	sendData("H");	outJson.put("r", true);		}	
			else				{	sendData("h");	outJson.put("r", false);	}
			break;
		case 9://rele 9
			outJson.put("S", "1");
			if("1".equals(msg))	{	sendData("I");	outJson.put("r", true);		}	
			else				{	sendData("i");	outJson.put("r", false);	}
			break;
		case 10://rele 10
			outJson.put("S", "1");
			if("1".equals(msg))	{	sendData("I");	outJson.put("r", true);		}	
			else				{	sendData("i");	outJson.put("r", false);	}
			break;
		case 11://ping
			//outJson.put("response", "Conection was successful");
			sendData("*");
			/*
			synchronized (input) {
				byte[] buffer = new byte[1024];		
				int len = -1;
//				String in = new String(buffer, 0, input.read( buffer ));
				while( ( len = this.input.read( buffer ) ) > -1 ) {
			       System.out.print( new String( buffer, 0, len ) );
			    }
			}
			*/
			//SerialReader reader = new SerialReader(input);
			//reader.run();
			//(new Thread( new SerialReader( input) ) ).start();
			
			//leyendo serial
			/*
			byte[] buffer = new byte[1024];		
			int len = -1;
			String in = new String(buffer, 0, input.read( buffer ));
			/*while( ( len = this.input.read( buffer ) ) > -1 ) {
		       System.out.print( new String( buffer, 0, len ) );
		    }*/
			/*System.out.println(incoming);
			int i = 1;
			for( String relevador : incoming.split(","))
			{
				outJson.put("r"+ (i++), "1".equals(relevador) ? true : false);
			}
			*/
			System.out.println(input.readLine());
			
			outJson.put("S", "*");
			/*outJson.put("r1", true);
			outJson.put("r2", false);
			outJson.put("r3", true);
			outJson.put("r4", false);
			outJson.put("r5", false);
			outJson.put("r6", false);
			outJson.put("r7", false);
			outJson.put("r8", false);
			outJson.put("r9", false);
			outJson.put("r10", false);*/
			/* envio imagenes
			try {
				File imagen = new File("D:\\test.jpg");
				byte[] bytes = Files.readAllBytes(imagen.toPath());				
				outJson.put("image", DatatypeConverter.printBase64Binary(bytes));				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			break;
		case 12://request image
			try {
				File imagen = new File("D:\\test.jpg");
				byte[] bytes = Files.readAllBytes(imagen.toPath());
				outJson.put("response", DatatypeConverter.printBase64Binary(bytes));				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}		
		logger.info("Server response: "+outJson.toString());
		logger.info("Finish the process.");		
		return new ResponseEntity<byte[]>(outJson.toString().getBytes(), HttpStatus.OK);
	}
	
	
	
	public static class SerialReader implements Runnable {
		 
	    InputStream in;
	 
	    public SerialReader( InputStream in ) {
	      this.in = in;
	    }
	 
	    public void run() {
	      byte[] buffer = new byte[ 1024 ];
	      int len = -1;
	      try {
	        while( ( len = this.in.read( buffer ) ) > -1 ) {
	        	incoming = new String( buffer, 0, len ) ;
	          System.out.print( new String( buffer, 0, len ) );
	        }
	      } catch( IOException e ) {
	        e.printStackTrace();
	      }
	    }
	  }
	
	
	
	@RequestMapping(value = "/jsonIn", method = RequestMethod.POST)
	public ResponseEntity<byte[]> control(@RequestBody String string)	
	{		
		JSONObject outJson = new JSONObject();		
		JSONObject inJson = new JSONObject(string);				
		switch (inJson.getInt("id")) {		
		case 1://ping
			outJson.put("response", "Conection was successful");
			outJson.put("rele1", true);
			outJson.put("rele2", false);
			outJson.put("rele3", true);
			outJson.put("rele4", false);
			break;
		case 2://request image
			try {
				File imagen = new File("D:\\test.jpg");
				byte[] bytes = Files.readAllBytes(imagen.toPath());				
				outJson.put("response", DatatypeConverter.printBase64Binary(bytes));				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3://rele 1
			if("On".equals(inJson.getString("message")))			
			{
				sendData("A");
				outJson.put("response", true);
			}
			else
			{
				outJson.put("response", false);
			}
			break;
		default:
			break;
		}
		
		System.out.println(outJson.toString().getBytes().length);
		return new ResponseEntity<byte[]>(outJson.toString().getBytes(), HttpStatus.OK);
	}
	
	
	private void sendData(String data)
	{
		initializeArduinoConnection();
		try {
			logger.info("Sending information to Arduino");
			output.write(data.getBytes());
		} catch (IOException e) {
			logger.error(e.getCause());
			e.printStackTrace();
		}		
	}
	
	public synchronized void initializeArduinoConnection()
	{		
	logger.info("Initializing the arduino connection");
		
		System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM0");
		
		Enumeration<?> portEnum = CommPortIdentifier.getPortIdentifiers();		
		//iteration looking for the port
		while (portEnum.hasMoreElements())
		{			
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			logger.info("Trying to connect to port: " + currPortId);
			if( PORT_NAME_LINUX.equals(currPortId.getName()))
			{
				portId = currPortId;
				break;
			}
			else
			{
				if( PORT_NAME_WINDOWS.equals(currPortId.getName()))
				{
					portId = currPortId;
					break;
				}
			}			
		}
		
		
		try {
			//open serial port, and use class name for the appName
			
			 
			//logger.info("el propietario es " + portId.getCurrentOwner());	
			
			if(portId == null)
			{
				portId = CommPortIdentifier.getPortIdentifier("/dev/ttyACM0");
			}
			if(portId.getCurrentOwner()==null)
			{
				
				//portId.open(this.getClass().getName(), TIME_OUT).close();
				serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);
				logger.info("Lock port " +serialPort);
			}
			//set port parameters
			serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			logger.info("Setting properties to port" );
			
			//open the streams
			output = serialPort.getOutputStream();
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			
            serialPort.notifyOnDataAvailable(true);
		} catch (PortInUseException e) {
			logger.error(e.getCause());
			e.printStackTrace();
		} catch (UnsupportedCommOperationException e) {
			logger.error(e.getCause());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.getCause());
			e.printStackTrace();
		} catch (NoSuchPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Connection with arduino successful");
	}


}
