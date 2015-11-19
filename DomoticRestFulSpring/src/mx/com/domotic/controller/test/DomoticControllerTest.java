package mx.com.domotic.controller.test;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.json.JSONObject;
import org.junit.Test;

import com.sun.jersey.core.util.Base64;

public class DomoticControllerTest {
	
	private final static String TARGET_URL = "http://10.81.192.210:8080/DomoticRestFulSpring/service/domotic/jsonIn";

	@Test
	public void test()
	{
		try {
			URL url = new URL(TARGET_URL);	
		
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("message", "On");
			jsonObject.put("id", 2);
			
			OutputStream outputStream = connection.getOutputStream();
			outputStream.write(jsonObject.toString().getBytes());
			outputStream.flush();
			
			System.out.println("codigo "+connection.getResponseCode());
			
			BufferedReader response = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String output;
			StringBuilder builder = new StringBuilder();
			while((output = response.readLine()) != null)
			{				
				builder.append(output);
				System.out.println(output);
			}
			
			jsonObject = new JSONObject(builder.toString());
			System.out.println(connection.getHeaderFields());
			
			//recuperar imagen
			byte[] salida = Base64.decode(jsonObject.getString("response"));
			
			//**********************************************************//
			//mostrar imagen recuperada en componente
			InputStream in = new ByteArrayInputStream(salida);
			BufferedImage image = ImageIO.read(in);			
			JLabel jLabel= new JLabel();
			jLabel.setIcon(new ImageIcon(image));			
			JFrame frame = new JFrame();
			frame.add(jLabel);
			frame.setVisible(true);
			//**********************************************************//
			
			//guardar imagen recuperada en path
			/*
			File newFile = new File("D:\\cliente.jpg");
			FileOutputStream fop = new FileOutputStream(newFile);
			fop.write(salida);
			fop.flush();
			fop.close();
			*/
			//System.out.println(jsonObject.getString("image"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
