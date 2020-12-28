package com.marcodes;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

// tudo que possui um @exemplo e chamado de notacao dentro de java
@Controller
public class ContatosControle {

	private static final ArrayList<Contato> LISTA_CONTATOS = new ArrayList<>();
	
	/* Porque nao usamos um array convencional? a resposta e a diferenca de limite 
	entre o array convencional e o ArrayList, o convencional deve possuir um limite 
	definido enquanto o ArrayList nao precisa */
	
	//private static final Contato[] LISTA_CONTATO_2 = new Contato[10];
	
	
	static {
		LISTA_CONTATOS.add(new Contato("1", "ContatoTeste1", "11900000000"));
		LISTA_CONTATOS.add(new Contato("2", "ContatoTeste2", "11900000001"));
		LISTA_CONTATOS.add(new Contato("3", "ContatoTeste3", "11900000002"));
	}
	
	/* este static fara com que o programa inicie com alguns dados ja pre-carregados, 
	 * a ideia e que ele funcione de forma dinamica, portanto este static so esta aqui
	 * para sabermos que o app funciona 
	 */
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	/* este GetMapping serve para fazer a requisicao web da pagina que 
	 * estamos referenciando. Como esta escrito "/" isto significa que estamos 
	 * requisitando algo dentro do diretorio raiz
	 */
	
 	@GetMapping("/contatos")
	public ModelAndView listar() {
 		ModelAndView mav = new ModelAndView("listar");
 		
 		mav.addObject("contatos", LISTA_CONTATOS);
 		
 		return mav;
	}
 	
 	@GetMapping("/contatos/novo")
 	public ModelAndView novo() {
		ModelAndView mav = new ModelAndView("formulario");
		
 		mav.addObject("contato", new Contato());
 		
 		return mav;
 	}
 	
 	
 	/* este PostMapping serve para adicionar novos dados
	 */
 	@PostMapping("/contatos")
 	public String cadastrar(Contato contato) {
 		String id = UUID.randomUUID().toString();
 		contato.setId(id);
 		LISTA_CONTATOS.add(contato);
 		
 		return "redirect:/contatos";
 	}
 	
 	@GetMapping("/contatos/{id}/editar")
 	public ModelAndView editar (@PathVariable String id) {
 		ModelAndView mav = new ModelAndView ("formulario");
 		
 		Contato contato = procurarContato(id);
 		
 		mav.addObject("contato", contato);
 		
 		return mav;
 	}
 	
 	/* este PutMapping serve para atualizar dados ja existentes
	 */
 	@PutMapping("/contatos/{id}")
 	public String atualizar(Contato contato) {
 		
 		Integer indice = procurarIndiceContato(contato.getId());
 		Contato contatoVelho = LISTA_CONTATOS.get(indice);
 		LISTA_CONTATOS.remove(contatoVelho);
 		LISTA_CONTATOS.add(indice, contato);
 		
 		return "redirect:/contatos";
 		}
 	
 	/* este deleteMapping vai buscar os parametros definitos e vai remover
 	 * o dado equivalente por meio de uma solicitacao do tipo DELETE.
 	 */
 	@DeleteMapping("/contatos/{id}")
 	public String remover(@PathVariable String id){
 		
 		Contato contato = procurarContato(id);
 		
 		LISTA_CONTATOS.remove(contato);
 		
 		return "redirect:/contatos";
 		
 	}
 	
 	
 	//Metodos Auxiliares =================================================
 	
 	private Contato procurarContato (String id) {
 			Integer indice = procurarIndiceContato(id);
		
 			if (indice != null) {
 				Contato contato = LISTA_CONTATOS.get(indice);
 				return contato;
 			}
		
 			return null;
 	}
 	
 	
 	private Integer procurarIndiceContato(String id) {
 			for(int i = 0; i < LISTA_CONTATOS.size(); i++) {
 				Contato contato = LISTA_CONTATOS.get(i);
 				
 				if (contato.getId().equals(id)) {
 					return i;
 				}
 			}
 			
 			return null;
 	}
 	
 }
