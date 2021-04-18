package com.init.products.rest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.init.products.dao.ProductsDAO;
import com.init.products.entitys.Cliente;
import com.init.products.entitys.Pedido;
import com.init.products.entitys.Product;

@RestController
@RequestMapping("products")
public class ProductsRest {
	
	@Autowired
	private ProductsDAO productDAO;
	public int horaSolicitud;
	public double ultimoPrecio;
	@GetMapping
	public ResponseEntity<List<Product>> getProduct(){
		List<Product> products = productDAO.findAll();
		System.out.print(products); 
		return ResponseEntity.ok(products);
	
	}
	
	@RequestMapping(value="{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable("productId")Long productId){
		Optional<Product> optionalProduct= productDAO.findById(productId);
			double valorLeido;
			double valorNuevo;
			double iva;
			SimpleDateFormat standar;
			String standarFormat;
			 
				standar = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Calendar now = new GregorianCalendar();
				Date nowDate = now.getTime();
				standarFormat = standar.format( nowDate );
			if(optionalProduct.isPresent() && (optionalProduct.get().getPrecio() > 70000) && (optionalProduct.get().getPrecio() < 100000)) {
				Pedido pedido=new Pedido();
				Date hora = now.getTime();
				horaSolicitud= nowDate.getHours();
				
				System.out.println("\n" + "Hora de la realización del pedido: " + standarFormat); 
				Cliente cliente=new Cliente("12345","11#14-08",horaSolicitud);
				pedido.setDomicilio(10000);
				pedido.setIva(19);
				
				
				valorLeido = optionalProduct.get().getPrecio();
				
				if(ultimoPrecio>70000 && valorLeido > 100000) {
					pedido.setDomicilio(0);
					System.out.println("se resta el valor del domicilio"); 
				}
				valorNuevo =valorLeido+(valorLeido*(19)/100);
				iva = valorLeido*(19)/100;
				System.out.println("**************FACTURA GENERADA*********** "); 
				System.out.println(" Cédula Cliente  =   " + cliente.getCedula());
				System.out.println(" Dirección       =   " + cliente.getDireccion()+ "\n");
				System.out.println(" id Producto  	 =   " + optionalProduct.get().getId());
				System.out.println(" Precio Producto =  $" + optionalProduct.get().getPrecio());
				System.out.println(" IVA del 19%  	 =  $" + iva);
				System.out.println(" Valor Domicilio =  %" + pedido.getDomicilio());
				System.out.println(" VALOR TOTAL     =  $" + (valorNuevo+pedido.getDomicilio()));
				ultimoPrecio= valorLeido;
				return ResponseEntity.ok(optionalProduct.get());
			} else if(optionalProduct.isPresent() && ((optionalProduct.get().getPrecio() > 100000) || ultimoPrecio>100000) ) {
				Pedido pedido=new Pedido();
				Date hora = now.getTime();
				horaSolicitud= nowDate.getHours();
				Cliente cliente=new Cliente("12345","11#14-08",horaSolicitud);
				pedido.setDomicilio(0);
				pedido.setIva(19);
			;
				valorLeido = optionalProduct.get().getPrecio();
				valorNuevo =valorLeido+(valorLeido*(19)/100);
				iva = valorLeido*(19)/100;
				System.out.println("**************FACTURA GENERADA*********** "); 
				System.out.println(" Cédula Cliente  =   " + cliente.getCedula());
				System.out.println(" Dirección       =   " + cliente.getDireccion()+ "\n");
				System.out.println(" id Producto  	 =   " + optionalProduct.get().getId());
				System.out.println(" Precio Producto =  $" + optionalProduct.get().getPrecio());
				System.out.println(" IVA del 19%  	 =  $" + iva);
				System.out.println(" Valor Domicilio =  %" + pedido.getDomicilio());
				System.out.println(" VALOR TOTAL     =  $" + (valorNuevo+pedido.getDomicilio()));
				ultimoPrecio= valorLeido;
			}
			return ResponseEntity.noContent().build();
			
	}



	public Optional<Product> listarId(Long id){
		return productDAO.findById(id);
	}
	
	@GetMapping("/editar")
	public ResponseEntity<Product> editar(@RequestBody Product producto) {
		int horaLimite;
		SimpleDateFormat standar;
		String standarFormat;
		 
		standar = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar now = new GregorianCalendar();
		Date nowDate = now.getTime();
		standarFormat = standar.format( nowDate );
		System.out.println("\n" + "Hora de la petición para editar: " + standarFormat); 
		horaLimite= 5 + horaSolicitud;
		System.out.print("editado con éxito");
		if (horaSolicitud < horaLimite) {
		Product obj = productDAO.save(producto);
		
		return new ResponseEntity<Product>(obj, HttpStatus.OK);
		}else {
			return ResponseEntity.noContent().build();
		}
		
	}
		
		
		@GetMapping("/eliminar/{id}")
		public ResponseEntity<Product> delete(@PathVariable Long id) {
			int horaLimite;
			SimpleDateFormat standar;
			String standarFormat;
			double descuento;
			 
			standar = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar now = new GregorianCalendar();
			Date nowDate = now.getTime();
			standarFormat = standar.format( nowDate );
			System.out.println("\n" + "Hora de la petición para eliminar: " + standarFormat); 
			horaLimite= 12 + horaSolicitud;
			System.out.print("eliminado con éxito");
			
			Optional<Product> producto= productDAO.findById(id);
			if (horaSolicitud < horaLimite) {
			productDAO.deleteById(id);
			descuento= ultimoPrecio*(10/100);
			
			System.out.print("se factura el 10% = $"+ descuento);
			System.out.print("Cancelado con éxito");
			}else {
				return ResponseEntity.noContent().build();
			}
		
			return ResponseEntity.noContent().build();
	}
		
	
	
		
	
	
	


}
