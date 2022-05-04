package edu.it.repository;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import com.google.gson.Gson;

import edu.it.annotations.Traceable;
import edu.it.entities.Ticket;

@Repository
public class GrabadorTicketJSON implements GrabadorDeTicket {
	
	@Value("${ozark.pathTickets}")
	private String path;
	
	@Traceable
	public void grabar(Ticket tkt) {
		var archivoSuelto = tkt.id + ".json";
		var archivoComp = String.join("/", path, archivoSuelto);
		System.out.println(archivoComp);
		
		try {
			var contenido = new Gson().toJson(tkt);
			
			FileUtils.writeStringToFile(
					new File(archivoComp),
					contenido, 
					"utf-8");
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

}
